package org.babyfish.example.federation.r2dbc.employee

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux

/*
 * See "@EnableR2dbcRepositories" on App
 */
interface EmployeeRepository : R2dbcRepository<Employee, Long> {

    fun findAllByDepartmentIdIn(
        departmentIds: Collection<Long>
    ): Flux<Employee>
}