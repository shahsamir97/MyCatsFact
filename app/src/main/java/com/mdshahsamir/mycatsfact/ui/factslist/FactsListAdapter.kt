package com.mdshahsamir.mycatsfact.ui.factslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mdshahsamir.mycatsfact.databinding.FactListItemBinding
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat

class FactsListAdapter(
    private val factListItemActions: FactListItemActions,
    private val glideRequestManager: RequestManager,
) : ListAdapter<Animal, FactsListAdapter.CatFactViewHolder>(FactDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactViewHolder {
        val binding = FactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CatFactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatFactViewHolder, position: Int) {
        holder.bind(getItem(position) as Cat)
    }

    inner class CatFactViewHolder(private val binding: FactListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            cat.also { cat ->
                binding.animalNameTextView.text = cat.name
                binding.animalFactTextView.text = cat.fact
                binding.animaImageView.transitionName = cat.imageLink
                glideRequestManager.load(cat.imageLink).into(binding.animaImageView)
                binding.root.setOnClickListener { factListItemActions.onClick(cat, binding.animaImageView) }
            }
        }
    }

    companion object FactDiffUtil : DiffUtil.ItemCallback<Animal>() {
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.uniqueKey() == newItem.uniqueKey()
        }

        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem == newItem
        }
    }
}
