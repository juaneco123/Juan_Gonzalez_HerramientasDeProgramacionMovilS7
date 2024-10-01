package com.example.juan_gonzalez_herramientasdeprogramacionmovils7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeriadosAdapter(private val feriados: List<String>) : RecyclerView.Adapter<FeriadosAdapter.FeriadoViewHolder>() {
    class FeriadoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.listViewFeriados)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeriadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_feriados,parent, false)
        return FeriadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeriadoViewHolder, position: Int) {
        holder.textView.text = feriados[position]
    }

    override fun getItemCount() = feriados.size
}
