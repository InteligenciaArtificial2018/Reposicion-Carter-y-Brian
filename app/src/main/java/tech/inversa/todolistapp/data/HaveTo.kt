package tech.inversa.todolistapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo")
class HaveTo(
    @ColumnInfo(name = "asunto")
    var asunto: String = "",
    @ColumnInfo(name = "detalle")
    var detalle: String = "",
    @ColumnInfo(name = "fechaFinal")
    var fechafinal: String = "",
    @ColumnInfo(name = "monto")
    var  monto: String = ""){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}