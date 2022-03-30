package com.vinayismd.tvshowapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vinayismd.tvshowapp.databinding.TvShowLayoutAdapterBinding
import com.vinayismd.tvshowapp.models.TvShow.TvShowResponseItem

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {


    inner class MyViewHolder(val viewBinding: TvShowLayoutAdapterBinding) : RecyclerView.ViewHolder(viewBinding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TvShowResponseItem>() {
        override fun areItemsTheSame(oldItem: TvShowResponseItem, newItem: TvShowResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowResponseItem, newItem: TvShowResponseItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

   // private val list:List<TvShowResponseItem> = emptyList()

    var tvShows: List<TvShowResponseItem>
      get() = differ.currentList
      set(value) {
          differ.submitList(value)
      }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TvShowLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentShow = tvShows[position]
       holder.viewBinding.apply {
           textView.text = currentShow.name
           imageView.load(currentShow.image.original){
               crossfade(true)
               crossfade(1000)
           }
       }

    }

    override fun getItemCount(): Int {
        return  tvShows.size
    }


}