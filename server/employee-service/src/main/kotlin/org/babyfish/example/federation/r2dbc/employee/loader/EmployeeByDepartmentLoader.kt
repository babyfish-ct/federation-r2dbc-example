package org.babyfish.example.federation.r2dbc.employee.loader

import org.babyfish.example.federation.r2dbc.employee.Employee
import org.babyfish.example.federation.r2dbc.employee.EmployeeRepository
import org.dataloader.MappedBatchLoader
import org.springframework.stereotype.Component
import java.util.concurrent.CompletionStage

@Component
open class EmployeeByDepartmentLoader(
    private val employeeRepository: EmployeeRepository
) : MappedBatchLoader<Long, List<Employee>> {
    override fun load(
        keys: Set<Long>
    ): CompletionStage<Map<Long, List<Employee>>> =
        employeeRepository
            .findAllByDepartmentIdIn(keys)
            .collectList()
            .toFuture()
            .thenApply { list ->
                list
                    .groupBy {
                        it.departmentId
                    }
                    .defaultValueForKeys(
                        emptyList(),
                        keys
                    )
            }
}