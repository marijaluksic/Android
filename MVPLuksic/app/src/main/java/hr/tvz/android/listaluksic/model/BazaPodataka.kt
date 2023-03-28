package hr.tvz.android.listaluksic.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1, exportSchema = false)
abstract class BazaPodataka : RoomDatabase() {
    abstract fun todoDao(): ToDoDao
    companion object{ private var instance: BazaPodataka? = null

        fun getDatabase(context: Context): BazaPodataka? {
            if (instance == null) {
                instance = buildDatabase(context)
            }
            return instance
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                BazaPodataka::class.java,
                "database-name"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}