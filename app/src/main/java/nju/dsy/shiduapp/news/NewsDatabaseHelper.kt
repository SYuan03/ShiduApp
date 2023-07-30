package nju.dsy.shiduapp.news

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class NewsDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // 设置一个tag
    private val TAG = "newsDatabaseHelper"

    companion object {
        private const val DATABASE_NAME = "news_database_3"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "news_table_3"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_SUMMARY = "summary"
        const val COLUMN_URL = "url"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_IMAGE_URL TEXT,
                $COLUMN_SUMMARY TEXT,
                $COLUMN_URL TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
        Log.i(TAG, "onCreate: $createTableQuery")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

}
