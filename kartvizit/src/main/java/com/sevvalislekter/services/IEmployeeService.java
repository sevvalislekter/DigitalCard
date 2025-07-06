package com.sevvalislekter.services;

import com.sevvalislekter.dto.DTOEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;


public interface IEmployeeService {
	
	public DTOEmployee saveEmployee(DtoEmployeeIU employee);
	
	

}
