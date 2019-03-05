package com.api.note.repository;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.api.note.entity.Collaborator;


public interface CollaboratorRepository  extends JpaRepository<Collaborator,Long> {
	@Query(value="select noteid from collaborator where id=:id", nativeQuery=true)
	Optional <List<Long>> findAllById(@Param("id")Long id);
	
	@Query(value="select collaboratorid from collaborator where id=:id and noteid=:noteid", nativeQuery=true)
	Optional<Long> findBy(@Param("id")long id,@Param("noteid")long noteid);
	
	@Query(value="select id from collaborator where noteid=:noteid",nativeQuery=true)
	Optional<List<BigInteger>> findAllUsersOfNote (@Param("noteid") long noteid);
	
	
}
