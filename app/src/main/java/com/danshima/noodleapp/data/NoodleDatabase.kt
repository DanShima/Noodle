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
            var noodle = Noodle(
                name = "Spicy Ramen",
                description = "Chicken broth, marinated pork, chilli and bean sprouts",
                photoID = R.drawable.spicyramen,
                suggestedRestaurant = "Totemo Ramen\n Sankt Eriksgatan 70, 11320 Stockholm",
                categoryNumber = 1)
            dao.insert(noodle)
            noodle = Noodle(
                name = "Tokyo Ramen",
                description = "Dashi-based broth, marinated pork and fermented bamboo shoots",
                photoID = R.drawable.tokyo,
                suggestedRestaurant = "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm",
                categoryNumber = 1)
            dao.insert(noodle)
            noodle = Noodle(
                name = "Vegetarian Ramen",
                description = "Mushroom dashi-based broth, tofu, pak choi, miso and corn",
                photoID = R.drawable.vegetarianramen,
                suggestedRestaurant = "Ai Ramen\\nErstagatan 22, 11636 Stockholm",
                categoryNumber = 1
            )
            dao.insert(noodle)


//        addNoodle(db, Noodle("Miso Ramen", "Miso broth, marinated pork, egg, spring onion and bean sprouts", R.drawable.miso, "Cafe Stiernan\nÖsterlånggaten 45, 11131 Stockholm", 1))
//        addNoodle(db, Noodle("Tonkatsu Ramen", "Pork bone based broth, grilled pork, spicy garlic with miso", R.drawable.tonkatsu, "Blue Light Yokohama\nÅsögatan 170, 11632 Stockholm", 1))
//        addNoodle(db, Noodle("Shio Ramen", "Seasalt broth, pork, egg, quail and vegetable", R.drawable.shio, "Ramen Manga\nPilgatan 28, 11223 Stockholm", 1))
//        addNoodle(db, Noodle("Nabeyaki Udon", "Udon noodles in fish broth with chicken, shrimp, egg and leek", R.drawable.udon, "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 1))
//        addNoodle(db, Noodle("Cao Lau", "Braised five spice porkbelly, soy sauce, garlic, shallots, fish mint, crown daisy leaves, cilantro, Thai basil, Chinese chives, gem salad, mung bean sprouts, deep fried noodle dough croutons, broth, chili paste with lemon grass.",
//            R.drawable.caolau, "Minh Mat\nOdengatan 94, 113 33 Stockholm", 2))
//        addNoodle(db, Noodle("Bun Bo Hue", "Rich, spicy vermicelli noodle soup with deep layers of flavors. Served with, banana flowers,  tender slices of beef and pork, lemongrass, and lots of fresh herbs.",
//            R.drawable.bonbohue, "Sankt Eriksgatan 124, 113 31 Stockholm", 2))
//        addNoodle(db, Noodle("Beef Pho", "A comforting richly seasoned beef broth is ladled over rice noodles and thinly sliced beef.", R.drawable.beefpho, "EatNam\nOdengatan 85, 113 22 Stockholm", 2))
//        addNoodle(db, Noodle("Hoanh Thanh Chay", "Vegetarian wonton soup with black bean dumplings, tofu balls with five spices, shiitake, shimeij, pak choy, lemon grass bouillon.", R.drawable.hoanh_thanh_chay,
//            "Minh Mat\nOdengatan 94, 113 33 Stockholm", 2))
//        addNoodle(db, Noodle("Chicken Pho", "A light chicken broth with banh pho noodles and tender poached chicken.", R.drawable.chickenpho, "Pho & Bun\nTegnérgatan 19, 111 40 Stockholm", 2))
//        addNoodle(db, Noodle("Pad See Ew", "A popular and delicious Thai noodle dish made with flat, wide rice noodles fried in soy sauce with meat, egg, and vegetables.",
//            R.drawable.pad_see_ew, "Waan Thai\nSankt Eriksgatan 92, 113 62 Stockholm", 3))
//        addNoodle(db, Noodle("Pad Thai", "Stir fry rice noodles with shrimp, tofu, eggs and bean sprouts with sweet sour sauce", R.drawable.padthai, "Tjabba Thai\nWallingatan 7, 111 60 Stockholm", 3))
//        addNoodle(db, Noodle("Tom Yum Ramen", "Sour and spicy soup made from lemongrass, kaffir lime leaves & galangal filled with prawns, mushrooms, tomatoes , onions and rice vermicelli noodles.",
//            R.drawable.thaispicy, "Ramen Ki-mama\nBirger Jarlsgatan 93, 113 56 Stockholm", 3))
//        addNoodle(db, Noodle("Jajang Myeon", " Korean Chinese noodle dish topped with a thick sauce made of chunjang, diced pork and vegetables.",
//            R.drawable.jajangmyeon, "Koreana\nLuntmakargatan 76, 113 51 Stockholm", 4))
//        addNoodle(db, Noodle("Japchae", "Stir-fried glass noodles and vegetables is a sweet and savory dish popular in Korean cuisine, with sweet potatoe noodles, assorted vegetables, meat, and mushrooms",
//            R.drawable.japchae, "Arirang\nLuntmakargatan 65, 113 58 Stockholm", 4))
//        addNoodle(db, Noodle("Kimchi Ramen", "Noodles in soy broth, yakiniku(beef), fermented cabbage called kimchi, bean sprouts and sesame", R.drawable.kimchiramen,
//            "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 4))
//        addNoodle(db, Noodle("Mul Naengmyeon", "Nourishing, icy-cold broth with chewy buckwheat noodles that is very popular in the summer.",
//            R.drawable.colramen, "Kimchi Restaurant\nLuntmakargatan 95, 113 51 Stockholm", 4))
//        addNoodle(db, Noodle("Wonton Soup", "Noodles in soy broth, leek, shrimp and pork dumplings.", R.drawable.wonton, "Ki-mama Ramen\nBirger Jarlsgatan 93, 11356 Stockholm", 5))
//        addNoodle(db, Noodle("Tofu hotpot", "Vegetarian noodle soup with tomato-based broth, tofu, shitake, and zucchini", R.drawable.tofu, "Lao Wai\nLuntmakargatan 74, 113 51 Stockholm", 5))
//        addNoodle(db, Noodle("Beef Noodle Soup", "Chinese Noodle soup with tender beef and pakchoi", R.drawable.chinesebeef, "Mandarin City\nSveavägen 33, 111 34 Stockholm", 5))
//        addNoodle(db, Noodle("Dandan Mian", "A spicy sauce containing preserved vegetables, chili oil, Sichuan pepper, minced pork, and scallions served over noodles",
//            R.drawable.spicydandan, "Waipo Stockholm\nDrottninggatan 25, 111 51 Stockholm", 5))
            return null
        }
    }

    companion object {
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