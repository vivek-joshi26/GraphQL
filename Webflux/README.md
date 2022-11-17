Spring Boot GraphQL Example

<br>
Dashboard can be accessed on localhost:8080/graphiql

<br>
Mutation example for creating an employee-
<br>

mutation{
addEmployee(addEmployeeInput :{name:"Vivek Joshi2",salary :"30000",departmentId:3 }){
id,name,salary,departmentId
}
}

<br>

Query example for getting all the employees for a given name
<br>

query{
employeeByName(employeeName: "Vivek Joshi"){
name, salary, departmentId
}
}