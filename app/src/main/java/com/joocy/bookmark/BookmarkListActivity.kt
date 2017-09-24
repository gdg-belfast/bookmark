package com.joocy.bookmark

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.joocy.bookmark.data.InMemoryDataService
import com.joocy.bookmark.model.Bookmark
import kotlinx.android.synthetic.main.activity_boookmark_list.bookmarkList

class BookmarkListActivity : AppCompatActivity() {

    companion object {
        val ACTION_SAVE_BOOKMARK = "com.joocy.action.SAVE_BOOKMARK"
    }

    val dataService = InMemoryDataService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If this activity has been started because of an intent filter
        // being fired, the intent that fired the filter will be available
        // through by calling getIntent().
        // In Kotlin, properties are available by name, so 'intent'is
        // equivalent to getIntent in Java.
        when (this.intent.action) {
            Intent.ACTION_SEND -> handleSendIntent(this.intent)
            ACTION_SAVE_BOOKMARK -> handleSaveBookmarkIntent(this.intent)
        }

        setContentView(R.layout.activity_boookmark_list)
        bookmarkList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bookmarkList.adapter = BookmarkAdapter(dataService)
    }

    private fun handleSendIntent(sendIntent: Intent) {
        if ("text/plain".equals(sendIntent.type)) {
            val url = sendIntent.getStringExtra(Intent.EXTRA_TEXT)
            handleSharedURL(url)
        }
    }

    private fun handleSaveBookmarkIntent(saveIntent: Intent) {
        val bookmark = saveIntent.getParcelableExtra<Bookmark>("bookmark")
        Log.d("Bookmark", "Saving bookmark with name " + bookmark.name)
        dataService.saveBookmark(bookmark)
    }

    // handleSharedURL will start a new activity to display the shared
    // URL and ask the user to provide a name.
    private fun handleSharedURL(url: String) {
        val newBookmarkIntent = Intent(this, NewBookmarkActivity::class.java)
        newBookmarkIntent.putExtra(NewBookmarkActivity.URL, url)
        startActivity(newBookmarkIntent)
    }

}
