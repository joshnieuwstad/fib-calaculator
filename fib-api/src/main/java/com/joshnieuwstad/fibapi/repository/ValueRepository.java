package com.joshnieuwstad.fibapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joshnieuwstad.fibapi.Entity.Value;

@Repository
public interface ValueRepository extends CrudRepository<Value, Integer> {

}
