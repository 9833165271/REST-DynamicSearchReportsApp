package com.abd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abd.entities.InsuranceEntity;


public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Integer> {
	
	//query to get distinct dropdown at ui side
	@Query("Select distinct planName from InsuranceEntity")
	public List<String>getPlanName();
//	public List<String>findByplanName(String planName);
	
	//query to get distinct dropdown at ui side
	@Query("Select distinct planStatus from InsuranceEntity")
	public List<String>getPlanStatus();
	
//	public List<String>findByplanStatus(String planStatus);
	
}
