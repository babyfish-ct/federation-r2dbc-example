package org.babyfish.example.federation.r2dbc.employee.common

import graphql.schema.DataFetchingEnvironment
import org.dataloader.MappedBatchLoader
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass

fun <K, V, L: MappedBatchLoader<K, V>> DataFetchingEnvironment.required(
    mappedBatchLoaderType: KClass<L>,
    key: K,
    defaultValueGetter: () -> V = { error("") }
): CompletableFuture<V> =
    optional(mappedBatchLoaderType, key).thenApply {
        it ?: defaultValueGetter()
    }

fun <K, V, L: MappedBatchLoader<K, V>> DataFetchingEnvironment.optional(
    mappedBatchLoaderType: KClass<L>,
    key: K
): CompletableFuture<V?> =
    getDataLoader<K, V?>(mappedBatchLoaderType.simpleName)
        .load(key)