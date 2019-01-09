package com.api.note.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.api.note.dto.CreateNoteDto;
import com.api.note.entity.Note;
import com.api.note.exception.NoteException;

public interface NoteService {

	public Note createNote(@Valid CreateNoteDto createnotedto,String token) throws NoteException;

	public void updateNote(Note note, String token) throws NoteException;

	public void deleteNote(Note note, String token) throws NoteException;

	public List<Note> getNotesById(String token) throws NoteException;

	public List<Note> getAllNotes() throws NoteException;

	//List<Note> listOfNotes(String token, String value) throws NoteException;



}
