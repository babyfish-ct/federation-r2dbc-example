package org.babyfish.example.federation.r2dbc.employee

import com.expediagroup.graphql.generator.federation.directives.ExtendsDirective
import com.expediagroup.graphql.generator.federation.directives.ExternalDirective
import com.expediagroup.graphql.generator.federation.directives.FieldSet
import com.expediagroup.graphql.generator.federation.directives.KeyDirective
import graphql.schema.DataFetchingEnvironment
import org.babyfish.example.federation.r2dbc.employee.common.load
import org.babyfish.example.federation.r2dbc.employee.loader.EmployeeByDepartmentLoader
import java.util.concurrent.CompletableFuture

@KeyDirective(fields = FieldSet("id"))
/*
 * Extends Department of "department-service"
 * to add an one-to-many association
 */
@ExtendsDirective
class Department(
    @ExternalDirective val id: Long
) {
    /*
     * one-to-many
     */
    fun employees(
        env: DataFetchingEnvironment
    ): CompletableFuture<List<Employee>> =
        env.load(id, EmployeeByDepartmentLoader::class)
}