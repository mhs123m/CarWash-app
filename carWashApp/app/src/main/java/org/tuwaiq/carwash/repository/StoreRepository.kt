package org.tuwaiq.carwash.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.StoreServices
import org.tuwaiq.carwash.network.UserServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository {

    // register user
    fun registerNewStore(store: Store): MutableLiveData<Store> {
        val mLiveData = MutableLiveData<Store>()
        val userServices = Api.getInstance().create(StoreServices::class.java)
        userServices.registerNewStore(store)
            .enqueue(object : Callback<Store> {
                override fun onResponse(call: Call<Store>, response: Response<Store>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                        Log.d("USER_REGISTER", "success res.body: ${response.body()}")
                    }else {
                        Log.d("USER_REGISTER", "fail res.message: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<Store>, t: Throwable) {
                    Log.d("USER_REGISTER_FAIL", "fail t.message: ${t.message}")
                }

            })

        return mLiveData
    }


    //logIn store
    fun storeLogIn(loginModel: LoginModel): MutableLiveData<Store> {
        val loginLiveData = MutableLiveData<Store>()
        val storeServices = Api.getInstance().create(StoreServices::class.java)
        storeServices.storeLogIn(loginModel).enqueue(object : Callback<Store> {
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                if (response.isSuccessful) {
                    loginLiveData.postValue(response.body())
                    Log.d("STORE_LOGIN", "success res.body: ${response.body()}")
                } else {
                    Log.d("STORE_LOGIN", "fail res.message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                Log.d("STORE_LOGIN", "fail t.message: ${t.message}")
            }

        })

        return loginLiveData
    }
}