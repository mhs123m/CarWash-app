package org.tuwaiq.carwash.views.storeLoginAndRegister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.repository.StoreRepository

class StoreLogInViewModel: ViewModel() {

    private val storeRepository = StoreRepository()
    var registerLiveData = MutableLiveData<Store>()
    var loginLiveData = MutableLiveData<Store>()
    var allStoresLiveData = MutableLiveData<List<Store>>()

    // get live data of store from repo to view model
    fun registerNewStore(store: Store) {
        registerLiveData =  storeRepository.registerNewStore(store)
    }

    // get live data of store from repo to view model
    fun storeLogIn(loginModel: LoginModel){
        loginLiveData = storeRepository.storeLogIn(loginModel)
    }

    // get live data of store from repo to view model
    fun getAllStores(){
        allStoresLiveData = storeRepository.getAllStores()
    }
}