package com.vinayismd.tvshowapp.api

import com.vinayismd.tvshowapp.helper.Constants
import com.vinayismd.tvshowapp.models.TvShow.TvShowResponse
import com.vinayismd.tvshowapp.models.episode.TvEpisodesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT1)
    suspend fun  getTvShows(): Response<TvShowResponse>


    @GET(Constants.END_POINT2)
    suspend fun getTvEpisodes(): Response<TvEpisodesResponse>
}