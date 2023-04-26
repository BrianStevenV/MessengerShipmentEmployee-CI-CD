package com.integrador.employee.Service;

import com.integrador.employee.DTO.EmployeeDTO;
import com.integrador.employee.Exception.HandlerResponseException;
import com.integrador.employee.Module.Employee;
import com.integrador.employee.Module.EmployeeFactory.EmployeeFactoryImp;
import com.integrador.employee.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class EmployeeService {
    private static final Field[] FIELDS = Employee.class.getDeclaredFields();
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeFactoryImp employeeFactoryImp;

    //nuevo
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    //Se cambia tipo Employee por tipo credential
    public Employee create(Employee employee) {
        Employee newEmployee = employeeFactoryImp.createEmployeeImp(employee.getDni(), employee.getNameEmployee(), employee.getLastNameEmployee(), employee.getPhoneEmployee(), employee.getEmailEmployee(), employee.getResidencyAddressEmployee(), employee.getCityLocationEmployee(), employee.getLengthServiceEmployee(), employee.getRhEmployee(), employee.getTypeEmployee());
        if (newEmployee.getDni() != null && newEmployee.getDni().toString().isEmpty()) {
            Optional<Employee> tempCustomer = this.employeeRepository.findById(employee.getDni());
            if (tempCustomer.isPresent()) {
                throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "DNI is rejected in database.");
            }
        }
        //Colocar resto de validaciones... resto de campos por validar
        if (newEmployee.getDni() != null && newEmployee.getDni() instanceof Integer
                && !newEmployee.getNameEmployee().isEmpty() && newEmployee.getNameEmployee() != null
                && !newEmployee.getLastNameEmployee().isEmpty() && newEmployee.getLastNameEmployee() != null) {
            return this.employeeRepository.save(employee);
            //return client;
        } else {
            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "DNI, First Name and Last Name are required");

        }
    }
    public Optional<Employee> updateEmployeeAll(Integer dni, Employee employeeUpdate) {
        Optional<Employee> employeeOptional = employeeRepository.findById(dni);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setNameEmployee(employeeUpdate.getNameEmployee());
            employee.setLastNameEmployee(employeeUpdate.getLastNameEmployee());
            employee.setPhoneEmployee(employeeUpdate.getPhoneEmployee());
            employee.setEmailEmployee(employeeUpdate.getEmailEmployee());
            employee.setResidencyAddressEmployee(employeeUpdate.getResidencyAddressEmployee());
            employee.setCityLocationEmployee(employeeUpdate.getCityLocationEmployee());
            employee.setLengthServiceEmployee(employeeUpdate.getLengthServiceEmployee());
            employee.setRhEmployee(employeeUpdate.getRhEmployee());
            employee.setTypeEmployee(employeeUpdate.getTypeEmployee());
            employeeRepository.save(employee);
            return Optional.of(employee);
        }
        return Optional.empty();
    }

    public Boolean deleteEmployee(Integer dni){
        Optional<Employee> employee = employeeRepository.findById(dni);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Employee> researchById(int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){

            throw new HandlerResponseException(HttpStatus.INTERNAL_SERVER_ERROR,"Employee doesn't find in our database.");
        }
        return employee;
    }

    public Optional<EmployeeDTO> getCustomer(Integer dni) {
        Optional<Employee> employee =researchById(dni);
        if(employee.isPresent()){
            EmployeeDTO employeeDTO = new EmployeeDTO();
            return Optional.of(new EmployeeDTO(
                    employee.get().getDni(),
                    employee.get().getNameEmployee(),
                    employee.get().getLastNameEmployee(),
                    employee.get().getPhoneEmployee(),
                    employee.get().getEmailEmployee(),
                    employee.get().getResidencyAddressEmployee(),
                    employee.get().getCityLocationEmployee(),
                    employee.get().getLengthServiceEmployee(),
                    employee.get().getRhEmployee(),
                    employee.get().getTypeEmployee()));
        }   else{
            return Optional.empty();
        }

    }
    private static String generateRandomAlphanumeric(int length) {
        return IntStream.range(0, length)
                .map(i -> random.nextInt(CHARACTERS.length()))
                .mapToObj(CHARACTERS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
