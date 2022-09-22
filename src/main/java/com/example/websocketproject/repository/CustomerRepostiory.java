package com.example.websocketproject.repository;

import com.example.websocketproject.entity.user;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepostiory extends MongoRepository<user,String> {

}
