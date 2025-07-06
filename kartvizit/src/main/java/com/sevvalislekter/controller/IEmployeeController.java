package com.sevvalislekter.controller;

import com.sevvalislekter.dto.DTOEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;


public interface IEmployeeController {

	public DTOEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU);
	
}
