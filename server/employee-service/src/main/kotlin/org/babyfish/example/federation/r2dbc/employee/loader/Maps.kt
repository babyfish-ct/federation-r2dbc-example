package org.babyfish.example.federation.r2dbc.employee.loader

inline fun <K, V> Map<K, V>.defaultValueForKeys(
    defaultValue: V,
    keys: Collection<K>
): Map<K, V> {
    val newMap = if (this is MutableMap<*, *>) {
        this as MutableMap<K, V>
    } else {
        toMutableMap()
    }
    for (key in keys) {
        newMap.putIfAbsent(key, defaultValue)
    }
    return newMap
}