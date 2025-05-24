package org.banking.simple.app.features.pin_entry.presentation

sealed class EntryIntent {

    data class OnHandleNumberPress(val number:String): EntryIntent()
    data object HandleBackPress: EntryIntent()
    data object OnHandleClear: EntryIntent()
    data object OnHandleShow: EntryIntent()

}