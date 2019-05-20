package com.korera.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korera.domain.Title;

@Repository
public interface TitleDao extends JpaRepository<Title, Long>{

}
