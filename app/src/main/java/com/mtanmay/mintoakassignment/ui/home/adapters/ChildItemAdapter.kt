package com.mtanmay.mintoakassignment.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtanmay.domain.models.BaseChildItem
import com.mtanmay.domain.models.ChildItemMID
import com.mtanmay.domain.models.ChildItemTID
import com.mtanmay.mintoakassignment.databinding.ChildDataItemBinding

class ChildItemAdapter(
    private val items: List<BaseChildItem>
) : RecyclerView.Adapter<ChildItemAdapter.ChildItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemVH {
        val binding = ChildDataItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChildItemVH(binding)
    }

    override fun onBindViewHolder(holder: ChildItemVH, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ChildItemVH(
        private val binding: ChildDataItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BaseChildItem) {
            binding.apply {
                if (item is ChildItemMID)
                    binding.itemSortId.text = "MID: ${item.mid}"
                else if (item is ChildItemTID)
                    binding.itemSortId.text = "TID: ${item.tid}"

                itemAmount.text = "AMOUNT: ${item.amount}"
                itemNarration.text = "NARRATION: ${item.narration}"
            }
        }
    }
}