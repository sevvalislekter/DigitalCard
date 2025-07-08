package com.sevvalislekter.controller;

import com.sevvalislekter.dto.DtoEmployee;
import com.sevvalislekter.dto.DtoEmployeeIU;


public interface IEmployeeController {

	public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU);
	
}
