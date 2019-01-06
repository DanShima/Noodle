package com.danshima.noodleapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

/**
 * The Data Access Object for the Noodle class.
 */
@Dao
interface NoodleDao {
    @Query("SELECT * FROM noodles ORDER BY name")
    fun getNoodles(): LiveData<List<Noodle>>

    @Query("SELECT * FROM noodles WHERE categoryNumber = :categoryNumber ORDER BY name")
    fun getNoodlesWithCategoryNumber(categoryNumber: Int): LiveData<List<Noodle>>

    @Query("SELECT * FROM noodles WHERE name = :noodleId")
    fun getNoodle(noodleId: String): LiveData<Noodle>

    @Insert
    fun insert(noodle: Noodle)

    @Insert(onConflict = REPLACE)
    fun insertAll(noodles: List<Noodle>)

    @Query("DELETE FROM noodles")
    fun deleteAll()
}