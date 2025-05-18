package org.banking.simple.app.features.dashboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.banking.simple.app.features.dashboard.domain.CardEntity


@Dao
interface CardDao {

    @Insert
    suspend fun createCard(cardEntity: CardEntity):Long


    @Query("Select * from card where userId=:userId")
    suspend fun getUserCards(userId: Int): List<CardEntity>



}