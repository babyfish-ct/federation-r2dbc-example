package org.babyfish.example.federation.r2dbc.employee.federation

import com.expediagroup.graphql.generator.federation.execution.FederatedTypeResolver
import graphql.schema.DataFetchingEnvironment
import org.babyfish.example.federation.r2dbc.employee.Department
import org.springframework.stereotype.Component

/*
 * If Apollo gateway execute
 * `
 * findDepartments {
 *      id
 *      name
 *      employees {
 *          name
 *      }
 * }
 * `,
 * it uses "department-service" to retrieve department.name
 * it also uses "employee-service"(this service) to retrieve department.employees
 * so this federation is necessary
 */
@Component
open class DepartmentFederatedResolver : FederatedTypeResolver<Department> {

    override val typeName: String
        get() = Department::class.simpleName!!

    override suspend fun resolve(
        environment: DataFetchingEnvironment,
        representations: List<Map<String, Any>>
    ): List<Department?> {
        val ids = representations.map{ (it["id"] as Number).toLong() }
        return ids.map { Department(it) }
    }
}