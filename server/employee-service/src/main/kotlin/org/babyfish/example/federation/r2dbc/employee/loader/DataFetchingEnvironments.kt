package org.babyfish.example.federation.r2dbc.employee.loader

import graphql.schema.DataFetchingEnvironment
import org.dataloader.DataLoader
import org.dataloader.MappedBatchLoader
import kotlin.reflect.KClass

fun <
    K, V, L: MappedBatchLoader<K, V>
> DataFetchingEnvironment.mappedDataLoader(
    mappedBatchLoaderType: KClass<L>
): DataLoader<K, V> =
    getDataLoader(mappedBatchLoaderType.simpleName)