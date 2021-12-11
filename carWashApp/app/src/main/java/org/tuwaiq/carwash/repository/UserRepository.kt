package org.tuwaiq.carwash.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.UserServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun registerNewUser(user: User): LiveData<User>{
        val mLiveData = MutableLiveData<User>()
        val userServices = Api.getInstance().create(UserServices::class.java)
        userServices.registerNewUser(user)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    mLiveData.postValue(response.body())
                    Log.d("USER_REGISTER_SUCCESS","success: ${response.body()}")
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("USER_REGISTER_FAIL","fail: ${t.message}")
                }

            })

        return mLiveData
    }

}