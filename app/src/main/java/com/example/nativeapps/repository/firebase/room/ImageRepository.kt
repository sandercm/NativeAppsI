package com.example.nativeapps.repository.firebase.room


import androidx.lifecycle.LiveData
import com.example.nativeapps.repository.firebase.Image
import com.example.nativeapps.repository.firebase.ImageDao

class ImageRepository(private val imageDao: ImageDao) {

    suspend fun insertAll(vararg images: Image) =
        imageDao.insertAll(*images)

    fun getAllImages(): LiveData<List<Image>> = imageDao.getAll()
}