package org.babyfish.example.federation.r2dbc.employee.loader

import com.expediagroup.graphql.server.execution.DataLoaderRegistryFactory
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.dataloader.MappedBatchLoader
import org.springframework.stereotype.Component

/*
 * The most important benefit for DataLoader is
 * to resolve the "N + 1" query problem implicitly
 * because async loading is easy to be combined together.
 */
@Component
open class DataLoaderRegistryFactoryImpl(
    private val mappedBatchLoaders: List<MappedBatchLoader<*, *>>
): DataLoaderRegistryFactory {

    override fun generate(): DataLoaderRegistry =
        DataLoaderRegistry().apply {
            for (mappedBatchLoader in mappedBatchLoaders) {
                register(
                    mappedBatchLoader::class.simpleName,
                    DataLoader.newMappedDataLoader(mappedBatchLoader)
                )
            }
        }
}