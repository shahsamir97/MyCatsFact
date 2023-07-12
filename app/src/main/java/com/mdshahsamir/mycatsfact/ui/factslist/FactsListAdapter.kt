package com.mdshahsamir.mycatsfact.ui.factslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mdshahsamir.mycatsfact.databinding.FactListItemBinding
import com.mdshahsamir.mycatsfact.model.Animal
import kotlinx.coroutines.coroutineScope

class FactsListAdapter(private val factListItemActions: FactListItemActions) :
    RecyclerView.Adapter<FactsListAdapter.FactViewHolder>() {

    private var data: List<Animal> = ArrayList()

    inner class FactViewHolder(private val binding: FactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {
            binding.animalNameTextView.text = animal.name
            binding.animalFactTextView.text = animal.fact
            Glide.with(binding.root.context).load(animal.imageLink).into(binding.animaImageView)
            binding.root.setOnClickListener { factListItemActions.onClick(animal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding =
            FactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitData(data: List<Animal>) {
        this.data = data
        notifyDataSetChanged()
    }
}