package com.example.nativeapps.repository.firebase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nativeapps.repository.firebase.Image
import com.example.nativeapps.repository.firebase.ImageDao


@Database(
    entities = [Image::class],
    version = 1
)
abstract class ImageDatabase: RoomDatabase() {

    abstract fun imageDAO(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Context): ImageDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImageDatabase::class.java,
                    "room_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}