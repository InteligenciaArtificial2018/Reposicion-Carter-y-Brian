package tech.inversa.todolistapp.data

import android.arch.persistence.room.*

@Dao
interface HaveToPayAltaDao {
    /**
     * Retorna todos las tuplas de Todo en orden ascendente.
     */
    @Query("SELECT * FROM todo ORDER BY id ASC")
    fun getHaveList(): List<HaveToAlta>

    /**
     * Retorna una tupla desde la tabla todo
     * @param id el valor de la llave primaria a retornar.
     */
    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoItem(id: Int): HaveToAlta

    /**
     * Inserta una nueva tupla en la tabla todo.
     * @param todo la tupla a insertar en la tabla.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTodo(todo: HaveToAlta)

    /**
     * Actualiza una tupla en la tabla todo.
     * @param todo el valor de la tupla a actualizar.
     */
    @Update
    fun updateTodo(todo: HaveToAlta)

    /**
     * Remueve una tupla de la tabla todo.
     * @param todo el valor de la tupla a remover.
     */
    @Delete
    fun deleteTodo(todo: HaveToAlta)
}