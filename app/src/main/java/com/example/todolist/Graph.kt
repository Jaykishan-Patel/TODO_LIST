package com.example.todolist

import android.content.Context
import androidx.room.Room
import com.example.todolist.Data.WishDataBase
import com.example.todolist.Data.WishRespository

object Graph {
     lateinit var dataBase: WishDataBase
    val wishRespository by lazy {
        WishRespository(dataBase.wishDao())
    }
    fun provide(context: Context){
        dataBase= Room.databaseBuilder(context,WishDataBase::class.java,"wishlist.db").build()
    }
}