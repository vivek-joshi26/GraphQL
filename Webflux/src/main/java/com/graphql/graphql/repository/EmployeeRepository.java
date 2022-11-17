package com.graphql.graphql.repository;

import com.graphql.graphql.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {

    Flux<Employee> getEmployeeByName(String name);
    Employee getEmployeeById(Integer id);

    Flux<Employee> getAllEmployeeByDepartmentId(Integer departmentId);

}
