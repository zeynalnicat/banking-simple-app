package org.banking.simple.app.features.dashboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.banking.simple.app.features.dashboard.domain.entities.TransactionHistory


@Dao
interface TransactionDao {


    @Insert
    suspend fun insertTransaction(transactionHistory: TransactionHistory):Long

    @Query("Select * from Transactions where userId=:userId")
    suspend fun getTransactions(userId:Int,):List<TransactionHistory>

    @Query("Select * from Transactions where userId=:userId and cardId=:cardId")
    suspend fun getTransactionsDue(userId:Int, cardId:Int):List<TransactionHistory>


}