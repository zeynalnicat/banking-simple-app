package org.banking.simple.app.features.auth.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int =0,
    var name: String ,
    var pin: String,

)
