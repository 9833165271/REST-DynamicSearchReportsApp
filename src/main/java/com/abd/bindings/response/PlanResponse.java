package com.abd.bindings.response;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer planId;
	private String planName;
	private String planHolderName;
	private String planHolderSsn;
	private String planStatus;
}
