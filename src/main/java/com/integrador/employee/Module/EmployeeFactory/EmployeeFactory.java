package com.integrador.employee.Module.EmployeeFactory;

import com.integrador.employee.Module.Employee;
import com.integrador.employee.Module.TypeEmployee;


public interface EmployeeFactory {
    public Employee createEmployee(Integer dni, String nameEmployee, String lastNameEmployee, String phoneEmployee, String emailEmployee, String residencyAddressEmployee, String cityLocationEmployee,String lengthServiceEmployee, String rhEmployee, TypeEmployee typeEmployee);
}
