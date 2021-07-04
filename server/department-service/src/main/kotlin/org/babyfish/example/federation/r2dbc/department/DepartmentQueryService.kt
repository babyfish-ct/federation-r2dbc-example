package org.babyfish.example.federation.r2dbc.department

import com.expediagroup.graphql.server.operations.Query
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service

@Service
open class DepartmentQueryService(
    private val departmentRepository: DepartmentRepository
) : Query {

    open suspend fun findDepartments(): List<Department> =
        departmentRepository
            .findAll()
            .toList()

    open suspend fun findDepartmentByName(name: String): Department? =
        departmentRepository.findByName(name)
}