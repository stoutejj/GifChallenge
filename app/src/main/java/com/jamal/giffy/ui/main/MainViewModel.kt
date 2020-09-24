package com.jamal.giffy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamal.giffy.data.model.GiffyImage
import com.jamal.giffy.data.repository.GiffyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: GiffyRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _state = MutableLiveData<MainViewState>()
    val state: LiveData<MainViewState>
        get() = _state

    fun fetchTrendingGifs() {
        _state.postValue(MainViewState.Loading)
        disposable.add(
            repository.fetchTrendingGifs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.data.isEmpty()) {
                        _state.postValue(
                            MainViewState.Error("No Data Found")
                        )
                    } else {
                        _state.postValue(MainViewState.Success(response.data))
                    }
                }, { throwable ->
                    _state.postValue(
                        MainViewState.Error(
                            throwable.localizedMessage ?: "Unknown Error"
                        )
                    )
                })
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    sealed class MainViewState {
        object Loading : MainViewState()
        data class Success(val images: List<GiffyImage>) : MainViewState()
        data class Error(val errorMessage: String) : MainViewState()
    }

}