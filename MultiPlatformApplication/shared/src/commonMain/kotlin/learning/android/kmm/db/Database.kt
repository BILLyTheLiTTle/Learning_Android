package learning.android.kmm.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    /*
    When you do a clean install (without having the version on your device at all) you go to the
    correct schema version but the SQL queries are not getting executed.
    In order to avoid this issue you need the code which is in init.
    This way you force, somehow, to execute the SQL queries in the correct sqm file!
     */
    init {
        AppDatabase.Schema.migrate(
            driver = databaseDriverFactory.createDriver(),
            oldVersion = 0,
            newVersion = AppDatabase.Schema.version
        )
    }

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAll()
        }
    }

    internal fun insertItem(id: Long, content: String) {
        dbQuery.insertItem(
            id = id,
            content = content
        )
    }

    internal fun selectItem(id: Long): Flow<String> {
        val result = dbQuery.selectById(
            id = id
        ).asFlow()
            .mapToOne()
            .map {
                it.content
            }
        return result
    }

    internal fun updateItem(id: Long, content: String) {
        dbQuery.updateById(content = content, id = id)
    }

    internal fun getVersion(): Flow<String> {
        val result = dbQuery.getVersion()
            .asFlow()
            .mapToOne()
            .map {
                "$it(${AppDatabase.Schema.version})"
            }
        return result
    }
}