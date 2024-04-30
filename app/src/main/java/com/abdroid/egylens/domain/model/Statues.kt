package com.abdroid.egylens.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Statue(
    val statueId:Int = 1,
    val name: String = "",
    val desc: String = "",
    val imageUrl: String = "",
    val about :String,
) : Parcelable {
    constructor() : this(1,"", "", "" , "") // Add a no-argument constructor
}