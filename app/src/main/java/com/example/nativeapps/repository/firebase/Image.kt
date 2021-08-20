package com.example.nativeapps.repository.firebase

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
    fun getAll(): List<Image>

    @Query("SELECT * FROM image WHERE id IS (:imageId)")
    fun loadAllByIds(imageId: String): Image

    @Insert
    fun insertAll(vararg users: Image)

    @Delete
    fun delete(image: Image)

    @Query("SELECT EXISTS (SELECT 1 FROM image WHERE id = :id)")
    fun exists(id: String): Boolean
}
