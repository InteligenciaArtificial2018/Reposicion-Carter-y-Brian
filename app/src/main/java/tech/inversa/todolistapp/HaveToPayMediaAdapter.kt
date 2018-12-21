package tech.inversa.todolistapp.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import tech.inversa.todolistapp.R
import tech.inversa.todolistapp.data.HaveToMedia

class HaveToPayMediaAdapter(var todoList: List<HaveToMedia>? = ArrayList<HaveToMedia>()): RecyclerView.Adapter<HaveToPayMediaAdapter.ViewHolder>() {
    private var onHaveToMediaItemClickListener: OnHaveToMediaItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HaveToPayMediaAdapter.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_todo_item, parent, false)

        return ViewHolder(vista, todoList!!)
    }

    override fun getItemCount(): Int {
        return todoList?.count()!!
    }

    override fun onBindViewHolder(holder: HaveToPayMediaAdapter.ViewHolder, position: Int) {
        // Obtener la posición del item clickeado
        holder.vista.setOnClickListener{
            onHaveToMediaItemClickListener?.onHaveToMediaItemClickListener(todoList?.get(position)!!)
        }
        holder.vista.setOnLongClickListener{
            onHaveToMediaItemClickListener?.onHaveToMediaItemLongClickListener(todoList?.get(position)!!)
            true
        }
        holder.onBindViews(position)
    }

    class ViewHolder(val vista: View, val todoList: List<HaveToMedia>): RecyclerView.ViewHolder(vista) {
        fun onBindViews(position: Int) {
            vista.findViewById<TextView>(R.id.tvTitulo).text = todoList.get(position).titulo
            vista.findViewById<TextView>(R.id.tvComentario).text = todoList.get(position).comentario
//            vista.findViewById<TextView>(R.id.tvPrimeraLetra).text = todoList.get(position).asunto.first().toUpperCase().toString()
        }
    }

    /**
     * Utilizamos un método en lugar de asignar la funcionalidad vía el constructor
     * de la clase principal para mayor comodidad al momento de sobreescribir la funcionalidad.
     */
    fun setHaveToMediaItemClickListener(onHaveMediaToItemClickListener: OnHaveToMediaItemClickListener) {
        this.onHaveToMediaItemClickListener = onHaveToMediaItemClickListener
    }

    /**
     * Definimos la interface que permite extender métodos que el RecyclerView no posee
     */
    interface OnHaveToMediaItemClickListener {
        fun onHaveToMediaItemClickListener(todo: HaveToMedia)
        fun onHaveToMediaItemLongClickListener(todo: HaveToMedia)
    }
}