package com.mtanmay.mintoakassignment.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtanmay.domain.models.BaseParentItem
import com.mtanmay.domain.models.ParentItemMID
import com.mtanmay.domain.models.ParentItemTID
import com.mtanmay.mintoakassignment.R
import com.mtanmay.mintoakassignment.databinding.ParentDataItemBinding

class ParentItemAdapter(
    private var items: List<BaseParentItem>
): RecyclerView.Adapter<ParentItemAdapter.ParentItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentItemVH {
        val binding = ParentDataItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ParentItemVH(binding)
    }

    override fun onBindViewHolder(holder: ParentItemVH, position: Int) {
        holder.onBind(items[position]) {
            closeExpandedTiles(holder.adapterPosition)
            notifyItemChanged(holder.adapterPosition)
        }
    }

    private fun closeExpandedTiles(position: Int) {
        val expandedTileIndex = items.indexOfFirst { it.isExpanded }

        if (expandedTileIndex >= 0 && expandedTileIndex != position) {
            items[expandedTileIndex].isExpanded = false
            notifyItemChanged(expandedTileIndex)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ParentItemVH(
        private val binding: ParentDataItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: BaseParentItem, onExpandAction: () -> Unit) {
            binding.apply {
                when (item) {
                    is ParentItemMID -> {
                        itemMid.text = "MID: ${item.mid}"

                        childRv.apply {
                            layoutManager = LinearLayoutManager(binding.root.context)
                            setHasFixedSize(true)
                            adapter = ChildItemAdapter(item.entries)
                        }

                        val isExpanded = item.isExpanded

                        childRv.visibility = if (isExpanded) View.VISIBLE else View.GONE

                        expandBtn.setImageResource(
                            if (isExpanded) R.drawable.ic_arrow_up
                            else R.drawable.ic_arrow_down
                        )

                        binding.root.setOnClickListener {
                            onExpandAction()
                            item.isExpanded = !item.isExpanded
                        }
                    }
                    is ParentItemTID -> {
                        itemMid.text = "TID: ${item.tid}"

                        childRv.apply {
                            layoutManager = LinearLayoutManager(binding.root.context)
                            setHasFixedSize(true)
                            adapter = ChildItemAdapter(item.entries)
                        }

                        val isExpanded = item.isExpanded

                        childRv.visibility = if (isExpanded) View.VISIBLE else View.GONE

                        expandBtn.setImageResource(
                            if (isExpanded) R.drawable.ic_arrow_up
                            else R.drawable.ic_arrow_down
                        )

                        binding.root.setOnClickListener {
                            onExpandAction()
                            item.isExpanded = !item.isExpanded
                        }
                    }
                }
            }
        }
    }
}