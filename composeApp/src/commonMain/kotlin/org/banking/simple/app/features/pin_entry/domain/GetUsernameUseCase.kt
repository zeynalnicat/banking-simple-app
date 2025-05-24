package org.banking.simple.app.features.pin_entry.domain

import org.banking.simple.app.core.data.Result

class GetUsernameUseCase(private val repository: EntryRepository) {

    suspend operator fun invoke(userId:Int): Result<String>{
        return repository.getUsername(userId)
    }
}