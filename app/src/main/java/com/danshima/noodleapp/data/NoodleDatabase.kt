package com.danshima.noodleapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import android.os.AsyncTask
import com.danshima.noodleapp.R


@Database(entities = [Noodle::class], version = 1, exportSchema = false)
abstract class NoodleDatabase: RoomDatabase() {
    abstract fun noodleDao(): NoodleDao

    private class PopulateDbAsync internal constructor(db: NoodleDatabase) : AsyncTask<Void, Void, Void>() {

        private val dao: NoodleDao = db.noodleDao()

        override fun doInBackground(vararg params: Void): Void? {
            dao.deleteAll()
            var noodle = Noodle(name = "Spicy Ramen", description = "Chicken broth, marinated pork, chilli and bean sprouts", photoID = R.drawable.spicyramen, suggestedRestaurant = "Totemo Ramen\n Sankt Eriksgatan 70, 11320 Stockholm", categoryNumber = 1)
            dao.insert(noodle)
            noodle = Noodle(name = "Tokyo Ramen", description = "Dashi-based broth, marinated pork and fermented bamboo shoots", photoID = R.drawable.tokyo, suggestedRestaurant = "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm", categoryNumber = 1)

            dao.insert(noodle)
            return null
        }
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: NoodleDatabase? = null
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(instance!!).execute()
            }
        }

        fun getDatabase(context: Context): NoodleDatabase? {
            if (instance == null) {
                synchronized(NoodleDatabase::class.java) {
                    if (instance == null) {
                        // Create database here
                        instance = Room.databaseBuilder(context.applicationContext,
                            NoodleDatabase::class.java, "noodle_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }
            return instance
        }
    }
}