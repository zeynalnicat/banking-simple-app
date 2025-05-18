package org.banking.simple.app.features.dashboard.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.banking.simple.app.features.auth.domain.UserEntity


@Entity(tableName = "Transactions", foreignKeys = [ForeignKey(entity = CardEntity::class, parentColumns =["id"], childColumns = ["cardId"] ), ForeignKey(entity = UserEntity::class, parentColumns = ["id"], childColumns = ["userId"])], indices = [
    Index(value = ["cardId"]),
    Index(value = ["userId"])
] )
data class TransactionHistory(
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,
    var cardId : Int,
    var userId: Int,
    var total : Int ,
    var isExpense: Boolean,
    var type: TransactionType,
)


enum class TransactionType {
    FOOD,
    HEALTH,
    ELECTRICITY,
    MOBILE_CREDIT,
    MERCHANT,
    ASSURANCE
}
