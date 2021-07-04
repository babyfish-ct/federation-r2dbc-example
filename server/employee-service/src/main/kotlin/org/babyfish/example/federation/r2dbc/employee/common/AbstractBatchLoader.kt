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
                onLoad(keys).let {
                    val absentValue = absentValue
                    if (absentValue !== null) {
                        val missedKeys = keys - it.keys
                        if (missedKeys.isNotEmpty()) {
                            val mutableMap = if (it is MutableMap<*, *>) {
                                it as MutableMap<K, V>
                            } else {
                                it.toMutableMap()
                            }
                            for (missedKey in missedKeys) {
                                mutableMap[missedKey] = absentValue
                            }
                            return@let mutableMap
                        }
                    }
                    it
                }
            }
            .asCompletableFuture()

    protected abstract suspend fun onLoad(keys: Set<K>): Map<K, V>

    protected open val absentValue : V? = null
}