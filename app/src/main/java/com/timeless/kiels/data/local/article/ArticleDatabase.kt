package com.timeless.kiels.data.local.article

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.timeless.kiels.data.local.typeConverter.ArticleTypeConverter

@Database(
    entities = [ArticleEntity::class, SourceEntity::class, StarredArticleEntity::class, StarredSourceEntity::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)
@TypeConverters(ArticleTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val articleDAO : ArticleDAO

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                //Create a new table
                db.execSQL("CREATE TABLE IF NOT EXISTS new_articles_table (id INTEGER PRIMARY KEY, " +
                        "title TEXT NOT NULL, author TEXT NOT NULL, content TEXT NOT NULL, " +
                        "description TEXT NOT NULL, publishedAt TEXT NOT NULL, source TEXT NOT NULL, " +
                        "url TEXT NOT NULL, urlToImage TEXT NOT NULL)"
                )
                //Copy all data from old table to new table
                db.execSQL("INSERT INTO new_articles_table (title, author, content, description," +
                        "publishedAt, source, url, urlToImage)" +
                        "SELECT title, author, content, description, publishedAt, source, url, urlToImage FROM articles_table"
                )

                //Drop old table and change new table name to old table
                db.execSQL("DROP TABLE articles_table")
                db.execSQL("ALTER TABLE new_articles_table RENAME TO articles_table")
            }

        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                //Create a new table
                db.execSQL("CREATE TABLE IF NOT EXISTS starred_articles_table (id INTEGER NOT NULL PRIMARY KEY, " +
                        "title TEXT NOT NULL, author TEXT NOT NULL, content TEXT NOT NULL, " +
                        "description TEXT NOT NULL, publishedAt TEXT NOT NULL, source TEXT NOT NULL, " +
                        "url TEXT NOT NULL, urlToImage TEXT NOT NULL, isStarred INTEGER NOT NULL)"
                )
                db.execSQL("CREATE TABLE IF NOT EXISTS starred_source_table (id TEXT NOT NULL PRIMARY KEY, " +
                        "name TEXT NOT NULL)"
                )
            }

        }

    }

}