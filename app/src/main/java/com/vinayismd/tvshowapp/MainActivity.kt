package com.vinayismd.tvshowapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinayismd.tvshowapp.adapter.TvEpisodeAdapter
import com.vinayismd.tvshowapp.adapter.TvShowAdapter
import com.vinayismd.tvshowapp.databinding.ActivityMainBinding
import com.vinayismd.tvshowapp.viewmodel.TvShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TvShowViewModel
    //val viewModel: TvShowViewModel by viewModels()
    private lateinit var tvShowAdapte: TvShowAdapter
    private lateinit var tvEpisodeAdapter: TvEpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)

        //supportActionBar?.hide() // tohide the action bar
        setupRV()
    }

    fun setupRV(){
        tvShowAdapte = TvShowAdapter()
        tvEpisodeAdapter = TvEpisodeAdapter()

        binding.recyclerView.apply {
            adapter = tvShowAdapte
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvEpisodes.apply {
            adapter = tvEpisodeAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvRecentlyAdded.apply {
            adapter = tvShowAdapte
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        viewModel.responseTvShow.observe(this , {
            Log.d("VINAY", it.toString())
            when(it){
                is com.vinayismd.tvshowapp.repository.Response.Success -> {
                    tvShowAdapte.tvShows = it.data!!
                    Log.d("VINAY", "Show Success")
                }
                else -> {
                    tvShowAdapte.tvShows = emptyList()
                    Toast.makeText(this , "Please check you Internet Connection!!" , Toast.LENGTH_LONG).show()
                    Log.d("VINAY", "Show Fail")
                }
            }

        })


        viewModel.responseTvEpisodes.observe(this , {
            when (it) {
                is com.vinayismd.tvshowapp.repository.Response.Success -> {
                    tvEpisodeAdapter.tvEpisodes = it.data!!
                    Log.d("VINAY", "Episode Success")
                }
                else -> {
                    tvEpisodeAdapter.tvEpisodes = emptyList()
                    Toast.makeText(this, "SomeThing Went Wrong!", Toast.LENGTH_LONG).show()
                    Log.d("VINAY", "Episode Fail")
                }
            }
        })
    }
}