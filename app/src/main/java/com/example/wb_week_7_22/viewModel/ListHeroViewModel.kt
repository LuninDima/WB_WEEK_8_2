package com.example.wb_week_7_22.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_7_22.App
import com.example.wb_week_7_22.model.DATA_HEROES_PREFERENCES_KEY
import com.example.wb_week_7_22.model.Hero
import com.example.wb_week_7_22.model.NAME_APP_PREFERENCES
import com.example.wb_week_7_22.repository.LocalDataSource
import com.example.wb_week_7_22.repository.RemoteDataSource
import com.example.wb_week_7_22.repository.Repository
import com.example.wb_week_7_22.repository.RepositoryImpl
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


private const val SEVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "ошибка запроса на сервер"
private var listHeroes = arrayListOf<Hero>()


class ListHeroViewModel(
    private val listHeroLiveData: MutableLiveData<
            AppStateList> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl(RemoteDataSource(), LocalDataSource())
) : ViewModel() {
    fun getLiveData() = listHeroLiveData

    fun getDataHeroes() {
        val isExistDataHeroesInPreferences = App.getSApplicationContext()
            .getSharedPreferences(NAME_APP_PREFERENCES, Context.MODE_PRIVATE).all.containsKey(
                DATA_HEROES_PREFERENCES_KEY
            )
        if (isExistDataHeroesInPreferences) {
            listHeroes = repositoryImpl.getHeroFromSharedPreferences()
            listHeroLiveData.postValue(
                setData(listHeroes)
            )

        } else {
            getListHeroFromRemoteServer()
        }
    }

    fun getListHeroFromRemoteServer() {
        repositoryImpl.getHeroFromServer(callback)
    }

    private val callback = object : Callback<ArrayList<Hero>> {
        @Throws(IOException::class)
        override fun onResponse(call: Call<ArrayList<Hero>>, response: Response<ArrayList<Hero>>) {
            listHeroes = response.body()!!
            val json = Gson()
            val jsonListHeroes = json.toJson(listHeroes)
            repositoryImpl.saveDataHeroesToSharedPreferences(jsonListHeroes)
            listHeroLiveData.postValue(
                if (response.isSuccessful) {
                    setData(listHeroes)
                } else {
                    AppStateList.Error(Throwable(SEVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<ArrayList<Hero>>, t: Throwable) {
            listHeroLiveData.postValue(
                AppStateList.Error(Throwable(t.message ?: REQUEST_ERROR))
            )
        }
    }

    private fun setData(serverResponse: ArrayList<Hero>): AppStateList {
        return AppStateList.Success(serverResponse)
    }
}