package com.vinayismd.tvshowapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vinayismd.tvshowapp.api.ApiService
import com.vinayismd.tvshowapp.models.TvShow.Image
import com.vinayismd.tvshowapp.models.TvShow.TvShowResponse
import com.vinayismd.tvshowapp.models.TvShow.TvShowResponseItem
import com.vinayismd.tvshowapp.repository.TvShowRepository
import junit.framework.Assert
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    lateinit var repo : TvShowRepository
    lateinit var  viewModel: TvShowViewModel
    lateinit var  api: ApiService
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        api = Mockito.spy(ApiService::class.java)
        repo = Mockito.spy(TvShowRepository(api))
        viewModel = TvShowViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getTvShowsSuccess() = runBlockingTest {
        val image = Image("hjbsdjkf", "kjbfkj")
        val show = TvShowResponseItem(1, image, "Hello")
        val show2 = TvShowResponseItem(2, image, "World")
//        val abc = Mockito.mock(TvShowResponse::class.java)
//        abc.add(show)
//        abc.add(show2)

       // Mockito.doReturn(emptyArray()).`when`(repo).getTvShows()
        viewModel._response1.postValue(com.vinayismd.tvshowapp.repository.Response.Success(emptyList()))
        viewModel.responseTvShow.observeForever {  }
        Assert.assertEquals(true, viewModel.responseTvShow.value != null)
    }
}