package org.banking.simple.app.features.pin_entry.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.pin_entry.domain.EntryRepository

class EntryRepositoryImpl(private val userDao: UserDao): EntryRepository {
    override suspend fun getUsername(userId:Int): Result<String> {
        try {
           val response = userDao.getName(userId)
           return Result.Success(response)
        }catch (e: Exception){
            return Result.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun insertPin(pin: String): Result<Unit> {
        try {
           val response = userDao.enterPin(pin)
           if(response){
               return Result.Success(Unit)
           }
            return Result.Error("Wrong Pin!")

        }catch (e: Exception){
            return Result.Error(e.message ?: "Unknown Error")
        }
    }
}