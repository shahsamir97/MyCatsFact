package com.mdshahsamir.mycatsfact.ui.factslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mdshahsamir.mycatsfact.databinding.FactListItemBinding
import com.mdshahsamir.mycatsfact.model.Animal

class FactsListAdapter() : RecyclerView.Adapter<FactsListAdapter.FactViewHolder>() {

    private var data : List<Animal> = ArrayList()

    inner class FactViewHolder(private val binding: FactListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(animal : Animal) {
            binding.animalNameTextView.text = animal.name
            binding.animalFactTextView.text = animal.fact
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = FactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun submitData(data : List<Animal>){
        this.data = data
        notifyDataSetChanged()
    }
}