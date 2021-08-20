package com.example.nativeapps.data.viewmodel

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import coil.load
import com.example.nativeapps.repository.firebase.Image
import com.example.nativeapps.repository.firebase.ImageDao
import com.example.nativeapps.repository.firebase.StorageRepository
import com.example.nativeapps.repository.firebase.room.ImageDatabase
import com.example.nativeapps.repository.firebase.room.ImageRepository
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.nativeapps.data.model.Task as ModelTask

class TaskAddViewModel(application: Application): AndroidViewModel(application) {
    private var storageRepository = StorageRepository()
    private var imageRepository: ImageRepository

    init {
        val imageDB = ImageDatabase.getDatabase(application).imageDAO()
        imageRepository = ImageRepository(imageDB)

    }

    fun addAllImages(vararg images: Image) {
        viewModelScope.launch (Dispatchers.IO){
            var newImages: MutableList<Image> = ArrayList()
            images.forEach {
                if(!imageRepository.exists(it.id)){
                    newImages.add(it)
                }
            }
            imageRepository.insertAll(*newImages.toTypedArray())
        }
    }

    fun getCall(): Call<List<Image>> {
        return storageRepository.apiInterface
    }

    fun storeTask(task: ModelTask): Task<Void> {
        return storageRepository.saveTaskItem(task)
    }

    fun load(imageView: ImageView) {
        getCall().enqueue( object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>?, response: Response<List<Image>>?) {
                if(response?.body() != null) {
                    // Save this state in Room
                    addAllImages(*response.body()!!.toTypedArray())
                    imageView.load(response.body()!!.random().downloadUrl)
                }
            }
            override fun onFailure(call: Call<List<Image>>?, t: Throwable?) {
                // use Room if the network call failed
                var roomImages = imageRepository.getAllImages()
                imageView.load(roomImages.random().downloadUrl)
            }
        })
    }

}