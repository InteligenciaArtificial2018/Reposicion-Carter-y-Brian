package tech.inversa.todolistapp.data

import android.arch.persistence.room.*

@Dao
interface HaveToPayMediaDao {
    /**
     * Retorna todos las tuplas de Todo en orden ascendente.
     */
    @Query("SELECT * FROM categoriaMedia ORDER BY id ASC")
    fun getHaveList(): List<HaveToMedia>

    /**
     * Retorna una tupla desde la tabla todo
     * @param id el valor de la llave primaria a retornar.
     */
    @Query("SELECT * FROM categoriaMedia WHERE id = :id")
    fun getTodoItem(id: Int): HaveToMedia

    /**
     * Inserta una nueva tupla en la tabla todo.
     * @param categoriaMedia la tupla a insertar en la tabla.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTodo(categoriaMedia: HaveToMedia)

    /**
     * Actualiza una tupla en la tabla todo.
     * @param categoriaMedia el valor de la tupla a actualizar.
     */
    @Update
    fun updateTodo(categoriaMedia: HaveToMedia)

    /**
     * Remueve una tupla de la tabla todo.
     * @param categoriaMedia el valor de la tupla a remover.
     */
    @Delete
    fun deleteTodo(categoriaMedia: HaveToMedia)
}