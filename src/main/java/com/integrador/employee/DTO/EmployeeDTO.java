package com.integrador.employee.DTO;

import com.integrador.employee.Module.TypeEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmployeeDTO {

    //Look if all variable is correct and neccesary....
    private Integer dni;
    private String nameEmployee;
    private String lastNameEmployee;
    private String phoneEmployee;
    private String emailEmployee;
    private String residencyAddressEmployee;
    private String cityLocationEmployee;
    private String lengthServiceEmployee;
    private String rhEmployee;
    private TypeEmployee typeEmployee;
}
