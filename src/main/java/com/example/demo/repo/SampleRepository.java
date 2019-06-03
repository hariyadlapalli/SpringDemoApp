package com.example.demo.repo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Covers;

@Repository
@Transactional
public interface SampleRepository extends MongoRepository<Covers, ObjectId>{

	public List<Covers> findAll();
	
}
