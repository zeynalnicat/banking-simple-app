package org.banking.simple.app

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.domain.AuthRepository
import org.banking.simple.app.features.auth.domain.UserEntity

//class MockAuthRepository : AuthRepository {
//    private val fakeUser = UserEntity(id = 1, name = "Mock User", pin = "123456")
//
//    override suspend fun submit(authEntity: UserEntity): Result<Int> {
////         return Result.Success(fakeUser)
//    }
//}