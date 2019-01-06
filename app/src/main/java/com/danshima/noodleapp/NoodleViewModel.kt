package com.danshima.noodleapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import com.danshima.noodleapp.data.Noodle
import com.danshima.noodleapp.data.NoodleRepository


class NoodleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoodleRepository = NoodleRepository(application)

    private val noodles: LiveData<List<Noodle>>
    private lateinit var noodle: LiveData<Noodle>

    init {
        noodles = repository.getAllNoodles()

    }

    fun getAllNoodles(): LiveData<List<Noodle>> {
        return noodles
    }

    fun getNoodle(id: String): LiveData<Noodle> {
        noodle = repository.getNoodle(id)
        return noodle
    }

    fun insert(noodle: Noodle) {
        repository.insert(noodle)
    }
}