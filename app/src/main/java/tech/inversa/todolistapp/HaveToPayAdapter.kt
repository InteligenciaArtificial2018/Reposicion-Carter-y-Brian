package tech.inversa.todolistapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import tech.inversa.todolistapp.data.HaveTo

class HaveToPayAdapter(var todoList: List<HaveTo>? = ArrayList<HaveTo>()): RecyclerView.Adapter<HaveToPayAdapter.ViewHolder>() {
    private var onHaveToItemClickListener: OnHaveToItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HaveToPayAdapter.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_todo_item, parent, false)

        return ViewHolder(vista, todoList!!)
    }

    override fun getItemCount(): Int {
        return todoList?.count()!!
    }

    override fun onBindViewHolder(holder: HaveToPayAdapter.ViewHolder, position: Int) {
        // Obtener la posición del item clickeado
        holder.vista.setOnClickListener{
            onHaveToItemClickListener?.onHaveToItemClickListener(todoList?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onHaveToItemClickListener?.onHaveToItemLongClickListener(todoList?.get(position)!!)
            true
        }
//        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val todoList: List<HaveTo>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = todoList.get(position).asunto
            vista.findViewById<TextView>(R.id.tvComentario).text = todoList.get(position).detalle
//            vista.findViewById<TextView>(R.id.tvPrimeraLetra).text = todoList.get(position).asunto.first().toUpperCase().toString()
        }
    }

    /**
     * Utilizamos un método en lugar de asignar la funcionalidad vía el constructor
     * de la clase principal para mayor comodidad al momento de sobreescribir la funcionalidad.
     */
    fun setHaveToItemClickListener(onHaveToItemClickListener: OnHaveToItemClickListener) {
        this.onHaveToItemClickListener = onHaveToItemClickListener
    }

    /**
     * Definimos la interface que permite extender métodos que el RecyclerView no posee
     */
    interface OnHaveToItemClickListener {
        fun onHaveToItemClickListener(todo: HaveTo)
        fun onHaveToItemLongClickListener(todo: HaveTo)
    }
}