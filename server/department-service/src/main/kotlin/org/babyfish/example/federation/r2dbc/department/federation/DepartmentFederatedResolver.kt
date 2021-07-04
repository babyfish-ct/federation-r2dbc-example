package org.babyfish.example.federation.r2dbc.department.federation

import com.expediagroup.graphql.generator.federation.execution.FederatedTypeResolver
import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.babyfish.example.federation.r2dbc.department.Department
import org.babyfish.example.federation.r2dbc.department.DepartmentRepository
import org.springframework.stereotype.Component

/*
 * If Apollo gateway execute
 * `
 * findEmployees {
 *      name {
 *          departments {
 *              id
 *              name
 *          }
 *      }
 * }
 * `,
 * it uses "department-service"(this service) to retrieve department.name
 * so this federation is necessary
 */
@Component
open class DepartmentFederatedResolver(
    private val departmentRepository: DepartmentRepository
) : FederatedTypeResolver<Department> {

    override val typeName: String
        get() = Department::class.simpleName!!

    override suspend fun resolve(
        environment: DataFetchingEnvironment,
        representations: List<Map<String, Any>>
    ): List<Department?> {
        val ids = representations.map{ (it["id"] as Number).toLong() }
        val departmentMap =
            departmentRepository
                .findAllById(ids.distinct())
                .toList()
                .associateBy { it.id }
        return ids.map { departmentMap[it] }
    }
}