package com.graphql.graphql.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddEmployeeInput {

    private String name, salary;
    private Integer departmentId;
}
