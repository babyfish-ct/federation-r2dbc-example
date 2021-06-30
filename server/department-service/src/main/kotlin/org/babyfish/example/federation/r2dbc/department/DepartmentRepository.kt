package org.babyfish.example.federation.r2dbc.department

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

/*
 * See "@EnableR2dbcRepositories" on App
 */
interface DepartmentRepository: R2dbcRepository<Department, Long> {

    fun findByName(name: String): Mono<Department>
}