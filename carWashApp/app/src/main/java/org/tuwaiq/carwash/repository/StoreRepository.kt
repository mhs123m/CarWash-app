package org.tuwaiq.carwash.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.StoreServices
import org.tuwaiq.carwash.network.UserServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository {

    fun registerNewStore(store: Store): LiveData<Store> {
        val mLiveData = MutableLiveData<Store>()
        val userServices = Api.getInstance().create(StoreServices::class.java)
        userServices.registerNewStore(store)
            .enqueue(object : Callback<Store> {
                override fun onResponse(call: Call<Store>, response: Response<Store>) {
                    if (response.isSuccessful)
                        mLiveData.postValue(response.body())
                    else {
                        Log.d("USER_REGISTER", "failure message: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Store>, t: Throwable) {
                    Log.d("USER_REGISTER","fail t message: ${t.message}")
                }

            })

        return mLiveData
    }
}