package com.example.nativeapps.repository.firebase

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity
class Image {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    var id: String = ""
    @ColumnInfo(name = "author")
    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
    @ColumnInfo(name = "download_url")
    @SerializedName("download_url")
    @Expose
    var downloadUrl: String? = null
}

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): LiveData<List<Image>>

    @Query("SELECT * FROM image WHERE id IS (:imageId)")
    fun loadAllByIds(imageId: String): Image

    @Insert
    fun insertAll(vararg users: Image)

    @Delete
    fun delete(image: Image)

    @Query("SELECT EXISTS (SELECT 1 FROM image WHERE id = :id)")
    fun exists(id: Int): Boolean
}