package org.banking.simple.app.features.pin_entry.domain

import org.banking.simple.app.core.data.Result

interface EntryRepository {

    suspend fun getUsername(userId:Int): Result<String>

    suspend fun insertPin(pin:String):Result<Unit>
}