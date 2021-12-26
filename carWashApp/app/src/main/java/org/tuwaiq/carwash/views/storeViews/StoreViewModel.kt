package org.tuwaiq.carwash.views.storeViews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.repository.StoreRepository

class StoreViewModel: ViewModel() {

    private val storeRepository = StoreRepository()
    var registerLiveData = MutableLiveData<Store>()
    var loginLiveData = MutableLiveData<Store>()
    var allStoresLiveData = MutableLiveData<List<Store>>()
    var updatedStoreLiveData = MutableLiveData<Store>()
    var oneStoreLiveData = MutableLiveData<Store>()

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
    //update updated data with response from repo
    fun updateStoreInfo(id:String,store: Store){
        updatedStoreLiveData = storeRepository.updateStoreInfo(id, store)
    }
    // set live data of one store by id
    fun getStoreById (id: String){
        oneStoreLiveData = storeRepository.getStoreById(id)
    }
}