package com.example.springlist.service;

import com.example.springlist.Employee;
import com.example.springlist.exceptions.EmployeeAlreadyAddedException;
import com.example.springlist.exceptions.EmployeeNotFoundException;
import com.example.springlist.exceptions.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmployeeService {
    private final Employee[] employees = new Employee[5];
    private final int flagEmptyIndex = -1;

    public boolean addEmployee(String firstName, String lastname){
        int index = findEmployee(firstName, lastname);
        if (index != flagEmptyIndex)
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в массиве", 888);

        index = findEmptyIndex(firstName, lastname);
        if (index == flagEmptyIndex)
            throw new EmployeeStorageIsFullException("К сожалению, отсутствует место для сотрудника", 999);

        employees[index] = new Employee(firstName, lastname);
        /* добавление объекта в массив с расширением массива
        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = new Employee(firstName, lastname);*/

        return true;
    }

    public boolean removeEmployee(String firstName, String lastname){

        int index = findEmployee(firstName, lastname);
        if(index == flagEmptyIndex)
            throw new EmployeeNotFoundException("Сотрудник для удаления не найден", 555);

        employees[index] = null;

        /* удаление и смещение элементов массива вправо с уменьшением длины
        for (int i = 0; i < employees.length - 1; i++) {
            if(employees[i] == null){
                employees[i] = employees[i+1];
                employees[i+1] = null;
            }
        }
        employees = Arrays.copyOf(employees, employees.length - 1);*/
        return true;
    }

    public int findEmployee(String firstName, String lastname){
        for (int i = 0; i < employees.length; i++)
            if(employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastname))
                    return i;
        return flagEmptyIndex;
    }

    public int findEmptyIndex(String firstName, String lastname){
        for (int i = 0; i < employees.length; i++)
            if(employees[i] == null)
                return i;

        return flagEmptyIndex;
    }

    public String getEmployee(String firstName, String lastname){
        int index = findEmployee(firstName, lastname);

        if(index == flagEmptyIndex)
            return "Сотрудник не был найден";
        return employees[index].toString();
    }

    public Employee[] getEmployees(){
        return employees;
    }
}
