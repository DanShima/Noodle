package com.danshima.noodleapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The noodle class defines a single noodle dish with its attributes
 * Created by Giddy on 04/11/2017.
 */
@Entity(tableName = "noodles")
data class Noodle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val photoID: Int,
    val suggestedRestaurant: String,
    val categoryNumber: Int?)

