package org.babyfish.example.federation.r2dbc.employee.common

import graphql.schema.DataFetchingEnvironment
import kotlinx.coroutines.future.await
import org.dataloader.DataLoader
import org.dataloader.MappedBatchLoader
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass

fun <
    K, V, L: MappedBatchLoader<K, V>
> DataFetchingEnvironment.load(
    key: K,
    mappedBatchLoaderType: KClass<L>
): CompletableFuture<V> =
    getDataLoader<K, V>(mappedBatchLoaderType.simpleName)
        .load(key)