package com.abdroid.egylens.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Statue(
    val statueId:Int = 1,
    val name: String,
    val desc: String,
    @PrimaryKey val imageUrl: String,
    val about :String,
) : Parcelable {
    constructor() : this(1,"", "", "" , "")
}