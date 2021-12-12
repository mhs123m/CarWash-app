package org.tuwaiq.carwash.views.storeLoginAndRegister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.repository.StoreRepository

class StoreLogInViewModel: ViewModel() {

    val storeRepository = StoreRepository()

    fun registerNewStore(store: Store): LiveData<Boolean> {
        val mLiveData = MutableLiveData<Boolean>()

        storeRepository.registerNewStore(store).observeForever {
                mLiveData.postValue(true)
            }

        return mLiveData
    }
}