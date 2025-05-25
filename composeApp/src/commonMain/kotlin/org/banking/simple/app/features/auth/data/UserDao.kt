package org.banking.simple.app.features.auth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.banking.simple.app.features.auth.domain.UserEntity


@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE name = :name AND pin = :pin LIMIT 1")
    suspend fun getUserByNameAndPin(name: String, pin: String): UserEntity?

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    suspend fun loginOrInsert(user: UserEntity): UserEntity {
        val existingUser = getUserByNameAndPin(user.name, user.pin)
        return existingUser ?: run {
            val newId = insertUser(user)
            user.copy(id = newId.toInt())
        }
    }

    @Query("Select name from users where id=:userId")
    suspend fun getName(userId:Int):String

    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE pin = :pin)")
    suspend fun enterPin(pin: String): Boolean

    @Query("Select * from users where id=:userId")
    suspend fun getUser(userId:Int): UserEntity


}