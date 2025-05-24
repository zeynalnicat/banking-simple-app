package org.banking.simple.app.features.auth.domain

import org.banking.simple.app.core.data.Result


class AuthUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(authEntity: UserEntity): Result<Int>{
        return authRepository.submit(authEntity)
    }
}