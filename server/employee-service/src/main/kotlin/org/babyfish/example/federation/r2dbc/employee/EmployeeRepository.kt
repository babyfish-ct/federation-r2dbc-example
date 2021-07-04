package org.babyfish.example.federation.r2dbc.employee

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface EmployeeRepository : CoroutineCrudRepository<Employee, Long> {

    fun findAllByDepartmentIdIn(
        departmentIds: Collection<Long>
    ): Flow<Employee>
}