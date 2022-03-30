package com.vinayismd.tvshowapp.repository

import com.vinayismd.tvshowapp.api.ApiService
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTvShows() = apiService.getTvShows()

    suspend fun getTvEpisodes() = apiService.getTvEpisodes()
}