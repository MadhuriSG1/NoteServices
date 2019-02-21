package com.api.note.service;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private CollaboratorRepository collaboratorRepository;

	@Autowired
	private ModelMapper modelMapper;


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
		long userid = TokenUtil.verifyToken(token);

		 List<Note> noteList=noterepository.findAllByStatus(userid, Boolean.valueOf(isArchive), Boolean.valueOf(isTrash))
				.orElse(new ArrayList<Note>());
		 noteList.addAll(collaboratorService.getCollaboratorNotes(token));
		 
		 List<TotalNotesDto> xyz=new ArrayList<TotalNotesDto>();
		 for(int i=0;i<noteList.size();i++)
		 {
			 List<BigInteger> allUsers=new ArrayList<BigInteger>();
			 Optional<List<Object>> usersList=collaboratorRepository.findAllUsersOfNote(noteList.get(i).getNoteid());
			 TotalNotesDto lmn=null;
			 if(usersList.isPresent())
			 {
				 usersList.get().stream().forEach(each->allUsers.add((BigInteger)each));
				 ResponseEntity<CollaboratorUserDetails[] >response=restTemplate.postForEntity(ROOT_URI,allUsers,CollaboratorUserDetails[].class);
				 lmn=new TotalNotesDto(noteList.get(i),Arrays.asList(response.getBody()));
			 }
			 else
			 {
				 lmn=new TotalNotesDto(noteList.get(i),new ArrayList<CollaboratorUserDetails>());
			 }
			 xyz.add(lmn);		 }
		return xyz;
		
	}
	
}	
