package com.integrador.employee;

import com.integrador.employee.DTO.EmployeeDTO;
import com.integrador.employee.Exception.HandlerResponseException;
import com.integrador.employee.Module.Employee;
import com.integrador.employee.Module.EmployeeFactory.EmployeeFactoryImp;
import com.integrador.employee.Module.TypeEmployee;
import com.integrador.employee.Repository.EmployeeRepository;
import com.integrador.employee.Service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    EmployeeRepository employeeRepository;
    EmployeeService employeeService;
    EmployeeFactoryImp employeeFactory;

    @Before
    public void SetUp(){
        this.employeeRepository = mock(EmployeeRepository.class);
        this.employeeFactory = new EmployeeFactoryImp();
        this.employeeService = new EmployeeService(employeeRepository, employeeFactory);
    }

    @Test
    public void validateCreateEmployee(){
        Employee employee = new Employee(123,"Brian","Steven","3192621119","brian@gmail.com","Carrera Java","Ciudad Java", "10 years", "O+", TypeEmployee.COORDINATOR);
        Employee service = employeeService.create(employee);
        assertTrue((employee.getNameEmployee() != null && employee.getLastNameEmployee() != null && employee.getDni() instanceof Integer));
    }

    @Test(expected = HandlerResponseException.class)
    public void validationDNI(){
        //Arrange
        Employee employee = new Employee(null,"Brian","Steven","3192621119","brian@gmail.com","Carrera Java","Ciudad Java", "10 years", "O+", TypeEmployee.COORDINATOR);
        Employee response = this.employeeService.create(employee);
        //Act and Assert
        assertThrows(ResponseStatusException.class, () -> {
            employeeService.create(employee);
        });
    }

    @Test
    public void testDeleteByIdTrue() {
        Integer dni = 6;
        Employee employee = new Employee(123,"Brian","Steven","3192621119","brian@gmail.com","Carrera Java","Ciudad Java", "10 years", "O+", TypeEmployee.COORDINATOR);
        when(employeeRepository.findById(dni)).thenReturn(Optional.of(employee));
        employeeRepository.save(employee);

        Optional<Employee> foundEmployee = employeeRepository.findById(dni);
        assertNotNull(foundEmployee);

        Boolean isDeleted = employeeService.deleteEmployee(dni);
        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteByIdFalse() {
        Integer dni = 6;
        Integer num = 3;
        Employee employee = new Employee(123,"Brian","Steven","3192621119","brian@gmail.com","Carrera Java","Ciudad Java", "10 years", "O+", TypeEmployee.COORDINATOR);
        when(employeeRepository.findById(num)).thenReturn(Optional.of(employee));
        employeeRepository.save(employee);

        Optional<Employee> foundClient = employeeRepository.findById(num);
        assertNotNull(foundClient);

        Boolean isDeleted = employeeService.deleteEmployee(dni);
        assertFalse(isDeleted);
    }

    @Test
    public void testGetEmployee(){
        int dni = 7;
        Employee employee = new Employee(dni,"Brian","Steven","3192621119","brian@gmail.com","Carrera Java","Ciudad Java", "10 years", "O+", TypeEmployee.COORDINATOR);

        when(employeeRepository.findById(dni)).thenReturn(Optional.of(employee));

        Optional<EmployeeDTO> result = employeeService.getCustomer(dni);

        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni().intValue());
        assertEquals(employee.getNameEmployee(), result.get().getNameEmployee());
        assertEquals(employee.getLastNameEmployee(), result.get().getLastNameEmployee());
        assertEquals(employee.getPhoneEmployee(), result.get().getPhoneEmployee());
        assertEquals(employee.getEmailEmployee(), result.get().getEmailEmployee());
        assertEquals(employee.getResidencyAddressEmployee(), result.get().getResidencyAddressEmployee());
        assertEquals(employee.getCityLocationEmployee(), result.get().getCityLocationEmployee());
        assertEquals(employee.getLengthServiceEmployee(), result.get().getLengthServiceEmployee());
        assertEquals(employee.getRhEmployee(), result.get().getRhEmployee());
        assertEquals(employee.getTypeEmployee(), result.get().getTypeEmployee());
    }

    @Test
    public void testResearchByIdSuccess(){
        int dni = 7;
        Employee employee = new Employee(dni,"Brian","Steven","3192621119","brian@gmail.com","Carrera Java","Ciudad Java", "10 years", "O+", TypeEmployee.COORDINATOR);

        when(employeeRepository.findById(dni)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.researchById(dni);

        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni().intValue());
        assertEquals(employee.getNameEmployee(), result.get().getNameEmployee());
        assertEquals(employee.getLastNameEmployee(), result.get().getLastNameEmployee());
        assertEquals(employee.getPhoneEmployee(), result.get().getPhoneEmployee());
        assertEquals(employee.getEmailEmployee(), result.get().getEmailEmployee());
        assertEquals(employee.getResidencyAddressEmployee(), result.get().getResidencyAddressEmployee());
        assertEquals(employee.getCityLocationEmployee(), result.get().getCityLocationEmployee());
        assertEquals(employee.getLengthServiceEmployee(), result.get().getLengthServiceEmployee());
        assertEquals(employee.getRhEmployee(), result.get().getRhEmployee());
        assertEquals(employee.getTypeEmployee(), result.get().getTypeEmployee());
    }

}
