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

	@Query(value="select * from note where noteid IN (:ids)",nativeQuery=true)
	List<Note> findAllCollaboratorNotes(@Param("ids") List<Long> allNotesId);
	
}
