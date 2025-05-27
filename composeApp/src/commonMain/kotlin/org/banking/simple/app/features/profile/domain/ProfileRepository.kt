package org.banking.simple.app.features.profile.domain

import org.banking.simple.app.core.data.Result
import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.profile.domain.models.UserDTO

interface ProfileRepository {

    suspend fun getUser(userId:Int): Result<UserDTO>

    suspend fun updateName(userId: Int,name: String):Result<Unit>
}