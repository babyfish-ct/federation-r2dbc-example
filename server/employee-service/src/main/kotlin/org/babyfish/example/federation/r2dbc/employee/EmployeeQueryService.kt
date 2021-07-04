package org.babyfish.example.federation.r2dbc.employee

import com.expediagroup.graphql.server.operations.Query
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.stereotype.Service

@Service
class EmployeeQueryService(
    private val employeeRepository: EmployeeRepository
): Query {

    suspend fun findEmployees(): List<Employee> =
        employeeRepository
            .findAll()
            .toList()
}