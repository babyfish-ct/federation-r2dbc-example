package org.babyfish.example.federation.r2dbc.department

import com.expediagroup.graphql.generator.federation.FederatedSchemaGeneratorHooks
import com.expediagroup.graphql.generator.federation.execution.FederatedTypeResolver
import com.expediagroup.graphql.generator.hooks.SchemaGeneratorHooks
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories
@SpringBootApplication
open class App {

    @Bean
    open fun hooks(
        federatedTypeResolvers: List<FederatedTypeResolver<*>>
    ): SchemaGeneratorHooks =
        FederatedSchemaGeneratorHooks(
            federatedTypeResolvers
        )

    @Bean
    open fun connectionFactoryInitializer(
        connectionFactory: ConnectionFactory
    ): ConnectionFactoryInitializer =
        ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory)
            setDatabasePopulator(
                ResourceDatabasePopulator(
                    ClassPathResource("database.sql")
                )
            )
        }
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}