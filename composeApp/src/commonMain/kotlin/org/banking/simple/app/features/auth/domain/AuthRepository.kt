package org.banking.simple.app.features.auth.domain

import org.banking.simple.app.core.data.Result

interface AuthRepository {

    suspend fun submit(authEntity: UserEntity): Result<Int>
}