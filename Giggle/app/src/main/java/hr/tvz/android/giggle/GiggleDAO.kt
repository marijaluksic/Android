package hr.tvz.android.giggle

import androidx.room.*

@Dao
interface GiggleDAO {
    @Query("SELECT * FROM GiggleData ORDER BY id DESC")
    fun getAll(): MutableList<GiggleData>

    @Insert
    fun insertOne(giggle: GiggleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg giggles: GiggleData)

    @Delete
    fun delete(giggle: GiggleData)
}