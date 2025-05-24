package org.banking.simple.app.features.auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.banking.simple.app.features.auth.domain.UserEntity


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("Select name from users where id=:userId")
    suspend fun getName(userId:Int):String

    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE pin = :pin)")
    suspend fun enterPin(pin: String): Boolean

    @Query("Select * from users where id=:userId")
    suspend fun getUser(userId:Int): UserEntity


}