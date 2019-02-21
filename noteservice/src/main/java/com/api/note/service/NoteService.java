package com.api.note.service;
import java.util.List;
import com.api.note.dto.NoteDto;
import com.api.note.dto.TotalNotesDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;

public interface NoteService {

	void createNote(NoteDto createnotedto,String token) throws NoteException;

	public void updateNote(Note note, String token) throws NoteException;

	public void deleteNote(Note note, String token) throws NoteException;

	public List<TotalNotesDto> getAllNotes(String token,String isTrash,String isArchive)throws NoteException;

}
