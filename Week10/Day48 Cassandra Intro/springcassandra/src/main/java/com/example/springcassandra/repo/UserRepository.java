package com.example.springcassandra.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.springcassandra.model.User;

public interface UserRepository extends CassandraRepository<User, String> {
    // Custom query methods can be defined here, e.g.,
    // List<User> findByFirstName(String firstName);
}
