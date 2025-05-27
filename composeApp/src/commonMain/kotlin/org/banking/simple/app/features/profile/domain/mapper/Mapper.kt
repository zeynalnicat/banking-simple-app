package org.banking.simple.app.features.profile.domain.mapper

import org.banking.simple.app.features.auth.domain.UserEntity
import org.banking.simple.app.features.profile.domain.models.UserDTO


fun UserEntity.toModel(): UserDTO{
   return UserDTO(
       id = this.id,
       name = this.name
   )
}

//fun UserDTO.toEntity(): UserEntity{
//    return UserDTO(
//        id = this.id,
//        name = this.name,
//
//    )
//}

//fun UserDTO.toModel(): UserEntity{
//    return UserEntity(
//        id = this.id,
//        name = this.name,
////
//    )
//}