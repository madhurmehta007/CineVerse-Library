package org.android.cineverse.shared.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.android.cineverse.shared.database.CineVerseDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(CineVerseDatabase.Schema, "cineverse.db")
    }
}
