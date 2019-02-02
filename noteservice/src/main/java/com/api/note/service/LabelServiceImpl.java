package com.api.note.service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.note.entity.Label;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.LabelRepository;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class LabelServiceImpl implements LabelService{

	@Autowired
	private LabelRepository labelrepository;

	@Autowired
	private NoteRepository noterepository;
	

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createLabel(Label label, String token) throws NoteException {

		long userid = TokenUtil.verifyToken(token);
		 label.setUserId(userid);
		labelrepository.save(label);
	}
	

	@Override
	public void labelAddToNote(long noteid, long labelid) throws NoteException {
		Label label=labelrepository.findById(labelid).orElseThrow(()-> new NoteException("Label not found"));
		
		log.info("label");
		Note note=noterepository.findById(noteid).orElseThrow(()-> new NoteException("Note not found"));
	    List<Label>	labels=note.getLabels();
		labels.add(label);
		note.setLabels(labels);
		
		log.info("note");
		noterepository.save(note);		
	}


	@Override
	public List<Label> getAllLabels(String token) throws NoteException {
	
	
		long userid = TokenUtil.verifyToken(token);		
		List<Label> list = labelrepository.findAll().stream().filter
				(label->label.getUserId()==userid).collect(Collectors.toList());

		return list;
	}


	@Override
	public void removeLabelFromNote(long noteid, long labelId) {
		Label label=labelrepository.findById(labelId).get();
		Note note=noterepository.findById(noteid).get();
		note.getLabels().remove(label);
		noterepository.save(note);
	}


	@Override
	public void deleteLabel(Label label, String token) throws NoteException {
		
			long userid = TokenUtil.verifyToken(token);
			labelrepository.delete(label);
	}


	@Override
	public void updateLabel(Label label, String token) throws NoteException {
		long userid = TokenUtil.verifyToken(token);
		label.setUserId(userid);
		labelrepository.save(label);
			
	}


	@Override
	public List<?> sortByTitle() {
		List<Label> labels=labelrepository.findAll();
		labels.sort(Comparator.comparing(Label::getLabelTitle));
		return labels;
	}



}
