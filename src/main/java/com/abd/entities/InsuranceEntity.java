package com.abd.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="INSURANCE_PLANS")
public class InsuranceEntity {
	
	
//@GeneratedValue HERE WE DONT NEED GENERATED COS WE R NOT SAVING RECORDS,ONLY RETRIEVE
	@Id
	@Column(name="PLAN_ID")
	private Integer planId;
	
	@Column(name="PLANE_NAME")
	private String planName;
	
	@Column(name="PLAN_HOLDER_NAME")
	private String planHolderName;
	
	@Column(name="PLAN_HOLDER_SSN")
	private String planHolderSsn;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
}
