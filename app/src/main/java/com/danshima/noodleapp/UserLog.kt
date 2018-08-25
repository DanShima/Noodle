package com.danshima.noodleapp

import java.text.SimpleDateFormat
import java.util.Date

/**
 * This class holds info for the "my list" option in which the user can write down some review, experience
 * or a noodle dish he just tried.
 * Created by Giddy on 16/11/2017.
 */

class UserLog//to initialize a simpler, user version of noodle
@JvmOverloads constructor(var experience: String?, val date: Date = Date(java.lang.System.currentTimeMillis())) {

    /**
     * @return the string representation of a user experience log
     */
    override fun toString(): String {
        val newDate = SimpleDateFormat("dd/MM/yy")
        val dateString = newDate.format(date)
        return "($dateString) $experience"
    }
}
