package com.sevvalislekter.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.sevvalislekter.dto.EmployeeDTO;
import com.sevvalislekter.entity.EmployeeEntity;
import com.sevvalislekter.repository.EmployeeRepository;
import com.sevvalislekter.services.EmployeeServiceProfile;

@Service
public class EmployeeServiceProfileImpl implements EmployeeServiceProfile {
	
	private final EmployeeRepository employeeRepository;
	public EmployeeServiceProfileImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}
	
    @Override
    public EmployeeDTO ShowProfileForUser(String randomCode) {
        System.out.println("Aranan randomCode: " + randomCode);
        EmployeeEntity employeeEntity = employeeRepository.findByRandomCode(randomCode.trim());
        System.out.println("Bulunan employee: " + 
            (employeeEntity != null ? employeeEntity.getId() : "Yok"));
        if(employeeEntity==null) {
        	return null;
        }
        EmployeeDTO dto=new EmployeeDTO();
        BeanUtils.copyProperties(employeeEntity, dto);
        return dto;
    }

}
