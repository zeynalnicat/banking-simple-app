package org.banking.simple.app.features.new_card.data


    import org.banking.simple.app.core.data.Result
    import org.banking.simple.app.features.dashboard.data.local.CardDao
    import org.banking.simple.app.features.dashboard.domain.CardDTO
    import org.banking.simple.app.features.dashboard.domain.entities.CardEntity
    import org.banking.simple.app.features.dashboard.domain.mapper.toEntity
    import org.banking.simple.app.features.new_card.domain.NewCardRepository

    class NewCardRepositoryImpl(private val cardDao: CardDao?=null): NewCardRepository {


        override suspend fun createCard(card: CardDTO): Result<Unit> {
            try {
                val response =  cardDao?.createCard(card.toEntity())
                if(response!=-1L){
                    return Result.Success(Unit)
                }
                return Result.Error("Unknown Error occurred")
            }catch (e: Exception){
                return Result.Error(e.message?:"Unknown Error occurred")
            }

        }


    }