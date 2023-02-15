package com.abd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.abd.bindings.request.SearchRequest;
import com.abd.bindings.response.PlanResponse;
import com.abd.entities.InsuranceEntity;
import com.abd.repositories.InsuranceRepository;


@Service
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	private InsuranceRepository repo;
	
	@Override
	public List<PlanResponse> searchPlans(SearchRequest request) {
		//Sometimes we want to search based in plan name, planStatus, so passing request 
		InsuranceEntity entity = new InsuranceEntity();
		if(request!=null && request.getPlanName()!=null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if(request!=null && request.getPlanStatus()!=null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		Example<InsuranceEntity> of =Example.of(entity);
		List<InsuranceEntity> findAll = repo.findAll();
		
		List<PlanResponse> plans = new ArrayList<PlanResponse>();
		for(InsuranceEntity plan : findAll) {
			PlanResponse presp = new PlanResponse();
			BeanUtils.copyProperties(plan, presp);
			plans.add(presp);
		}
		return plans;
	}

	/*
	 * Dynamic search so no multiple mtd based on search keywords abobve mtd will satisfy all search */
	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanName();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return repo.getPlanStatus();
	}
	
	

}
