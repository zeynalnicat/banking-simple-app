package org.banking.simple.app.features.profile.data

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.data.UserDao
import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.profile.domain.ProfileRepository
import org.banking.simple.app.features.profile.domain.mapper.toModel
import org.banking.simple.app.features.profile.domain.models.UserDTO

class ProfileRepositoryImpl(private val userDao: UserDao): ProfileRepository {
    override suspend fun getUser(userId: Int): Result<UserDTO> {
         try {
             val response = userDao.getUser(userId)
             return Result.Success(response.toModel())
         }catch (e: Exception){
              return Result.Error(e.message ?: "Unknown Error")
         }
    }

    override suspend fun updateName(userId: Int, name: String): Result<Unit> {
         try {
             val response = userDao.updateName(userId,name)
             return Result.Success(Unit)
         }catch (e: Exception){
             return Result.Error(e.message ?: "Unknown Error")
         }
    }
}