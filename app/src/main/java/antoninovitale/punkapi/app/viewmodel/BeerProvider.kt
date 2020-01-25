package antoninovitale.punkapi.app.viewmodel

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import antoninovitale.punkapi.app.api.PunkService.apiClient
import antoninovitale.punkapi.app.api.model.Beer
import antoninovitale.punkapi.app.util.DebugLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by antoninovitale on 28/08/2017.
 */
class BeerProvider : ViewModel() {
    private val currentStatus: MutableLiveData<CurrentStatus?>? = MutableLiveData()
    private val selectedBeer: MutableLiveData<Beer?>? = MutableLiveData()
    //TODO convert API models to UI models eventually
    private var beers: MutableLiveData<List<Beer?>?>? = null

    fun getBeers(): LiveData<List<Beer?>?>? {
        if (beers == null) {
            beers = MutableLiveData()
            loadBeers()
        }
        return beers
    }

    fun loadBeers() {
        setCurrentStatus(refreshing = true, error = false)
        //instead of getting a random beer I download the full list
        val call = apiClient?.getBeers(null, null)
        call?.enqueue(object : Callback<List<Beer?>?> {
            override fun onResponse(@NonNull call: Call<List<Beer?>?>?, @NonNull response: Response<List<Beer?>?>) {
                var error = false
                if (response.isSuccessful && response.body() != null) {
                    beers?.setValue(response.body())
                } else {
                    DebugLog.log("loadBeers", "there was an error")
                    error = true
                }
                setCurrentStatus(false, error)
            }

            override fun onFailure(@NonNull call: Call<List<Beer?>?>?, @NonNull t: Throwable?) {
                DebugLog.log("loadBeers", "" + t!!.message, t)
                setCurrentStatus(refreshing = false, error = true)
            }
        })
    }

    fun setSelectedBeer(position: Int) {
        val beersValue = beers?.value
        if (beersValue != null && position < beersValue.size) {
            selectedBeer?.value = beersValue[position]
        }
    }

    fun getSelectedBeer(): MutableLiveData<Beer?>? {
        return selectedBeer
    }

    fun setSelectedBeer(beer: Beer?) {
        if (beer != null && selectedBeer?.value == null) {
            selectedBeer?.value = beer
        }
    }

    private fun setCurrentStatus(refreshing: Boolean, error: Boolean) {
        currentStatus?.value = CurrentStatus(refreshing, error)
    }

    fun getCurrentStatus(): MutableLiveData<CurrentStatus?>? {
        return currentStatus
    }
}