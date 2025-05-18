package org.banking.simple.app.features.auth.domain

interface AuthRepository {

    suspend fun submit(authEntity: UserEntity)
}