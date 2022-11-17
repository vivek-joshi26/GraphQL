package com.graphql.graphql.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSalaryInput {

    private Integer employeeId;
    private String salary;
}
