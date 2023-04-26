package com.integrador.employee.Module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
@ApiModel(description ="this model represent the Employee data.")
public class Employee implements Serializable {
    @ApiModelProperty(value = "DNI Employee", example ="15323")
    @Id
    @Column(name = "Dni_Employee")
    private Integer dni;
    @ApiModelProperty(value = "Name Employee", example ="Steven")
    @Column(name = "Name_Employee")
    private String nameEmployee;
    @ApiModelProperty(value = "Surname Employee", example ="Ortiz")
    @Column(name = "LastName_Employee")
    private String lastNameEmployee;
    @ApiModelProperty(value = "Phone Employee", example ="1002003004")
    @Column(name = "Phone_Employee")
    private String phoneEmployee;
    @ApiModelProperty(value = "Email Employee", example ="ortiz@gmail.com")
    @Column(name = "Email_Employee")
    private String emailEmployee;
    @ApiModelProperty(value = "Residency Address Employee", example ="Street Banana")
    @Column(name = "ResidencyAddress_Employee")
    private String residencyAddressEmployee;
    @ApiModelProperty(value = "City Location Employee", example ="London")
    @Column(name = "CityLocation_Employee")
    private String cityLocationEmployee;
    @ApiModelProperty(value = "Length Service Employee", example ="34 years")
    @Column(name = "LengthService_Employee")
    private String lengthServiceEmployee;
    @ApiModelProperty(value = "RH Employee", example ="O+")
    @Column(name = "Rh_Employee")
    private String rhEmployee;
    @ApiModelProperty(value = "Type Employee", example ="COORDINATOR")
    @Column(name = "Type_Employee")
    private TypeEmployee typeEmployee;
}
