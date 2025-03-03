package com.br.jc.list_your_product.display.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.databinding.MovieCardBinding
import com.br.jc.list_your_product.rest.model.Movie


class MovieDisplayAdapter(val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieDisplayAdapter.MovieViewHolder>() {
    //private val movieList: ArrayList<Movie> =  ArrayList();


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position] // Supondo que tenha uma lista chamada movieList

        holder.title.text = movie.title
        holder.image.setImageResource(R.drawable.icone) // Se estiver usando um recurso local
        // Ou use Glide/Picasso para carregar imagens de URL:
        // Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.image)


    }

    class MovieViewHolder(binding: MovieCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.imageCard
        val title: TextView = binding.titleCard

    }
}