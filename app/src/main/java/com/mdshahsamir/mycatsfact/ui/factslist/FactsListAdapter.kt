package com.mdshahsamir.mycatsfact.ui.factslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mdshahsamir.mycatsfact.databinding.FactListItemBinding
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat

class FactsListAdapter(private val factListItemActions: FactListItemActions) :
    RecyclerView.Adapter<FactsListAdapter.FactViewHolder>() {

    private var data: List<Animal> = ArrayList()

    inner class FactViewHolder(private val binding: FactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {
            (animal as Cat).let { cat ->
                binding.animalNameTextView.text = cat.name
                binding.animalFactTextView.text = cat.fact
                binding.animaImageView.transitionName = cat.imageLink
                Glide.with(binding.root.context).load(cat.imageLink).into(binding.animaImageView)
                binding.root.setOnClickListener { factListItemActions.onClick(cat, binding.animaImageView) }
            }
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
