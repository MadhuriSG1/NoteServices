package com.api.note.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.note.entity.Label;


public interface LabelRepository extends JpaRepository<Label,Long> {

	

}
