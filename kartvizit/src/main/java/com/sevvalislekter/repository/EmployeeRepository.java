package com.sevvalislekter.repository;

import com.sevvalislekter.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    
    @Query("SELECT e FROM Employee e WHERE LOWER(e.randomCode) = LOWER(:code)")
    Employee findByRandomCodeCaseInsensitive(@Param("code") String code);

    
    
}