package com.rodrigo.javier.eox.hackermen.routerpointer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.rodrigo.javier.eox.hackermen.routerpointer.databinding.ItemPointBinding
import com.rodrigo.javier.eox.hackermen.routerpointer.model.entities.DeliveryPointEntity
import com.rodrigo.javier.eox.hackermen.routerpointer.utilities.interfaces.ListenerWholeRoute

class DeliveryListingAdapter(
    private var list: MutableList<DeliveryPointEntity> = mutableListOf(),
    private val context: Context,
    private var listener: ListenerWholeRoute
) : RecyclerView.Adapter<DeliveryListingAdapter.BaseViewHolder<*>>() {

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    inner class MyOwnViewHolder(val binding: ItemPointBinding) :
        BaseViewHolder<DeliveryPointEntity>(binding.root) {

        override fun bind(item: DeliveryPointEntity) = with(binding) {
            this.textViewAddress.text = item.street.toString().toUpperCase()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        var binding = ItemPointBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        val holder = MyOwnViewHolder(binding)

        binding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                ?: return@setOnClickListener

            this.listener.viewTouchedShort(list[position], position)
        }

        binding.imageViewPhonecall.setOnLongClickListener {
            val position = holder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                ?: return@setOnLongClickListener true
            YoYo.with(Techniques.Shake)
                .duration(450)
                .repeat(5)
                .playOn(binding.carviewItem)

            this.listener.viewTouchedLong(list[position], position)

            return@setOnLongClickListener true
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MyOwnViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setProductosEnLaVista(pointEntities: MutableList<DeliveryPointEntity>?) {
        if (pointEntities != null) {
            this.list = pointEntities
            notifyDataSetChanged()
        }
    }

    fun getProductos(position: Int): DeliveryPointEntity {
        return list[position]
    }

}