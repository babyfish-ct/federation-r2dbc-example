# federation-r2dbc-example

This is an example about

1. How to use [apollo-federation](https://www.apollographql.com/docs/federation/) by [graphql-kotlin](https://github.com/ExpediaGroup/graphql-kotlin). 
2. How to use [r2dbc](https://r2dbc.io/)

## Start department-service

1. cd ${project_home}/server/department-service
2. mvn spring-boot:run
3. access "http://localhost:4001/playground".
   Open the document drawer of right side, then you can the definition of "Department":
   ![ImageText](https://github.com/babyfish-ct/federation-r2dbc-example/blob/master/department-service.png) 

## Start employee-service

1. cd ${project_home}/server/employee-service
2. mvn spring-boot:run
3. access "http://localhost:4002/playground".
   Open the document drawer of right side, then you can the definition of "Department":
   ![ImageText](https://github.com/babyfish-ct/federation-r2dbc-example/blob/master/employee-service.png) 

## Start gateway

1. cd ${project_home}/gateway
2. yarn install
3. yarn start
4. access "http://localhost:4000/graphql".
   Open the document drawer of right side, then you can the definition of "Department":
   ![ImageText](https://github.com/babyfish-ct/federation-r2dbc-example/blob/master/gateway.png)
5. Try to execute this query
```
query {
  queryParentAndFetchChild: findDepartments {
    id
    name 
    employees {
      name
    }
  }
  queryChildAndFetchParent: findEmployees {
    id
    name
    department {
      name
    }
  }
}
```
