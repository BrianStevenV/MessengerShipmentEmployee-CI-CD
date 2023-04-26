package com.integrador.employee.Controller;

import com.integrador.employee.DTO.EmployeeDTO;
import com.integrador.employee.Module.Employee;
import com.integrador.employee.Service.EmployeeService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api(value="---", description = "This is controller to access the service of Employee")
public class EmployeeController {
    //Confirm if @Autowired is running
    private EmployeeService employeeService;
    @ApiResponses(value={
            @ApiResponse( code = 201, message = "created Employee success"),
            @ApiResponse( code = 404, message = "DNI, Name or Surnames is incorrect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="employee", notes= "this create an Employee and save in the database", response = Employee.class)
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee register(@ApiParam(value = "Employee object", required = true) @RequestBody Employee employeeCreated){ return employeeService.create(employeeCreated);}

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "update Employee success"),
            @ApiResponse( code = 404, message = "Fields incorrect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="employee", notes= "this update all Employee and save in the database", response = Employee.class)
    @PutMapping("/employee/all/{dni}")
    public ResponseEntity<Employee> updateEmployeeAllData(@ApiParam(value = "DNI Employee", required = true)@PathVariable Integer dni, @ApiParam(value = "Employee object", required = true)@RequestBody Employee employeeToUpdate) {
        Optional<Employee> client = employeeService.updateEmployeeAll(dni, employeeToUpdate);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "delete Employee success"),
            @ApiResponse( code = 404, message = "DNI Employee incorect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="employee", notes= "this delete an Employee and delete in the database", response = String.class)
    @DeleteMapping("/employee/{dni}")
    public ResponseEntity<String> deleteEmployee(@ApiParam(value = "DNI Employee", required = true) @PathVariable Integer dni){
        boolean success = employeeService.deleteEmployee(dni);
        if(success){
            return ResponseEntity.ok().body("The Employee with DNI: " + dni + " had been eliminated.");
        }   else{
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponses(value={
            @ApiResponse( code = 201, message = "Get Employee success"),
            @ApiResponse( code = 404, message = "DNI Employee incorect."),
            @ApiResponse( code = 500, message ="That's an internal error"),
    })
    @ApiOperation(value="employee", notes= "this get an  Employee from the database", response = EmployeeDTO.class)
    @GetMapping("/employee/{dni}")
    public Optional<EmployeeDTO> getClientId(@ApiParam(value = "DNI Employee", required = true) @PathVariable Integer dni){
        return this.employeeService.getCustomer(dni);
    }

}
