package org.banking.simple.app.features.auth.data

import org.banking.app.features.auth.data.UserDao
import org.banking.simple.app.features.auth.domain.AuthRepository
import org.banking.simple.app.features.auth.domain.UserEntity

class AuthRepositoryImpl(private val authDao: UserDao): AuthRepository {
    override suspend fun submit(authEntity: UserEntity) {
         authDao.insertUser(authEntity)
    }
}