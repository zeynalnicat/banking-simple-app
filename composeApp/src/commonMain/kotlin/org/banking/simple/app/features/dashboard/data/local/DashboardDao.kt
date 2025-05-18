package org.banking.simple.app.features.dashboard.data.local

import androidx.room.Dao
import androidx.room.Query


@Dao
interface DashboardDao {


    @Query("Select * from cards")
    suspend fun getAll()

}