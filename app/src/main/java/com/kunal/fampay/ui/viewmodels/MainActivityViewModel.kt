package com.kunal.fampay.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kunal.fampay.data.network.models.CardGroups
import com.kunal.fampay.data.repositories.CardsGroupRepository
import com.kunal.fampay.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import org.greenrobot.eventbus.EventBus
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(
    private val repository: CardsGroupRepository,
):ViewModel() {

    lateinit var cardGroupsResp: LiveData<List<CardGroups>>

    fun getCardGroups(){
        Coroutines.main {
            try {
                EventBus.getDefault().post(CallStartEvent())
                val response = repository.getCardGroups()
                cardGroupsResp = response
                EventBus.getDefault().post(CallSuccessEvent())
            }catch (e: ApiException){
                Log.i("err",e.message.toString())
                EventBus.getDefault().post(CallFailEvent(e.message.toString()))
            }catch (e: NoInternetException){
                Log.i("err",e.message.toString())
                EventBus.getDefault().post(CallFailEvent(e.message.toString()))
            }catch (e: ConnectException){
                Log.i("err",e.message.toString())
                EventBus.getDefault().post(CallFailEvent(e.message.toString()))
            }
        }
    }
}