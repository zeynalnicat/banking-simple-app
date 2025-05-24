package org.banking.simple.app.features.profile.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.profile.domain.ProfileRepository
import org.banking.simple.app.features.profile.domain.models.UserDTO

class GetUserUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(userId:Int): Result<UserDTO>{
        return repository.getUser(userId)
    }
}