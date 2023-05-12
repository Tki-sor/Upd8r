package com.tkisor.upd8r.annotation

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class UsedBy(val value: String = "Used")
