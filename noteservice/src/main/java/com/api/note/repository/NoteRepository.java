package com.api.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.api.note.entity.Note;
public interface NoteRepository extends CrudRepository<Note,Long> {
//	@Query(value="select * from note where user_id=:id AND is_archive=:isArchive AND is_trash=:isTrash",nativeQuery=true)
//	Optional<List<Note>> findAllById(@Param("id") long id,@Param("isArchive") boolean isArchive,@Param("isTrash") boolean isTrash );
	@Query(value="select * from note where user_id=:id AND is_archive=:isArchive AND is_trash=:isTrash",nativeQuery=true)
	Optional<List<Note>> findAllByStatus(@Param("id")long id, @Param("isArchive") Boolean isArchive, @Param("isTrash") Boolean isTrash);

	/*@Query(value="SELECT * FROM note t WHERE t.userid = :id", nativeQuery=true)
	List<Note> findAllById(@Param("id") long id);
*/
	
	/* @Query("SELECT t FROM Todo t WHERE t.title = 'title'")
	    public List<Note> findById();*/
	/*@Query("SELECT n FROM Note n WHERE n.value = 'value'")
	public List<Note> findAllById(long id, String value);*/
}
