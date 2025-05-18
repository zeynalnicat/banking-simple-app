package org.banking.simple.app.features.new_card.data


    import org.banking.simple.app.features.dashboard.data.local.CardDao
    import org.banking.simple.app.features.dashboard.domain.CardEntity
    import org.banking.simple.app.features.new_card.domain.NewCardRepository

    class NewCardRepositoryImpl(private val cardDao: CardDao?=null): NewCardRepository {


        override suspend fun createCard(cardEntity: CardEntity) {
            cardDao?.createCard(cardEntity)
        }


    }