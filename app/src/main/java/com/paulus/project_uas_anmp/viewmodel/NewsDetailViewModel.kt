package com.paulus.project_uas_anmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.paulus.project_uas_anmp.model.News
import com.paulus.project_uas_anmp.util.buildNewsDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsDetailViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val newsLD = MutableLiveData<News>()

    fun addNews(list: List<News>) {
        launch {
            val db = buildNewsDb(getApplication())
            db.newsDao().insertAll(*list.toTypedArray())
        }
    }

    fun fetch(id:Int) {
        launch {
            val db = buildNewsDb(getApplication())
            newsLD.postValue(db.newsDao().selectNews(id))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}