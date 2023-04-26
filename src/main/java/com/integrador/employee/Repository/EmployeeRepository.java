package com.integrador.employee.Repository;

import com.integrador.employee.Module.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
