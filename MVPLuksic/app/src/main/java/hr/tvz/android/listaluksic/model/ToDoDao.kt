package hr.tvz.android.listaluksic.model

import androidx.room.*

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDo ORDER BY id DESC")
    fun getAll(): MutableList<ToDo>

    @Insert
    fun insertOne(todo: ToDo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todos: ToDo)

    @Delete
    fun delete(todo: ToDo)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewItem(todo : ToDo)

    @Query("DELETE FROM ToDo")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM ToDo")
    suspend fun getAllItems() : List<ToDo>
}