package tech.inversa.todolistapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast

class time : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        var btnGuardar=findViewById<Button>(R.id.btnAgregartiempo)
        var selectorTiempo=findViewById<TimePicker>(R.id.elSelector) as TimePicker

        selectorTiempo.setOnTimeChangedListener { view, hourOfDay, minute ->
            Toast.makeText(this@time,"la hora que selecionaste es $hourOfDay: $minute",Toast.LENGTH_SHORT).show()
        }






    }
}
