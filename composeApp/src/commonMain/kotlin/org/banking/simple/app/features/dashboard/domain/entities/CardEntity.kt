package org.banking.simple.app.features.dashboard.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.banking.simple.app.features.auth.domain.UserEntity


@Entity(
    tableName = "cards",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var cardName: String,
    var userId : Int,
    var balance: Int = 0,
    var cardColor: String,
    var lastDigits: String,
)
