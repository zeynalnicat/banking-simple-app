@file:OptIn(InternalCoroutinesApi::class)


import android.content.Context

import androidx.room.Room


import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.InternalCoroutinesApi
import org.banking.simple.app.core.data.local.AppDatabase


fun getDatabaseBuilder(ctx: Context): AppDatabase {
    val dbFile = ctx.getDatabasePath("banking.db")
    return Room.databaseBuilder<AppDatabase>(
        context = ctx.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}

