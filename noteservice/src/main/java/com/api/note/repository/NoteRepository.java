package com.api.note.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.api.note.entity.Note;
public interface NoteRepository extends CrudRepository<Note,Long> {

	@Query(value="SELECT * FROM note t WHERE t.userid = :id", nativeQuery=true)
	List<Note> findAllById(@Param("id") long id);

	
	/* @Query("SELECT t FROM Todo t WHERE t.title = 'title'")
	    public List<Note> findById();*/
	/*@Query("SELECT n FROM Note n WHERE n.value = 'value'")
	public List<Note> findAllById(long id, String value);*/
}
