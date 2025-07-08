package com.sevvalislekter.services;

import com.sevvalislekter.dto.DtoEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;


public interface IEmployeeService {
	
	public DtoEmployee saveEmployee(DtoEmployeeIU employee);
	
	

}
