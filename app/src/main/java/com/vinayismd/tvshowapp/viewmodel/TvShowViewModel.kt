package com.vinayismd.tvshowapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinayismd.tvshowapp.models.TvShow.TvShowResponseItem
import com.vinayismd.tvshowapp.models.episode.TvEpisodesItem
import com.vinayismd.tvshowapp.repository.Response
import com.vinayismd.tvshowapp.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val repository: TvShowRepository): ViewModel() {

    private val _response1 = MutableLiveData<Response<List<TvShowResponseItem>>>()
    val responseTvShow: LiveData<Response<List<TvShowResponseItem>>>
      get() = _response1

    private val _response2 = MutableLiveData<Response<List<TvEpisodesItem>>>()
    val responseTvEpisodes: LiveData<Response<List<TvEpisodesItem>>>
        get() = _response2

    init {
       viewModelScope.async {
           getAllTvShows()
          // withContext(Dispatchers.IO) {
           delay(5000)// added delay
           getAllEpisodes()

         //  }
       }
    }

    fun getAllTvShows() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val data = repository.getTvShows()
            if (data.body() != null) {
                _response1.postValue(Response.Success(data.body()))
            } else {
                _response1.postValue(Response.Error("Something Went Wrong!"))
            }
        } catch (e: Exception) {
            _response1.postValue(Response.Error("Something Went Wrong!"))

        }
    }


    fun getAllEpisodes() = viewModelScope.launch {
        try {
            val data = repository.getTvEpisodes()
            if (data.body() != null) {
                _response2.postValue(Response.Success(data = data.body()))
            } else {
                _response2.postValue(Response.Error("Something Went Wrong!"))
            }
        } catch (e: Exception) {
            _response2.postValue(Response.Error("Something Went Wrong!"))
        }
    }
}