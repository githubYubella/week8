package id.ac.ubaya.todo.model

import androidx.room.*

@Dao
interface TdoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)

    @Query("SELECT*FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo():List<Todo>

    @Query("SELECT*FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int):Todo


    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid =:id")
    suspend fun update(id:Int, title:String, notes:String, priority:Int)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}