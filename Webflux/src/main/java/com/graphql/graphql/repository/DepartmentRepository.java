package com.graphql.graphql.repository;

import com.graphql.graphql.model.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {
}
