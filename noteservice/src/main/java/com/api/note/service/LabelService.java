package com.api.note.service;


import java.util.List;

import com.api.note.entity.Label;

import com.api.note.exception.NoteException;

public interface LabelService {

	void createLabel(Label label,String token) throws NoteException;
	
	public void labelAddToNote(long noteid, long labelid) throws NoteException;

	List<Label> getAllLabels(String token)throws NoteException;

	void removeLabelFromNote(long noteid, long labelId);

	void deleteLabel(Label labelTitle, String token) throws NoteException;

	void updateLabel(Label label, String token) throws NoteException;

	List<?> sortByTitle();




}
