package com.abd.service;

import java.util.List;

import com.abd.bindings.request.SearchRequest;
import com.abd.bindings.response.PlanResponse;


public interface InsuranceService {
	
	public List<PlanResponse> searchPlans(SearchRequest insEntity);
	
	/*Dynamic search so creating single mtd so that in future we can add new search option without adding new methods
	 * 
	 * 
	 * */
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatuses();
	
	
}
