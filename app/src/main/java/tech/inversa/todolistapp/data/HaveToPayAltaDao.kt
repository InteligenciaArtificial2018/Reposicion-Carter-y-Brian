package tech.inversa.todolistapp.data

import android.arch.persistence.room.*

@Dao
interface HaveToPayAltaDao {
    /**
     * Retorna todos las tuplas de Todo en orden ascendente.
     */
    @Query("SELECT * FROM categoriaAlta ORDER BY id ASC")
    fun getHaveList(): List<HaveToAlta>

    /**
     * Retorna una tupla desde la tabla todo
     * @param id el valor de la llave primaria a retornar.
     */
    @Query("SELECT * FROM categoriaAlta WHERE id = :id")
    fun getTodoItem(id: Int): HaveToAlta

    /**
     * Inserta una nueva tupla en la tabla todo.
     * @param categoriaAlta la tupla a insertar en la tabla.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTodo(categoriaAlta: HaveToAlta)

    /**
     * Actualiza una tupla en la tabla todo.
     * @param categoriaAlta el valor de la tupla a actualizar.
     */
    @Update
    fun updateTodo(categoriaAlta: HaveToAlta)

    /**
     * Remueve una tupla de la tabla todo.
     * @param categoriaAlta el valor de la tupla a remover.
     */
    @Delete
    fun deleteTodo(categoriaAlta: HaveToAlta)
}