/*
 * Copyright (c) Gramophone 2019-2020.
 */

package com.android.sergiobelda.gramophone.util

fun getMinuteSeconds(millis : Int) : String {
    val minutes = (millis / 1000) / 60
    val seconds = (millis / 1000) % 60

    return "$minutes:$seconds"
}