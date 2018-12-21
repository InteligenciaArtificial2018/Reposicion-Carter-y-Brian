package tech.inversa.todolistapp.data

import android.arch.persistence.room.*

@Dao
interface HaveToPayBajaDao {
    /**
     * Retorna todos las tuplas de Todo en orden ascendente.
     */
    @Query("SELECT * FROM categoriaBaja ORDER BY id ASC")
    fun getHaveList(): List<HaveToBaja>

    /**
     * Retorna una tupla desde la tabla todo
     * @param id el valor de la llave primaria a retornar.
     */
    @Query("SELECT * FROM categoriaBaja WHERE id = :id")
    fun getTodoItem(id: Int): HaveToBaja

    /**
     * Inserta una nueva tupla en la tabla todo.
     * @param categoriaBaja la tupla a insertar en la tabla.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTodo(categoriaBaja: HaveToBaja)

    /**
     * Actualiza una tupla en la tabla todo.
     * @param categoriaBaja el valor de la tupla a actualizar.
     */
    @Update
    fun updateTodo(categoriaBaja: HaveToBaja)

    /**
     * Remueve una tupla de la tabla todo.
     * @param categoriaBaja el valor de la tupla a remover.
     */
    @Delete
    fun deleteTodo(categoriaBaja: HaveToBaja)
}