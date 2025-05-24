package org.banking.simple.app.features.auth.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.domain.AuthRepository
import org.banking.simple.app.features.auth.domain.UserEntity


class AuthRepositoryImpl(private val authDao: UserDao): AuthRepository {
    override suspend fun submit(authEntity: UserEntity):Result<Int> {
        try {
            val response = authDao.insertUser(authEntity)
            if(response !=-1L ){
                return Result.Success(response.toInt())
            }
            return Result.Error("Unknown Error occurred")

        }catch (e: Exception){
            return Result.Error(e.message?: "Unknown Error Occurred")
        }

    }
}