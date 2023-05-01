package com.integrador.employee.Service;

import com.integrador.employee.DTO.EmployeeDTO;
import com.integrador.employee.Exception.EmployeeNotFoundException;
import com.integrador.employee.Exception.HandlerResponseException;
import com.integrador.employee.Module.Employee;
import com.integrador.employee.Module.EmployeeFactory.EmployeeFactoryImp;
import com.integrador.employee.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.StringJoiner;
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

    public EmployeeDTO create(EmployeeDTO employeeDTO) throws HandlerResponseException {
        try {
            if (anyFieldIsNull(employeeDTO)) {
                throw new HandlerResponseException(HttpStatus.BAD_REQUEST, "The following fields are required: " + missingFields(employeeDTO));
            }
            if (employeeRepository.existsById(employeeDTO.getDni())) {
                throw new HandlerResponseException(HttpStatus.BAD_REQUEST, "DNI isn't available. ");
            }

            Employee employee = new Employee(employeeDTO.getDni(), employeeDTO.getNameEmployee(), employeeDTO.getLastNameEmployee(),
                    employeeDTO.getPhoneEmployee(), employeeDTO.getEmailEmployee(), employeeDTO.getResidencyAddressEmployee(),
                    employeeDTO.getCityLocationEmployee(), employeeDTO.getLengthServiceEmployee(), employeeDTO.getRhEmployee(),
                    employeeDTO.getTypeEmployee());
            employeeRepository.save(employee);
            return employeeDTO;
        } catch (HandlerResponseException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerResponseException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    private boolean anyFieldIsNull(EmployeeDTO employee) {
        return employee.getDni() == null ||
                !StringUtils.hasText(employee.getNameEmployee()) ||
                !StringUtils.hasText(employee.getLastNameEmployee()) ||
                !StringUtils.hasText(employee.getPhoneEmployee()) ||
                !StringUtils.hasText(employee.getEmailEmployee()) ||
                !StringUtils.hasText(employee.getResidencyAddressEmployee()) ||
                !StringUtils.hasText(employee.getCityLocationEmployee()) ||
                !StringUtils.hasText(employee.getLengthServiceEmployee()) ||
                !StringUtils.hasText(employee.getRhEmployee()) ||
                employee.getTypeEmployee() == null;
    }

    private String missingFields(EmployeeDTO employee) {
        StringJoiner joiner = new StringJoiner(", ");
        if (employee.getDni() == null) joiner.add("DNI");
        if (!StringUtils.hasText(employee.getNameEmployee())) joiner.add("Name");
        if (!StringUtils.hasText(employee.getLastNameEmployee())) joiner.add("Last Name");
        if (!StringUtils.hasText(employee.getPhoneEmployee())) joiner.add("Phone");
        if (!StringUtils.hasText(employee.getEmailEmployee())) joiner.add("Email");
        if (!StringUtils.hasText(employee.getResidencyAddressEmployee())) joiner.add("Residency Address");
        if (!StringUtils.hasText(employee.getCityLocationEmployee())) joiner.add("City Location");
        if (!StringUtils.hasText(employee.getLengthServiceEmployee())) joiner.add("Length Service");
        if (!StringUtils.hasText(employee.getRhEmployee())) joiner.add("RH");
        if (employee.getTypeEmployee() == null) joiner.add("Type");
        return joiner.toString();
    }

//    public Optional<Employee> updateEmployeeAll(Integer dni, Employee employeeUpdate) {
//        Optional<Employee> employeeOptional = employeeRepository.findById(dni);
//        if (employeeOptional.isPresent()) {
//            Employee employee = employeeOptional.get();
//            employee.setNameEmployee(employeeUpdate.getNameEmployee());
//            employee.setLastNameEmployee(employeeUpdate.getLastNameEmployee());
//            employee.setPhoneEmployee(employeeUpdate.getPhoneEmployee());
//            employee.setEmailEmployee(employeeUpdate.getEmailEmployee());
//            employee.setResidencyAddressEmployee(employeeUpdate.getResidencyAddressEmployee());
//            employee.setCityLocationEmployee(employeeUpdate.getCityLocationEmployee());
//            employee.setLengthServiceEmployee(employeeUpdate.getLengthServiceEmployee());
//            employee.setRhEmployee(employeeUpdate.getRhEmployee());
//            employee.setTypeEmployee(employeeUpdate.getTypeEmployee());
//            employeeRepository.save(employee);
//            return Optional.of(employee);
//        }
//        return Optional.empty();
//    }

    public Optional<EmployeeDTO> updateEmployee(Integer dni, @RequestBody EmployeeDTO employeeUpdate) {
        if (anyFieldIsNull(employeeUpdate)) {
            throw new HandlerResponseException(HttpStatus.BAD_REQUEST, "The following fields are required: " + missingFields(employeeUpdate));
        }
        Optional<Employee> employee = employeeRepository.findById(dni);
        if (employee.isPresent()) {
            Employee employeeToUpdate = employee.get();
            employeeToUpdate.setNameEmployee(employeeUpdate.getNameEmployee());
            employeeToUpdate.setLastNameEmployee(employeeUpdate.getLastNameEmployee());
            employeeToUpdate.setPhoneEmployee(employeeUpdate.getPhoneEmployee());
            employeeToUpdate.setEmailEmployee(employeeUpdate.getEmailEmployee());
            employeeToUpdate.setResidencyAddressEmployee(employeeUpdate.getResidencyAddressEmployee());
            employeeToUpdate.setCityLocationEmployee(employeeUpdate.getCityLocationEmployee());
            employeeToUpdate.setLengthServiceEmployee(employeeUpdate.getLengthServiceEmployee());
            employeeToUpdate.setRhEmployee(employeeUpdate.getRhEmployee());
            employeeToUpdate.setTypeEmployee(employeeUpdate.getTypeEmployee());

            // Save the updated entity to the database
            Employee updatedEmployee = employeeRepository.save(employeeToUpdate);

            // Convert the updated entity to a DTO and return it
            EmployeeDTO updatedEmployeeDTO = new EmployeeDTO(updatedEmployee.getDni(), updatedEmployee.getNameEmployee(),
                    updatedEmployee.getLastNameEmployee(), updatedEmployee.getPhoneEmployee(), updatedEmployee.getEmailEmployee(),
                    updatedEmployee.getResidencyAddressEmployee(), updatedEmployee.getCityLocationEmployee(),
                    updatedEmployee.getLengthServiceEmployee(), updatedEmployee.getRhEmployee(), updatedEmployee.getTypeEmployee());
            return Optional.of(updatedEmployeeDTO);
        }
        throw new EmployeeNotFoundException(HttpStatus.NOT_FOUND, "Employee with DNI: " + dni + " cannot be found.");
    }


    public Boolean deleteEmployee(Integer dni){
        Optional<Employee> employee = employeeRepository.findById(dni);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return true;
        } else {
            throw new EmployeeNotFoundException(HttpStatus.NOT_FOUND, "Employee with DNI: " + dni + " cannot be found.");
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
