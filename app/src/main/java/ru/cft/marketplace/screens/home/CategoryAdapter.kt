package ru.cft.marketplace.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.cft.marketplace.R
import ru.cft.marketplace.databinding.ItemCategoryBinding
import ru.cft.marketplace.util.Category

interface OnCategoryClickListener {
    fun onCategoryClick(item: Category)
}

class CategoryAdapter(
    private val clickListener: OnCategoryClickListener
) : ListAdapter<Category, CategoryAdapter.ItemViewHolder>(Diff) {

    private var selected: Category? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.onCategoryClick(item)
            notifyDataSetChanged()
        }
    }

    inner class ItemViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.categoryName.context

        fun bind(item: Category) {
            item.setDetails(
                binding.categoryName,
                binding.image
            )
            with(binding) {
                if (selected != item) {
                    if (item.isSelected()) {
                        categoryName.setTextColor(ContextCompat.getColor(context, R.color.black))
                        layout.setBackgroundResource(R.drawable.seleted_category)
                        DrawableCompat.setTint(
                            binding.image.drawable,
                            ContextCompat.getColor(context, R.color.white)
                        )
                        selected?.clickReact()
                        selected = item
                    } else {
                        categoryName.setTextColor(ContextCompat.getColor(context, R.color.category))
                        layout.setBackgroundResource(R.drawable.category_background)
                        DrawableCompat.setTint(
                            binding.image.drawable,
                            ContextCompat.getColor(context, R.color.black)
                        )
                    }
                } else {
                    categoryName.setTextColor(ContextCompat.getColor(context, R.color.category))
                    layout.setBackgroundResource(R.drawable.category_background)
                    DrawableCompat.setTint(
                        binding.image.drawable,
                        ContextCompat.getColor(context, R.color.black)
                    )
                }
            }

        }
    }

    companion object Diff : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem.equals(newItem)
    }
}