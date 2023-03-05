package com.example.springlist.controller;

import com.example.springlist.Employee;
import com.example.springlist.exceptions.EmployeeAlreadyAddedException;
import com.example.springlist.exceptions.EmployeeNotFoundException;
import com.example.springlist.exceptions.EmployeeStorageIsFullException;
import com.example.springlist.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public String handleException(EmployeeStorageIsFullException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException(EmployeeAlreadyAddedException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException(EmployeeNotFoundException e) {
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @GetMapping
    public String Hello() {
        return "В программе вы можете удалить, добавить, найти сотрудника";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //методы для работы с массивами
    @GetMapping("/addEmployee")
    public Employee addEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }
    @GetMapping("/removeEmployee")
    public Employee removeEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }
    @GetMapping("/findEmployee")
    public Employee findEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.getEmployee(firstName, lastName);
    }
    @GetMapping("/showEmployees")
    public Employee[] showEmployees() {
        return employeeService.getEmployees();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //методы для работы с листом
    @GetMapping("/addEmployeeList")
    public Employee addEmployeeList(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.addEmployeeList(firstName, lastName);
    }

    @GetMapping("/removeEmployeeList")
    public Employee removeEmployeeList(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.removeEmployeeList(firstName, lastName);
    }

    @GetMapping("/findEmployeeList")
    public Employee findEmployeeList(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        return employeeService.getEmployeeList(firstName, lastName);
    }

    @GetMapping("/showEmployeesList")
    public List<Employee> showEmployeesList() {
        return employeeService.getEmployeesList();
    }
}