package com.codigo.appbase.mappers

interface UnidirectionalMap<S, T> {
    fun map(data: S): T
}