package com.example.springlist.service;

import com.example.springlist.Employee;
import com.example.springlist.exceptions.EmployeeAlreadyAddedException;
import com.example.springlist.exceptions.EmployeeNotFoundException;
import com.example.springlist.exceptions.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {
    private final int MAX_VALUE_ARRAY = 5;
    private final Employee[] employees = new Employee[MAX_VALUE_ARRAY];
    private final List<Employee> employeesList = new ArrayList<>();
    private final int flagEmptyIndex = -1;

    //методы для массива
    public Employee addEmployee(String firstName, String lastname){
        Employee localEmployee = new Employee(firstName, lastname);
        int index = findEmployeeIndex(localEmployee);
        if (index != flagEmptyIndex)
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в массиве", 888);

        index = findEmptyIndex();
        if (index == flagEmptyIndex)
            throw new EmployeeStorageIsFullException("К сожалению, отсутствует место для сотрудника", 999);

        employees[index] = localEmployee;
        /* добавление объекта в массив с расширением массива
        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = new Employee(firstName, lastname);*/
        return employees[index];
    }

    public Employee removeEmployee(String firstName, String lastname){

        Employee localEmployee = new Employee(firstName, lastname);

        int index = findEmployeeIndex(localEmployee);
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
        return employees[index];
    }

    public int findEmployeeIndex(String firstName, String lastname){
        for (int i = 0; i < employees.length; i++)
            if(employees[i] != null && employees[i].getFirstName().equals(firstName) && employees[i].getLastName().equals(lastname))
                    return i;
        return flagEmptyIndex;
    }
    public int findEmployeeIndex(Employee employee){
        for (int i = 0; i < employees.length; i++)
            if(employees[i] != null && employees[i].equals(employee))
                return i;
        return flagEmptyIndex;
    }

    public int findEmptyIndex(){
        for (int i = 0; i < employees.length; i++)
            if(employees[i] == null)
                return i;

        return flagEmptyIndex;
    }

    public Employee getEmployee(String firstName, String lastname){
        Employee localEmployee = new Employee(firstName, lastname);
        int index = findEmployeeIndex(localEmployee);

        if(index == flagEmptyIndex)
            return null;

        return employees[index];
    }

    public Employee[] getEmployees(){
        return employees;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //методы для листа
    public Employee addEmployeeList(String firstName, String lastname){
        if (employeesList.size() == MAX_VALUE_ARRAY)
            throw new EmployeeStorageIsFullException("К сожалению, отсутствует место для сотрудника", 999);

        Employee localEmployee = new Employee(firstName, lastname);
        if (employeesList.contains(localEmployee))
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в списке", 888);

        int index = findEmptyIndexList();
        if (index != flagEmptyIndex)
            employeesList.set(index, localEmployee);
        else
            employeesList.add(localEmployee);

        return localEmployee;
    }

    public Employee removeEmployeeList(String firstName, String lastname){

        Employee localEmployee = new Employee(firstName, lastname);
        if (!employeesList.contains(localEmployee))
            throw new EmployeeNotFoundException("Сотрудник для удаления не найден", 555);

        int index = findIndexEmployee(firstName, lastname);
        return employeesList.remove(index);
    }

    public int findIndexEmployee(String firstName, String lastname){
        for (int i = 0; i < employeesList.size(); i++)
            if(employeesList.get(i) != null && employeesList.get(i).getFirstName().equals(firstName) && employeesList.get(i).getLastName().equals(lastname))
                return i;
        return flagEmptyIndex;
    }

    public int findEmptyIndexList(){
        if (!employeesList.contains(null))
            return flagEmptyIndex;

        for (int i = 0; i < employeesList.size(); i++)
            if(employeesList.get(i) == null)
                return i;

        return flagEmptyIndex;
    }

    public Employee getEmployeeList(String firstName, String lastname){
        Employee localEmployee = new Employee(firstName, lastname);
        if (!employeesList.contains(localEmployee))
            throw new EmployeeNotFoundException("Сотрудник не найден", 555);

        for (Employee employee : employeesList)
            if(employee.equals(localEmployee))
                return localEmployee;

        return null;
    }

    public List<Employee> getEmployeesList(){
        return employeesList;
    }
}
