package tech.inversa.todolistapp

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_to_do_add.*
import kotlinx.android.synthetic.main.template_todo_item.*
import tech.inversa.todolistapp.data.HaveToPayDatabase
import tech.inversa.todolistapp.data.HaveTo

class HaveToPayAgregarActivity : AppCompatActivity(){

    private var todoDatabase: HaveToPayDatabase? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_add)

        var Opcion = findViewById<Spinner>(R.id.spCategoria)
        val Opciones = arrayOf("Baja","Media","Alta")
        Opcion.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Opciones)


        todoDatabase = HaveToPayDatabase.getInstance(this)

        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled((true))

        // Validar si se nos envía el intent con el valor del título
        val asunto = intent.getStringExtra("asunto")
        val detalle = intent.getStringExtra("detalle")

        // Si no está definido o viene en blanco, el usuario presionó el FAB
        if (asunto == null || asunto == "") {
            btnAgregarTarea.setOnClickListener {
                val tarea = HaveTo(etTitulo.text.toString(), etDescripcion.text.toString())
                todoDatabase?.getHaveToDao()?.saveTodo(tarea)
                finish()
            }
        } else {
            val id = intent.getIntExtra("id", 0)
            etTitulo.setText(asunto)
            etDescripcion.setText(detalle)
            btnAgregarTarea.setOnClickListener {
                val tarea = HaveTo(etTitulo.text.toString(), etDescripcion.text.toString())
                tarea.id = id
                todoDatabase?.getHaveToDao()?.updateTodo(tarea)
                finish()
            }
        }
        //val chequeado = findViewById<Switch>(R.id.swAlarma)

        //chequeado.setOnClickListener {
          //  val tiempo = Intent(this, time::class.java)
            //startActivity(tiempo)
       // }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
