package com.soethan.data.mapper

abstract class BaseMapper<out T, in E> {
    abstract fun map(e: E): T
}