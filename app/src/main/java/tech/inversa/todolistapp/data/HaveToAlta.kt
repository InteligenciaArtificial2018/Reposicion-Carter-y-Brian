package tech.inversa.todolistapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "categoriaBaja")
class HaveToAlta(
    @ColumnInfo(name = "titulo")
    var titulo: String = "",
    @ColumnInfo(name = "descripcion")
    var descripcion: String = "",
    @ColumnInfo(name = "categoria")
    var categoria: String = "",
    @ColumnInfo(name = "fecha")
    var  fecha: String = "",
    @ColumnInfo(name = "hora")
    var  hora: String = "",
    @ColumnInfo(name = "comentario")
    var  comentario: String = "")
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}