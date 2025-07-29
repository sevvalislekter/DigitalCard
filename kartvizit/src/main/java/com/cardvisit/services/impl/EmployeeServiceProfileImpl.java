package com.cardvisit.services.impl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cardvisit.dto.EmployeeDTO;
import com.cardvisit.entity.EmployeeEntity;
import com.cardvisit.repository.EmployeeRepository;
import com.cardvisit.services.EmployeeServiceProfile;
@Service
public class EmployeeServiceProfileImpl implements EmployeeServiceProfile {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceProfileImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}
    @Override
    public EmployeeDTO ShowProfileForUser(String randomCode) {
        EmployeeEntity employeeEntity = employeeRepository.findByRandomCode(randomCode.trim());
        if(employeeEntity==null) {
        	return null;
        }
        EmployeeDTO dto=new EmployeeDTO();
        BeanUtils.copyProperties(employeeEntity, dto);
        return dto;
    }
}