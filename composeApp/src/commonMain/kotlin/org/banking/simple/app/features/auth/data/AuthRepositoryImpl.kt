package org.banking.simple.app.features.auth.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.domain.AuthRepository
import org.banking.simple.app.features.auth.domain.UserEntity


class AuthRepositoryImpl(private val authDao: UserDao): AuthRepository {
    override suspend fun submit(authEntity: UserEntity):Result<Int> {
        try {
            val user = authDao.loginOrInsert(authEntity)
                return Result.Success(user.id)
        }catch (e: Exception){
            return Result.Error(e.message?: "Unknown Error Occurred")
        }

    }
}