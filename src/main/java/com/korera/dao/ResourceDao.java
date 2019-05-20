package com.korera.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korera.domain.Resource;

@Repository
public interface ResourceDao extends JpaRepository<Resource, Long>{

}
