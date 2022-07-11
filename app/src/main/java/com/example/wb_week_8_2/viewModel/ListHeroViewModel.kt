package com.example.wb_week_8_2.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_8_2.App
import com.example.wb_week_8_2.model.DATA_HEROES_PREFERENCES_KEY
import com.example.wb_week_8_2.model.Hero
import com.example.wb_week_8_2.model.NAME_APP_PREFERENCES
import com.example.wb_week_8_2.repository.LocalDataSource
import com.example.wb_week_8_2.repository.RemoteDataSource
import com.example.wb_week_8_2.repository.Repository
import com.example.wb_week_8_2.repository.RepositoryImpl
import com.github.terrakok.cicerone.Router
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


private const val SEVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "ошибка запроса на сервер"
private var listHeroes = arrayListOf<Hero>()


class ListHeroViewModel (
  var repositoryImpl: Repository
) : ViewModel() {
    var listHeroLiveData: MutableLiveData<AppStateList> = MutableLiveData()

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

     fun setData(serverResponse: ArrayList<Hero>): AppStateList {
        return AppStateList.Success(serverResponse)
    }
}