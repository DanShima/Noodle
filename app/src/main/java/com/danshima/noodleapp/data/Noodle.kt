package com.danshima.noodleapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noodles")
data class Noodle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "photoID")
    val photoID: Int,
    @ColumnInfo(name = "suggestedRestaurant")
    val suggestedRestaurant: String,
    @ColumnInfo(name = "categoryNumber")
    val categoryNumber: Int?,
    @ColumnInfo(name = "check")
    var check: Boolean = false)

