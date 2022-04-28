package id.ac.ubaya.todo.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.todo.model.ToDoDatabase

val DB_NAME = "tododb"

fun buildDb(context: Context) =
    Room.databaseBuilder(context, ToDoDatabase::class.java,"tododb")
    .addMigrations(MIGRATION_1_2)
    .build()

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }

}