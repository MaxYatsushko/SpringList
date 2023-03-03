package com.example.springlist.controller;

import com.example.springlist.Employee;
import com.example.springlist.exceptions.EmployeeAlreadyAddedException;
import com.example.springlist.exceptions.EmployeeNotFoundException;
import com.example.springlist.exceptions.EmployeeStorageIsFullException;
import com.example.springlist.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String Hello() {

        return "Впрограмме вы можете удалить, добавить, найти сотрудника";
    }
    @GetMapping("/addEmployee")
    public String addEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {

        boolean result = false;
        try {
            result = employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            return e.getMessage();
        } catch (Exception e){
            return "У нас обед";
        }

        return (result ? "Сотрудник добавлен" : "Добавить не удалось");
    }

    @GetMapping("/removeEmployee")
    public String removeEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {

        boolean result = false;
        try {
            result = employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        } catch (Exception e){
            return "У нас обед";
        }

        return (result ? "Сотрудник удален" : "Удалить не удалось");
    }

    @GetMapping("/findEmployee")
    public String findEmployee(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {

        String result;
        try {
            result = employeeService.getEmployee(firstName, lastName).toString();
        } catch (Exception e){
            return "У нас обед";
        }

        return result;
    }

    @GetMapping("/showEmployees")
    public Employee[] showEmployees() {
        return employeeService.getEmployees();
    }
}