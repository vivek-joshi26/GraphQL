
# Spring Boot + H2 + JPA + GraphQL


## Sample Requests

### Query 

query {
country(id:2014)  {
id
name
language
capital{
id
name
population
}
}
}

query {
city(id: 1012)  {
id
name
}
}

query {
citiesByName(name: "Dubai")  {
id
name
}
}


### Mutation


mutation{
addCity(name: "San Jose", population: 12345){
id
name,
population
}
}


mutation{
addCountry(name: "USA", language: "English"){
id
name,
language
}
}


mutation{
addCountryWithCapital(name: "Australia", language: "English", capital: {id: 1012}){
id
name,
language
capital{
id
name
population
}
}
}

mutation{
setCapital(countryId: 1, cityId: 1){
id
name,
language
capital{
id
population
name
}
}
}