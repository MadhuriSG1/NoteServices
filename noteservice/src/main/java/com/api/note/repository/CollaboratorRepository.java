package com.api.note.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.note.entity.Collaborator;


public interface CollaboratorRepository  extends JpaRepository<Collaborator,Long> {
	@Query(value="select noteid from collaborator where id=:id", nativeQuery=true)
	List<Long> findAllById(@Param("id")Long id);
	

}
