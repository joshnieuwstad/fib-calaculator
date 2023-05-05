package com.joshnieuwstad.fibapi.repository;

import org.springframework.stereotype.Repository;

import com.joshnieuwstad.fibapi.Entity.Index;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface IndexRepository extends CrudRepository<Index, Integer> {
    
}
