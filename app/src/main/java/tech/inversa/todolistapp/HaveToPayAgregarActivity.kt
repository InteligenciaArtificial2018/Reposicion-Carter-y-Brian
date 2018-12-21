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
import tech.inversa.todolistapp.data.HaveToMedia
import tech.inversa.todolistapp.data.HaveToBaja
import tech.inversa.todolistapp.data.HaveToAlta

class HaveToPayAgregarActivity : AppCompatActivity(){

    private var todoDatabase: HaveToPayDatabase? = null

    lateinit var Opcion : Spinner
    lateinit var  TextView : TextView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_add)

        Opcion = findViewById<Spinner>(R.id.spCategoria)
        val Opciones = arrayListOf<String>("Media","Baja","Alta")
        TextView = findViewById(R.id.textView)
        Opcion.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Opciones)

        Opcion.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TextView.text ="Selecciona Algo"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TextView.text = Opciones.get(position)
            }
        }


        todoDatabase = HaveToPayDatabase.getInstance(this)

        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled((true))

        // Validar si se nos envía el intent con el valor del título
        val asunto = intent.getStringExtra("asunto")
        val detalle = intent.getStringExtra("detalle")

        // Si no está definido o viene en blanco, el usuario presionó el FAB

        if (asunto == null || asunto == "") {

            btnAgregarTarea.setOnClickListener {
                if(textView.text == "Baja"){
                    val tarea = HaveToBaja(etTitulo.text.toString(), etDescripcion.text.toString(), textView.text.toString(), edDate.text.toString(), edTime.text.toString(), etDescripcion.text.toString())
                    todoDatabase?.getHaveToBajaDao()?.saveTodo(tarea)
                    finish()
                }
                else if (textView.text == "Media")
                {
                    val tarea = HaveToMedia(etTitulo.text.toString(), etDescripcion.text.toString(), textView.text.toString(), edDate.text.toString(), edTime.text.toString(), etDescripcion.text.toString())
                    todoDatabase?.getHaveToMediaDao()?.saveTodo(tarea)
                    finish()
                }
                else if (textView.text == "Alta"){

                    val tarea = HaveToAlta(etTitulo.text.toString(), etDescripcion.text.toString(), textView.text.toString(), edDate.text.toString(), edTime.text.toString(), etDescripcion.text.toString())
                    todoDatabase?.getHaveToAltaDao()?.saveTodo(tarea)
                    finish()
                }
            }

        } else {
            val id = intent.getIntExtra("id", 0)
            etTitulo.setText(asunto)
            etDescripcion.setText(detalle)
            btnAgregarTarea.setOnClickListener {
                if(textView.text == "Baja"){
                    val tarea = HaveToBaja(etTitulo.text.toString(), etDescripcion.text.toString(), textView.text.toString(), edDate.text.toString(), edTime.text.toString(), etDescripcion.text.toString())
                    tarea.id = id
                    todoDatabase?.getHaveToBajaDao()?.updateTodo(tarea)
                    finish()
                }
                else if (textView.text == "Media"){
                    val tarea = HaveToMedia(etTitulo.text.toString(), etDescripcion.text.toString(), textView.text.toString(), edDate.text.toString(), edTime.text.toString(), etDescripcion.text.toString())
                    tarea.id = id
                    todoDatabase?.getHaveToMediaDao()?.updateTodo(tarea)
                    finish()
                }
                else if (textView.text == "Alta"){
                    val tarea = HaveToAlta(etTitulo.text.toString(), etDescripcion.text.toString(), textView.text.toString(), edDate.text.toString(), edTime.text.toString(), etDescripcion.text.toString())
                    tarea.id = id
                    todoDatabase?.getHaveToAltaDao()?.updateTodo(tarea)
                    finish()
                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
