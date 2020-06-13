package sbnri.rakshit.nawani.ui.dashboard

import android.content.Context
import androidx.lifecycle.MutableLiveData
import sbnri.rakshit.nawani.R
import sbnri.rakshit.nawani.core.BaseViewModel
import sbnri.rakshit.nawani.db.dao.SBNRIDao
import sbnri.rakshit.nawani.extension.with
import sbnri.rakshit.nawani.io.RequestAPIs

class MainViewModel(
    private val api: RequestAPIs,
    private val context: Context,
    private val sbnriDao: SBNRIDao
) : BaseViewModel() {

    val adapter = MainAdapter()

    private var errorMsg = MutableLiveData<String>()
    private var showLoading = MutableLiveData<Boolean>()

    private var noDataAvailable = MutableLiveData<Boolean>()
    private var noMoreData = MutableLiveData<Boolean>()

    private val perPage = "10"

    fun showLoading(): MutableLiveData<Boolean> {
        return showLoading
    }

    fun noMoreDataObserver(): MutableLiveData<Boolean> {
        return noMoreData
    }

    fun noDataAvailableObserver(): MutableLiveData<Boolean> {
        return noDataAvailable
    }

    fun errorMessageObserver(): MutableLiveData<String> {
        return errorMsg
    }


    fun getData(page: Int) {
        if (isConnectedToInternet(context)) {

            addToDisposable(
                api.getRepos(page.toString(), perPage).with()
                    .doOnSubscribe { showLoading.value = true }
                    .doOnSuccess { showLoading.value = false }
                    .doOnError { showLoading.value = false }
                    .subscribe({

                        for (i in it) {
                            adapter.addItem(i)
                            sbnriDao.insertDetails(i)
                            noDataAvailable.value = false
                        }

                        if (it.isEmpty()) {
                            noMoreData.value = true
                        }


                    }, {
                        // handle errors
                        it.localizedMessage
                    })
            )
        } else {

            val savedData = sbnriDao.allData()
            if (savedData.isEmpty()) {
                errorMsg.value = context.getString(R.string.no_internet)
                noDataAvailable.value = true
            } else {
                adapter.clear()
                adapter.addItems(savedData)
                noDataAvailable.value = false
            }
        }

    }
}