package com.example.yapai.presentation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yapai.R
import com.example.yapai.databinding.ActivityMainBinding
import com.example.yapai.presentation.adapter.PhotoAdapter
import com.example.yapai.presentation.utils.affectOnItemClicks
import com.example.yapai.presentation.viewmodel.FlickrViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

private const val NUM = 100 //picture number per page

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var searchFor = ""
    private val charLength = 0
    private var job = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("Error", exception.toString())
    }
    val flickrViewModel: FlickrViewModel by viewModels()
    private var nextPage: Int = 0
    private val waitTimeForDebounce = 500L
    private var isLoading = false
    private var isLastPage = false
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bindings: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindings.lifecycleOwner = this
        bindings.searchView.addTextChangedListener(watcher)
        val photoList = bindings.photos
        gridLayoutManager =
            GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        val photoAdapter=PhotoAdapter(this@MainActivity, listOf())
        setPhotoList(photoList, photoAdapter) //initialize RecyclerView
        setObserver(photoAdapter)   //set ViewModel Actions and Observers
        photoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 10
                        && firstVisibleItemPosition >= 0
                    ) {
                        isLoading = true
                        flickrViewModel.searchFor(searchFor,1,"9837be3422713b1c45cd4c4df8688d43")
                    }
                }
            }
        })

    }

    /**
     * a Text Watcher for Edit text
     */
    private val watcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText.equals(searchFor, true) || searchText.length <= charLength) {
//                showDefaultView()
                searchFor = searchText
                return
            }

            searchFor = searchText

            coroutineScope.launch(errorHandler) {
                delay(waitTimeForDebounce)
                if (!searchText.equals(searchFor, true) || searchFor.length <= charLength)
                    return@launch
                launch(Dispatchers.Main) {
                    isLoading = true
                    nextPage = 0
                    flickrViewModel.searchFor(searchText,1,"9837be3422713b1c45cd4c4df8688d43")
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
    }

    private fun setObserver(photoAdapter: PhotoAdapter) {
        flickrViewModel.getRecent(NUM)
        flickrViewModel.recentLiveData.observe(this) {
            //adding list of photos to our adapter
            photoAdapter.addAll(it.photos?.photo)
            if (nextPage == it.photos?.pages)
                isLastPage = true
            nextPage = it.photos?.page!!+1
        }
    }

    /**
     * it initializes the RecyclerView
     * @param photoList : the RecyclerView for showing photos
     * @param photoAdapter : adapter to set for RecyclerView
     */
    private fun setPhotoList(
        photoList: RecyclerView,
        photoAdapter: PhotoAdapter
    ) {
        photoList.apply {
            setHasFixedSize(true)
            adapter = photoAdapter
            layoutManager =gridLayoutManager

        }
    }
}