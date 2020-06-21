package com.tarathep.eco.repository;

import com.tarathep.eco.model.Login;

import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login,Integer>{
    public Iterable<Login> findAllByUsername(String username);

    public Login findByUsername(String username);
}