package org.babyfish.example.federation.r2dbc.employee.loader

import kotlinx.coroutines.flow.toList
import org.babyfish.example.federation.r2dbc.employee.Employee
import org.babyfish.example.federation.r2dbc.employee.EmployeeRepository
import org.babyfish.example.federation.r2dbc.employee.common.AbstractBatchLoader
import org.springframework.stereotype.Component

@Component
open class EmployeeByDepartmentLoader(
    private val employeeRepository: EmployeeRepository
) : AbstractBatchLoader<Long, List<Employee>>() {

    override suspend fun onLoad(
        keys: Set<Long>
    ): Map<Long, List<Employee>> =
        employeeRepository
            .findAllByDepartmentIdIn(keys)
            .toList()
            .groupBy { it.departmentId }
}