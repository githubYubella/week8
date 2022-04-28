package id.ac.ubaya.todo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(entities = arrayOf(Todo::class),version = 1)
abstract class ToDoDatabase : RoomDatabase(){
    abstract  fun todoDao():TdoDao

    companion object{
        @Volatile private var instance:ToDoDatabase ?=null
        private val LOCK = Any()

        private fun buildDatabase(context:Context)=
            Room.databaseBuilder(context.applicationContext,
                                ToDoDatabase::class.java,
                                "tododb").build()
        operator fun invoke(context: Context){
            if(instance != null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance=it
                    }
                }
            }
        }
    }
}