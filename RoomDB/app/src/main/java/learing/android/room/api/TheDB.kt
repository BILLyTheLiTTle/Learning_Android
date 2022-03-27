package learing.android.room.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import learing.android.room.models.Customer
import learing.android.room.models.Product

@Database(entities = [Customer::class, Product::class], version = 1)
abstract class TheDB: RoomDatabase() {

abstract fun dataDao(): RoomDao

    companion object {
        private var INSTANCE: TheDB? = null

        fun getInstance(context: Context): TheDB? {
            if (INSTANCE == null) {
                synchronized(TheDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TheDB::class.java, "the_db.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}