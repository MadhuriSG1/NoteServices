package com.api.note.service;
import java.util.List;
import com.api.note.dto.LabelDto;
import com.api.note.entity.Label;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;

public interface LabelService {

	void createLabel(LabelDto labeldto,String token) throws NoteException;

	public void updateLabel(Note note, String token) throws NoteException;

	public void deleteLabel(Note note, String token) throws NoteException;

	public List<Label> getAllLabels(long noteid,String token) throws NoteException;

}
