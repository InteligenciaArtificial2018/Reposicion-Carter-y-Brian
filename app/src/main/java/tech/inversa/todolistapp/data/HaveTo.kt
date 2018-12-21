package tech.inversa.todolistapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ForeignKey


@Entity(tableName = "todo",
        foreignKeys = arrayOf(ForeignKey(entity = HaveToBaja::class, parentColumns = arrayOf("id"), childColumns = arrayOf("idCategoriaBaja")),
                              ForeignKey(entity = HaveToAlta::class, parentColumns = arrayOf("id"), childColumns = arrayOf("idCategoriaAlta")),
                              ForeignKey(entity = HaveToBaja::class, parentColumns = arrayOf("id"), childColumns = arrayOf("idCategoriaMedia"))))

class HaveTo(
    @ColumnInfo(name = "idCategoriaBaja")
    var asunto: String = "",
    @ColumnInfo(name = "idCategoriaMedia")
    var detalle: String = "",
    @ColumnInfo(name = "idCategoriaAlta")
    var  monto: String = ""){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}