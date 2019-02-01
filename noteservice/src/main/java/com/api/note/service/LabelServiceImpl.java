package com.api.note.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.note.dto.LabelDto;
import com.api.note.entity.Label;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;
import com.api.note.repository.LabelRepository;
import com.api.note.repository.NoteRepository;
import com.api.note.util.TokenUtil;
@Service
public class LabelServiceImpl implements LabelService{

	@Autowired
	private LabelRepository labelrepository;

	@Autowired
	private NoteRepository noterepository;
	

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createLabel(LabelDto labeldto, String token) throws NoteException {
		// TODO Auto-generated method stub
		long userid = TokenUtil.verifyToken(token);
		Note note=noterepository.findById(labeldto.getNoteid()).orElseThrow(()->new NoteException("Notes Not Found ",100));
		Label label=modelMapper.map(labeldto, Label.class);
		label.setUserId(userid);
		List<Label> labels=note.getLabels();
		labels.add(label);
		note.setLabels(labels);
		noterepository.save(note);	
	}
/*
	@Override
	public void createLabel(Label label, String token) throws NoteException {
		long userid = TokenUtil.verifyToken(token);
		//Optional<Note> note=noterepository.findById(label.getLabelId());
		label.setUserId(userid);
		//note.
		label.save(label);
		
	}*/

	@Override
	public void updateLabel(Note note, String token) throws NoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLabel(Note note, String token) throws NoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Label> getAllLabels(long noteid,String token) throws NoteException {
		TokenUtil.verifyToken(token);
		Note note=noterepository.findById(noteid).orElseThrow(()
				->new NoteException("Notes Not Found ",100));
		List<Label> labels=note.getLabels();
		return labels;
	}



}
