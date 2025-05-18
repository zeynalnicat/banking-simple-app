package org.banking.simple.app.features.auth.domain


class AuthUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(authEntity: UserEntity){
        return authRepository.submit(authEntity)
    }
}