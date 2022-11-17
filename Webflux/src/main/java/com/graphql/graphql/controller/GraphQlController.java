package com.graphql.graphql.controller;

import com.graphql.graphql.model.DTO.AddEmployeeInput;
import com.graphql.graphql.model.DTO.UpdateSalaryInput;
import com.graphql.graphql.model.Department;
import com.graphql.graphql.model.Employee;
import com.graphql.graphql.repository.DepartmentRepository;
import com.graphql.graphql.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class GraphQlController {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;


    public GraphQlController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }


    //@SchemaMapping(typeName = "Mutation", field = "addEmployee") // similar to @GetMapping
    @MutationMapping
    public Mono<Employee> addEmployee(@Argument AddEmployeeInput addEmployeeInput) {
        Employee employee = new Employee();
        employee.setName(addEmployeeInput.getName());
        employee.setDepartmentId(addEmployeeInput.getDepartmentId());
        employee.setSalary(addEmployeeInput.getSalary());
        return this.employeeRepository.save(employee);
    }

    @MutationMapping
    public Mono<Employee> updateSalary(@Argument UpdateSalaryInput updateSalaryInput){

        return this.employeeRepository.findById(updateSalaryInput.getEmployeeId()).flatMap( employee -> {
            employee.setSalary(updateSalaryInput.getSalary());
            return this.employeeRepository.save(employee);
        });

    }


    @QueryMapping
    public Flux<Employee> employeeByName(@Argument String employeeName) {
        return this.employeeRepository.getEmployeeByName(employeeName);
    }

    @QueryMapping
    public Flux<Department> allDepartment(){
        return this.departmentRepository.findAll();
    }

    // to get all the employees that are present in the department
//    @SchemaMapping(typeName = "Department", field = "employees")
//    public Flux<Employee> employees(Department department){
//        log.info("department: " + department.getId());
//        return this.employeeRepository.getAllEmployeeByDepartmentId(department.getId());
//    }

    // other way for above
    @BatchMapping
    public Mono<Map<Department, Collection<Employee>>> employees(List<Department> departments){
        return Flux.fromIterable(departments)
                .flatMap(department -> this.employeeRepository.getAllEmployeeByDepartmentId(department.getId()))
                .collectMultimap(employee -> departments.stream().filter(department -> department.getId().equals(employee.getDepartmentId())).findFirst().get());
    }

    // Getting multiple records in real time, whenever data is available
    @SubscriptionMapping
    public Flux<Employee> allEmployee(){
        return this.employeeRepository.findAll().delayElements(Duration.ofSeconds(3));
    }

}
