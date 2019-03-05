package com.api.note.service;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.api.note.dto.CollaboratorUserDetails;
import com.api.note.dto.NoteDto;
import com.api.note.dto.TotalNotesDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.CollaboratorRepository;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;

import javassist.compiler.ast.ASTList;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NoteRepository noterepository;
	
	@Autowired
	private CollaboratorRepository collaboratorRepository;
	
	@Autowired
	private CollaboratorService collaboratorService;
	
	@Autowired
	private RestTemplate restTemplate;
			 
	@Value("${spring.ROOT_URI}")
	 private String ROOT_URI; 
	
	/**
	 * Create Note
	 * 
	 * @throws NoteException
	 */
	@Override
	public void createNote(NoteDto createnotedto, String token) throws NoteException {

		long id = TokenUtil.verifyToken(token);
		Note note = modelMapper.map(createnotedto, Note.class);
		note.setUserId(id);
		noterepository.save(note);
//		return note;
	}

	/**
	 * Update note
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public void updateNote(Note note, String token) throws NoteException {

		long userid = TokenUtil.verifyToken(token);
		log.info("userid");
		note.setUpdateddate(LocalDateTime.now());
		note.setUserId(userid);
		noterepository.save(note);
	}

	/**
	 * Delete note
	 * @param note,token
	 * @throws NoteException
	 */
	@Override
	public void deleteNote(Note note, String token) throws NoteException 
	{
		long id = TokenUtil.verifyToken(token);
		noterepository.delete(note);
	}
	
	/**
	 * Get all notes
	 * @throws NoteException
	 */
	@Override
	public  List<TotalNotesDto> getAllNotes(String token,String isArchive,String isTrash) throws NoteException {
		long userId = TokenUtil.verifyToken(token);
		

		 List<Note> noteList=noterepository.findAllByStatus(userId, Boolean.valueOf(isArchive), Boolean.valueOf(isTrash))
				.orElse(new ArrayList<Note>());
		 List<Note> collabNotes = collaboratorService.getCollaboratorNotes(token);
		 noteList.addAll(collabNotes);
		// System.out.println("notelist  "+noteList);
		 //return noteList.stream().map(this::getCollabNotes).collect(Collectors.toList());
		 List<TotalNotesDto> totalNotesList=new ArrayList<TotalNotesDto>();
		 for(int i=0;i<noteList.size();i++)
		 {
			 List<BigInteger> allUsers=new ArrayList<BigInteger>();
			 Optional<List<BigInteger>> usersList=collaboratorRepository.findAllUsersOfNote(noteList.get(i).getNoteid());
			 System.out.println("usersList = "+usersList);
			 TotalNotesDto totalNotes=null;
			 if(usersList.isPresent())
			 {
				 System.out.println("ghkjghkkhggggggggggg");
				 usersList.get().stream().forEach(e->allUsers.add((BigInteger) e));
				 ResponseEntity<CollaboratorUserDetails[]>userDetails=restTemplate.postForEntity(ROOT_URI,allUsers,
						 CollaboratorUserDetails[].class);
				 System.out.println("userDetails  "+userDetails);
				 totalNotes=new TotalNotesDto(noteList.get(i),Arrays.asList(userDetails.getBody()));
				 System.out.println("totalNotes  "+totalNotes);
				 
			 }
			 else
			 {
				 totalNotes=new TotalNotesDto(noteList.get(i),new ArrayList<CollaboratorUserDetails>());
			 }
			 totalNotesList.add(totalNotes);	
			 System.out.println("totalNotesList  ="+totalNotesList);}
		return totalNotesList;	
	}
	/*private TotalNotesDto getCollabNotes(Note note){
		return collaboratorRepository.findAllUsersOfNote(note.getNoteid()).map(this::CollaboratorUserDetails).map(collabList->{
			return new TotalNotesDto(note, collabList);
		}).orElse(new TotalNotesDto(note, new ArrayList<>()));
	}
	private List<CollaboratorUserDetails> CollaboratorUserDetails(List<BigInteger> userIds){
		return restTemplate.postForEntity(ROOT_URI,userIds,CollaboratorUserDetails[].class;
		 
	}*/
}	

/*

for (int i = 0; i < notesList.size(); i++) {
	List<BigInteger> ll = new ArrayList<BigInteger>();
	Optional<List<Object>> optionalList = collabRepo.findAllUsersOfNote(notesList.get(i).getId());
	SendingNotes zz = null;
	if (optionalList.isPresent()) {
		optionalList.get().stream().forEach(x -> ll.add((BigInteger) x));
		ResponseEntity<CollabUserDetails[]> response = restTemplate.postForEntity(ROOT_URI, ll,
				CollabUserDetails[].class);
		zz = new SendingNotes(notesList.get(i), Arrays.asList(response.getBody())); // ll at response.getBody()
	} else {
		zz = new SendingNotes(notesList.get(i), new ArrayList<CollabUserDetails>());

	}
	xyz.add(zz);
}
*/