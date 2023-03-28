package hr.tvz.android.giggle

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GiggleData::class], version = 1, exportSchema = false)
abstract class GiggleDatabase : RoomDatabase() {
    abstract fun getDao(): GiggleDAO
}