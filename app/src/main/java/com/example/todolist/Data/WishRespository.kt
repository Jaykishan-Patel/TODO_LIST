package com.example.todolist.Data

import kotlinx.coroutines.flow.Flow

class WishRespository(private val wishDao: WishDao) {
    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getAllWish():Flow<List<Wish>>{
        return wishDao.getAllWishes()
    }

    fun getAWishById(id:Long):Flow<Wish>{
        return wishDao.getAWishById(id)
    }

    suspend fun updateAWish(wish: Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }

}