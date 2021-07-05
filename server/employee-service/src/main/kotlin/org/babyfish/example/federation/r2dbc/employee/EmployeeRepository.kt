package org.babyfish.example.federation.r2dbc.employee

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface EmployeeRepository : CoroutineCrudRepository<Employee, Long> {

    /*
     * This is not kotlin suspending method, but its returned type
     * is "kotlinx.coroutines.flow.Flow"
     *
     * The document of Spring Data R2DBC says the suspending method
     * returns list can also be supported, like this
     *
     * suspend fun findAllByDepartmentIn(
     *     departmentIds: Collection<Long>
     * ): List<Employee>
     *
     * But after test, it cannot work, is it a bug of Spring Data R2DBC?
     */
    fun findAllByDepartmentIdIn(
        departmentIds: Collection<Long>
    ): Flow<Employee>
}