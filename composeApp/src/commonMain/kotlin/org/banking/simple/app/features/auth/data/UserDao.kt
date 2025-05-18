package org.banking.simple.app.features.auth.data

import androidx.room.Dao
import androidx.room.Insert
import org.banking.simple.app.features.auth.domain.UserEntity


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long


}