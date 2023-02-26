package com.abd.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abd.bindings.request.SearchRequest;
import com.abd.bindings.response.PlanResponse;
import com.abd.reports.ExcelReportGenerator;
import com.abd.reports.PdfReportGenerator;
import com.abd.service.InsuranceService;

@CrossOrigin("*")
@RestController
public class InsuranceRestController {
	
	@Autowired
	private InsuranceService service;
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=plans.pdf";
        response.setHeader(headerKey, headerValue);
        
        List<PlanResponse> plans = service.searchPlans(null);
        PdfReportGenerator pdfReport = new PdfReportGenerator();
        pdfReport.exportPdf(plans, response);
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		
		response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
		
		
		List<PlanResponse> plans = service.searchPlans(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.export(plans, response);
	}
	
	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> getPlans(@RequestBody SearchRequest request) {
		List<PlanResponse> searchPlans = service.searchPlans(request);
		 return new ResponseEntity<>(searchPlans, HttpStatus.OK);	
	}
	
	@GetMapping("/plannames")
	public ResponseEntity<List<String>>getPlanNames(){
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<>(planNames,HttpStatus.OK);
	}
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>>getPlanStatus(){
		List<String> planStatuses = service.getUniquePlanStatuses();
		return new ResponseEntity<>(planStatuses,HttpStatus.OK);
	}
}
