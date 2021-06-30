package org.babyfish.example.federation.r2dbc.department

import com.expediagroup.graphql.generator.federation.directives.FieldSet
import com.expediagroup.graphql.generator.federation.directives.KeyDirective
import org.springframework.data.annotation.Id

@KeyDirective(fields = FieldSet("id"))
data class Department(
    @Id val id: Long,
    val name: String,
)