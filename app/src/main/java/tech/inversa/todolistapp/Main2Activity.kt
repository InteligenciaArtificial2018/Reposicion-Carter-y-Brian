package tech.inversa.todolistapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import tech.inversa.todolistapp.data.HaveToPayDatabase
import tech.inversa.todolistapp.data.HaveToBaja

class Main2Activity : AppCompatActivity(), HaveToPayBajaAdapter.OnHaveToBajaItemClickListener {
    private var haveDatabase: HaveToPayDatabase? = null
    private var haveAdapter: HaveToPayBajaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(this,"Categoria Baja",Toast.LENGTH_SHORT).show()

        haveDatabase = HaveToPayDatabase.getInstance(this)
        haveAdapter  = HaveToPayBajaAdapter(haveDatabase?.getHaveToBajaDao()?.getHaveList())
        haveAdapter?.setHaveBajaToItemClickListener(this)

        // Llamar la activity de agregar tarea mediante el floating action button
        fabAddToDo.setOnClickListener {
            startActivity(Intent(this, HaveToPayAgregarActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.opCategoriaBaja->{

                startActivity(Intent(this, Main2Activity::class.java))
                return super.onOptionsItemSelected(item)
            }
            R.id.opCategoriaMedia->{

                startActivity(Intent(this, Main4Activity::class.java))
                return super.onOptionsItemSelected(item)
            }
            R.id.opCategoriaAlta->{

                startActivity(Intent(this, Main3Activity::class.java))
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onResume() {
        super.onResume()
        haveAdapter?.todoList = haveDatabase?.getHaveToBajaDao()?.getHaveList()
        rvToDo.adapter = haveAdapter
        rvToDo.layoutManager = LinearLayoutManager(this)
        rvToDo.hasFixedSize()
    }

    /**
     * Sobreescribimos la funcionalidad de click desde la herencia de la interfaz
     * del TodoAdapter. Enviamos la información mediante un intent con parámetros
     * hacia el layout correspondiente.
     */
    override fun onHaveToBajaItemClickListener(haveToBaja: HaveToBaja) {
        val intent = Intent(this, HaveToPayAgregarActivity::class.java)
        intent.putExtra("id", haveToBaja.id)
        intent.putExtra("titulo", haveToBaja.titulo)
        intent.putExtra("descripcion", haveToBaja.descripcion)
        intent.putExtra("categoria", haveToBaja.categoria)
        intent.putExtra("fecha", haveToBaja.fecha)
        intent.putExtra("hora", haveToBaja.hora)
        intent.putExtra("comentario", haveToBaja.comentario)
        startActivity(intent)
    }

    override fun onHaveToBajaItemLongClickListener(haveToBaja: HaveToBaja) {
        // Inicializar una nueva instancia de AlertDialog
        val builder = AlertDialog.Builder(this)

        // Colocar el titulo del dialogo
        builder.setTitle(R.string.tituloDialogoLongClick)

        // Mensaje a desplegar en el dialogo
        builder.setMessage(R.string.mensajeDialogoLongClick)

        // Los dialogos pueden tener hasta 3 botones, uno positivo (SI), uno negativo (NO)
        // y un boton neutro (CANCEL) los cuales utilizaremos para Modificar, Eliminar y Cancelar
        builder.setPositiveButton(R.string.modificar) {dialog, wich ->
            // Realizar el llamado a la activity de agregar enviando los valores mediante el intent
            val intent = Intent(this, HaveToPayAgregarActivity::class.java)
            intent.putExtra("id", haveToBaja.id)
            intent.putExtra("titulo", haveToBaja.titulo)
            intent.putExtra("descripcion", haveToBaja.descripcion)
            intent.putExtra("categoria", haveToBaja.categoria)
            intent.putExtra("fecha", haveToBaja.fecha)
            intent.putExtra("hora", haveToBaja.hora)
            intent.putExtra("comentario", haveToBaja.comentario)

            startActivity(intent)
        }

        builder.setNegativeButton(R.string.eliminar) {dialog, which ->
            haveDatabase?.getHaveToBajaDao()?.deleteTodo(haveToBaja)
            onResume()
            Toast.makeText(this, R.string.mensajetareaEliminada, Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton(R.string.cancelar) {dialog, which ->
            Toast.makeText(this,R.string.mensajeCancelarDialogoLongClick, Toast.LENGTH_SHORT).show()
        }

        // Crear el dialogo de alerta con todos los parámetros establecidos
        val dialogo: AlertDialog = builder.create()

        // Mostrar el dialogo
        dialogo.show()
    }
}
