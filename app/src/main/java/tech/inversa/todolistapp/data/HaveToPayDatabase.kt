package tech.inversa.todolistapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [HaveTo::class], version = 2, exportSchema = false)
abstract class HaveToPayDatabase: RoomDatabase() {
    /**
     * Este es un método abstracto que retorna el DAO para la base de datos.
     */
    abstract fun getHaveToDao(): HaveToPayDao

    /**
     * Un patrón de diseño Singleton es utilizado para asegurarnos que
     * solamente se cree una instancia de la base de datos.
     */
    companion object {
        val databaseName = "tododatabase"
        var todoListDatabase: HaveToPayDatabase? = null

        fun getInstance(context: Context): HaveToPayDatabase? {
            if (todoListDatabase == null) {
                todoListDatabase = Room.databaseBuilder(context,
                    HaveToPayDatabase::class.java,
                    HaveToPayDatabase.databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return todoListDatabase
        }
    }
}