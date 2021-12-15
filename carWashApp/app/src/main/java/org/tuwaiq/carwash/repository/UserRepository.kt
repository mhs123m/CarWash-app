package org.tuwaiq.carwash.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.network.Api
import org.tuwaiq.carwash.network.UserServices
import org.tuwaiq.carwash.util.HelperFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    // register user
    fun registerNewUser(user: User): MutableLiveData<User> {
        val mLiveData = MutableLiveData<User>()
        val userServices = Api.getInstance().create(UserServices::class.java)
        userServices.registerNewUser(user)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        mLiveData.postValue(response.body())
                        Log.d("USER_REGISTER", "success res.body: ${response.body()}")
                        HelperFunctions.saveLoggedInUserData(response)
                    } else {
                        Log.d("USER_REGISTER", "fail res.message: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d("USER_REGISTER", "fail t.message: ${t.message}")
                }

            })

        return mLiveData
    }

    //logIn user
    fun userLogIn(loginModel: LoginModel): MutableLiveData<User> {
        val mLiveData = MutableLiveData<User>()
        val userServices = Api.getInstance().create(UserServices::class.java)
        userServices.userLogIn(loginModel).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    mLiveData.postValue(response.body())
                    Log.d("USER_LOGIN", "success res.body: ${response.body()}")
                    HelperFunctions.saveLoggedInUserData(response)
                } else {
                    Log.d("USER_LOGIN", "fail res.message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("USER_LOGIN", "fail t.message: ${t.message}")
            }

        })

        return mLiveData
    }

}