package com.example.wb_week_5_2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_5_2.model.Hero
import com.example.wb_week_5_2.repository.RemoteDataSource
import com.example.wb_week_5_2.repository.Repository
import com.example.wb_week_5_2.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

private const val SEVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "ошибка запроса на сервер"
private const val CORRUPTED_DATA = "неполные данные"
private var listHeroes = mutableListOf<Hero>()

class ListHeroViewModel(
    private val listHeroLiveData: MutableLiveData<AppStateList> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {
    fun getLiveData() = listHeroLiveData

    fun getListHeroFromRemoteServer() {
        val countHeroes = 20
        for (id in 1..countHeroes) {
            repositoryImpl.getHeroFromServer(id, callback)
        }
    }

    private val callback = object : Callback<Hero> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<Hero>, response: Response<Hero>) {
            val serverResponse: Hero? = response.body()
            listHeroLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppStateList.Error(Throwable(SEVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Hero>, t: Throwable) {
            listHeroLiveData.postValue(
                AppStateList.Error(Throwable(t?.message ?: REQUEST_ERROR))
            )
        }

        private fun checkResponse(serverResponse: Hero): AppStateList {
            return if (serverResponse == null) {
                AppStateList.Error(Throwable(CORRUPTED_DATA))
            } else {
                listHeroes.add(serverResponse)
                AppStateList.Success(listHeroes)
            }
        }
    }
}