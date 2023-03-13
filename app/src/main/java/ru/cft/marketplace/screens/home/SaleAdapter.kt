package ru.cft.marketplace.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.cft.domain.models.FlashSale
import ru.cft.marketplace.databinding.ItemFlashBinding

class SaleAdapter (private val list: List<FlashSale>) :
    RecyclerView.Adapter<SaleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFlashBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemFlashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlashSale) {
            with(binding) {
                tvCategory.text = item.category
                btnOff.text = item.discount.toString()+"% off"
                tvBrandName.text = item.name
                tvBrandPrice.text = item.price.toString()
                Glide.with(ivImage)
                    .load(item.image_url)
                    .timeout(500)
                    .into(ivImage)
            }
        }
    }
}