package com.example.sportapp.presentation.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sportapp.data.model.EQUIPMENT
import com.example.sportapp.databinding.ItemEquipmentBinding

class EquipmentAdapter: RecyclerView.Adapter<EquipmentAdapter.VenueViewHolder>()
{
    private var equipments: List<EQUIPMENT> = listOf()

    class VenueViewHolder(val binding: ItemEquipmentBinding): RecyclerView.ViewHolder(binding.root)
    {
        val strEquipment = binding.strEquipment
        val strType = binding.strType
        val strSeason = binding.strSeason
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentAdapter.VenueViewHolder {
        val binding = ItemEquipmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VenueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EquipmentAdapter.VenueViewHolder, position: Int) {
        val equipment = equipments[position]
        holder.binding.apply {
            strEquipment.load(equipment.strEquipment)
            when(equipment.strType)
            {
                "1st" -> strType.text = "Home"
                "2nd" -> strType.text = "Away"
                "3rd" -> strType.text = "Third kit"
            }
            strSeason.text = equipment.strSeason
        }
    }

    override fun getItemCount() = equipments.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateEquipments(newEquipments: List<EQUIPMENT>) {
        equipments = newEquipments
        notifyDataSetChanged()
    }
}