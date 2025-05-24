package org.banking.simple.app.features.pin_entry.domain

import org.banking.simple.app.core.data.Result

class OnInsertPinUseCase(private val repository: EntryRepository) {

    suspend operator fun invoke(pin:String): Result<Unit>{
         return repository.insertPin(pin)
    }
}