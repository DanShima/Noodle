package com.danshima.noodleapp


/**
 * The noodle class defines a single noodle dish with its attributes
 * Created by Giddy on 04/11/2017.
 */

data class Noodle(
    var name: String?,
    var description: String?,
    var photoID: Int,
    var suggestedRestaurant: String?,
    var categoryNumber: Int)

