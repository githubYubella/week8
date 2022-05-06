package id.ac.ubaya.todo.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.todo.model.ToDoDatabase

val DB_NAME = "tododb"

fun buildDb(context: Context) =
    Room.databaseBuilder(context, ToDoDatabase::class.java,"tododb")
    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
    .build()

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }

// 1. Alter the Todo table by adding a new field:
// 2. is_done
// 3. INTEGER
// 4. Default value is 0 and not null



}
val MIGRATION_2_3 = object : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Why this is_done field use INTEGER instead of BOOLEAN?
        //menggunakan integer supaya tidak perlu convert ke toInteger
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}