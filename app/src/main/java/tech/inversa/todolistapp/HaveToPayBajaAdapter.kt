package tech.inversa.todolistapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import tech.inversa.todolistapp.data.HaveToBaja

class HaveToPayBajaAdapter(var todoList: List<HaveToBaja>? = ArrayList<HaveToBaja>()): RecyclerView.Adapter<HaveToPayBajaAdapter.ViewHolder>() {
    private var onHaveToBajaItemClickListener: OnHaveToBajaItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HaveToPayBajaAdapter.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_todo_item, parent, false)

        return ViewHolder(vista, todoList!!)
    }

    override fun getItemCount(): Int {
        return todoList?.count()!!
    }

    override fun onBindViewHolder(holder: HaveToPayBajaAdapter.ViewHolder, position: Int) {
        // Obtener la posición del item clickeado
        holder.vista.setOnClickListener{
            onHaveToBajaItemClickListener?.onHaveToBajaItemClickListener(todoList?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onHaveToBajaItemClickListener?.onHaveToBajaItemLongClickListener(todoList?.get(position)!!)
            true
        }
//        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val todoList: List<HaveToBaja>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = todoList.get(position).titulo
//            vista.findViewById<TextView>(R.id.tvPrimeraLetra).text = todoList.get(position).asunto.first().toUpperCase().toString()
        }
    }

    /**
     * Utilizamos un método en lugar de asignar la funcionalidad vía el constructor
     * de la clase principal para mayor comodidad al momento de sobreescribir la funcionalidad.
     */
    fun setHaveBajaToItemClickListener(onHaveToBajaItemClickListener: OnHaveToBajaItemClickListener) {
        this.onHaveToBajaItemClickListener = onHaveToBajaItemClickListener
    }

    /**
     * Definimos la interface que permite extender métodos que el RecyclerView no posee
     */
    interface OnHaveToBajaItemClickListener {
        fun onHaveToBajaItemClickListener(todo: HaveToBaja)
        fun onHaveToBajaItemLongClickListener(todo: HaveToBaja)
    }
}