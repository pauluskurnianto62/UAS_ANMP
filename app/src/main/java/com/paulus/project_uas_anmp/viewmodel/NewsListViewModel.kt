package com.paulus.project_uas_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.paulus.project_uas_anmp.model.News
import com.paulus.project_uas_anmp.model.NewsDatabase
import com.paulus.project_uas_anmp.util.buildNewsDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsListViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val todoLD = MutableLiveData<List<News>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildNewsDb(getApplication())

            todoLD.postValue(db.newsDao().selectAllNews())
            loadingLD.postValue(false)
        }
    }

    fun clearNews(news: News) {
        launch {
            val db = NewsDatabase.buildDatabase(
                getApplication()
            )
            db.newsDao().deleteNews(news)

            todoLD.postValue(db.newsDao().selectAllNews())
        }
    }
}