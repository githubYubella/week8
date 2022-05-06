package id.ac.ubaya.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.todo.model.TdoDao
import id.ac.ubaya.todo.model.ToDoDatabase
import id.ac.ubaya.todo.model.Todo
import id.ac.ubaya.todo.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(list: List<Todo>) {
        launch {
//            val db = Room.databaseBuilder(
//                getApplication(), ToDoDatabase::class.java,
//                "tododb"
//            ).build()
            val db = buildDb(getApplication())
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(uuid:Int){
        launch {
            val db = buildDb(getApplication())
            todoLD.value=db.todoDao().selectTodo(uuid)
        }
    }

    fun update(id:Int, title:String, notes:String,priority:Int){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(id,title,notes,priority)
        }
    }
    fun update(todo: Todo){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(todo)
        }
    }
}

