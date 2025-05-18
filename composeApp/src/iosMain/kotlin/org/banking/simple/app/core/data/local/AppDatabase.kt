package org.banking.simple.app.core.data.local

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory



fun getDatabaseBuilder(): AppDatabase {
    val dbFilePath = NSHomeDirectory() + "/banking.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory = {AppDatabase::class.instantiateImpl()}
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}
