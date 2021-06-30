package org.babyfish.example.federation.r2dbc.employee

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.federation.directives.FieldSet
import com.expediagroup.graphql.generator.federation.directives.KeyDirective
import org.springframework.data.annotation.Id

@KeyDirective(fields = FieldSet("id"))
data class Employee(
    @Id val id: Long,
    val name: String,
    @GraphQLIgnore val departmentId: Long,
) {

    /*
     * many-to-one
     */
    fun department(): Department =
        Department(departmentId)
}