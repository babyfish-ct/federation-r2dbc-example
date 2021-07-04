package org.babyfish.example.federation.r2dbc.department

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface DepartmentRepository: CoroutineCrudRepository<Department, Long> {

    suspend fun findByName(name: String): Department?
}