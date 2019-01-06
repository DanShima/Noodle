package com.danshima.noodleapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import android.os.AsyncTask


class NoodleRepository() {
    private lateinit var noodleDao: NoodleDao
    private lateinit var noodles: LiveData<List<Noodle>>
    private lateinit var noodle: LiveData<Noodle>

    constructor(application: Application) : this() {
        val db = NoodleDatabase.getDatabase(application)
        noodleDao = db!!.noodleDao()
        noodles = noodleDao.getNoodles()

    }

    fun getAllNoodles(): LiveData<List<Noodle>> {
        return noodles
    }

    fun getNoodle(id: String): LiveData<Noodle> {
        noodle = noodleDao.getNoodle(id)
        return noodle
    }

    fun insert(noodle: Noodle) {
        InsertAsyncTask(noodleDao).execute(noodle)
    }

    private class InsertAsyncTask internal constructor(private val asyncTaskDao: NoodleDao) : AsyncTask<Noodle, Void, Void>() {

        override fun doInBackground(vararg params: Noodle): Void? {
            asyncTaskDao.insert(params[0])
            return null
        }
    }
}