package com.integrador.employee.Module.EmployeeFactory;

import com.integrador.employee.Module.Employee;
import com.integrador.employee.Module.TypeEmployee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeFactoryImp {
    public Employee createEmployeeImp(Integer dni, String nameEmployee, String lastNameEmployee, String phoneEmployee, String emailEmployee, String residencyAddressEmployee, String cityLocationEmployee,String lengthServiceEmployee, String rhEmployee, TypeEmployee typeEmployee){
        return new Employee(dni, nameEmployee, lastNameEmployee, phoneEmployee, emailEmployee, residencyAddressEmployee, cityLocationEmployee, lengthServiceEmployee, rhEmployee, typeEmployee);
    }
}
