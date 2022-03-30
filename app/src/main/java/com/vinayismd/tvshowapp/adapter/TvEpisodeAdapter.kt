package com.vinayismd.tvshowapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.vinayismd.tvshowapp.databinding.TvShowLayoutAdapterBinding
import com.vinayismd.tvshowapp.models.episode.TvEpisodesItem

class TvEpisodeAdapter: RecyclerView.Adapter<TvEpisodeAdapter.MyViewHolder>() {

    inner class MyViewHolder(val viewBinding: TvShowLayoutAdapterBinding): RecyclerView.ViewHolder(viewBinding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TvEpisodesItem>() {
        override fun areItemsTheSame(oldItem: TvEpisodesItem, newItem: TvEpisodesItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvEpisodesItem, newItem: TvEpisodesItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var tvEpisodes: List<TvEpisodesItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return  MyViewHolder(TvShowLayoutAdapterBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentShow = tvEpisodes[position]
        holder.viewBinding.apply {
            textView.text = currentShow.name
            imageView.load(currentShow.image.original){
                crossfade(true)
                crossfade(1000)
                //transformations(CircleCropTransformation())
            }
        }
    }

    override fun getItemCount(): Int {
        return  tvEpisodes.size
    }
}