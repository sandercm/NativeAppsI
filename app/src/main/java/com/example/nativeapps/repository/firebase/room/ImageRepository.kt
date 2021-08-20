package com.example.nativeapps.repository.firebase.room


import androidx.lifecycle.LiveData
import com.example.nativeapps.repository.firebase.Image
import com.example.nativeapps.repository.firebase.ImageDao

class ImageRepository(private val imageDao: ImageDao) {
    fun exists(id: String): Boolean =
        imageDao.exists(id)

    fun insertAll(vararg images: Image) =
        imageDao.insertAll(*images)

    fun getAllImages(): List<Image> = imageDao.getAll()
}