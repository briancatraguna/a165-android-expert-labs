package com.briancatraguna.reactivesearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.briancatraguna.reactivesearch.network.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel: ViewModel() {

    private val accessToken = "pk.eyJ1IjoiYnJpYW5jYXRyYWd1bmEiLCJhIjoiY2twdGo3NWlvMGh2NTJuczRqaTB6bXAxdyJ9.TBmQ5Jduey4JSlRExhnSUA"
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            ApiConfig.provideApiService().getCountry(it,accessToken).features
        }
        .asLiveData()

}