package com.api.note.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.note.entity.Label;


public interface LabelRepository extends CrudRepository<Label,Long> {

}
