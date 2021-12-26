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
import org.tuwaiq.carwash.util.Globals
import org.tuwaiq.carwash.util.HelperFunctions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository {
    // register store
    fun registerNewStore(store: Store): MutableLiveData<Store> {
        val mLiveData = MutableLiveData<Store>()
        val userServices = Api.getInstance().create(StoreServices::class.java)
        userServices.registerNewStore(store)
            .enqueue(object : Callback<Store> {
                override fun onResponse(call: Call<Store>, response: Response<Store>) {
                    HelperFunctions.ifResponseNotNullPostValue(mLiveData, response)
                    if (response.isSuccessful) HelperFunctions.saveLoggedInStoreData(response)
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
                HelperFunctions.ifResponseNotNullPostValue(loginLiveData, response)
                if (response.isSuccessful) HelperFunctions.saveLoggedInStoreData(response)
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                Log.d("STORE_LOGIN", "fail t.message: ${t.message}")
            }
        })
        return loginLiveData
    }

    //get all stores
    fun getAllStores(): MutableLiveData<List<Store>> {
        val allStoresLiveDate = MutableLiveData<List<Store>>()
        val storeServices = Api.getInstance().create(StoreServices::class.java)
        storeServices.getAllStores().enqueue(object : Callback<List<Store>> {
            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                HelperFunctions.ifResponseNotNullPostValue(allStoresLiveDate, response)
            }

            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                Log.d("STORE_GET_ALL", "fail t.message: ${t.message}")
            }
        })
        return allStoresLiveDate
    }

    //get a store by id
    fun getStoreById(id: String): MutableLiveData<Store>{
        val oneStoreLiveData = MutableLiveData<Store>()
        val storeServices = Api.getInstance().create(StoreServices::class.java)
        storeServices.getStoreById(id).enqueue(object : Callback<Store> {
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                HelperFunctions.ifResponseNotNullPostValue(oneStoreLiveData,response)
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                Log.d("STORE_BY_ID", "fail t.message: ${t.message}")
            }
        })

        return oneStoreLiveData
    }

    // update store
    fun updateStoreInfo(id: String, store: Store): MutableLiveData<Store> {
        val updatedStoreLiveData = MutableLiveData<Store>()
        val storeServices = Api.getInstance().create(StoreServices::class.java)
        storeServices.updateStoreInfo(id, store).enqueue(object : Callback<Store> {
            override fun onResponse(call: Call<Store>, response: Response<Store>) {
                HelperFunctions.ifResponseNotNullPostValue(updatedStoreLiveData, response)
                if (response.isSuccessful) HelperFunctions.saveLoggedInStoreData(response)
            }

            override fun onFailure(call: Call<Store>, t: Throwable) {
                Log.d("STORE_UPDATE", "fail t.message: ${t.message}")
            }
        })
        return updatedStoreLiveData
    }

}