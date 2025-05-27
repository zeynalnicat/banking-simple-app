package org.banking.simple.app.features.profile.domain.usecases

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.profile.domain.ProfileRepository
import org.banking.simple.app.features.profile.domain.models.UserDTO

class UpdateNameUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(userId:Int,name: String): Result<Unit>{
      return repository.updateName(userId,name)
    }
}