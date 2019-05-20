package com.korera.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korera.domain.Data;
import java.lang.Long;
import java.util.List;

@Repository
public interface DataDao extends JpaRepository<Data, Long>{
	
	List<Data> findByProjectIdAndResourceIdAndTitleId(Long projectId, Long resourceId, Long titleId);

}
