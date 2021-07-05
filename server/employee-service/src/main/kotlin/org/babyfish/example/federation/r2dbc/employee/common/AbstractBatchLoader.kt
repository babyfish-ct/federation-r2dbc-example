package org.babyfish.example.federation.r2dbc.employee.common

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.future.asCompletableFuture
import org.dataloader.MappedBatchLoader
import java.util.concurrent.CompletionStage

abstract class AbstractBatchLoader<K, V> : MappedBatchLoader<K, V> {

    override fun load(keys: Set<K>): CompletionStage<Map<K, V>> =
        GlobalScope
            .async {
                onLoad(keys)
            }
            .asCompletableFuture()

    protected abstract suspend fun onLoad(keys: Set<K>): Map<K, V>
}