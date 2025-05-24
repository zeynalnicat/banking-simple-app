package org.banking.simple.app.features.dashboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.banking.simple.app.features.dashboard.domain.entities.CardEntity


@Dao
interface CardDao {

    @Insert
    suspend fun createCard(cardEntity: CardEntity):Long


    @Query("SELECT * FROM cards where userId=:userId")
    suspend fun getUserCards(userId: Int): List<CardEntity>

    @Query("Select * from cards where userId=:userId and id=:id")
    suspend fun getCardDetails(userId: Int,id:Int): CardEntity


    @Query("Select balance from cards where userId=:userId and id=:id ")
    suspend fun getBalance(userId: Int,id: Int):Int


    @Insert
    suspend fun insertCard(cardEntity: CardEntity): Long

    @Query("UPDATE cards SET balance = :newBalance WHERE userId = :userId AND id = :id")
    suspend fun updateBalance(userId: Int, id: Int, newBalance: Int)


}