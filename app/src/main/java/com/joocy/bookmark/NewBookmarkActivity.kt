package com.joocy.bookmark

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.joocy.bookmark.model.Bookmark
import kotlinx.android.synthetic.main.activity_new_bookmark.*

class NewBookmarkActivity : AppCompatActivity() {

    companion object {
        const val URL = "url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_bookmark)

        val bookmark: Bookmark = getBookmark()
        displayBookmark(bookmark)
        saveButton.setOnClickListener { view ->
            saveBookmark(updatedBookmark())
        }
    }

    // getBookmark looks for a url in the intent
    // that started this activity. If it finds one,
    // it will return a new Bookmark using it. If not,
    // it will return a Bookmark created using the
    // default values for url and name.
    private fun getBookmark(): Bookmark {
        // the 'this' is not strictly required, but here
        // we're just being as explicit as possible.
        if (this.intent != null) {
            val url = this.intent.getStringExtra(URL)
            if (url != null) {
                return Bookmark.withURL(url)
            } else {
                return Bookmark.new()
            }
        } else {
            return Bookmark.new()
        }
    }

    // displayBookmark will display the contents of bookmark
    private fun displayBookmark(bookmark: Bookmark) {
        urlEditText.setText(bookmark.url)
        nameEditText.setText(bookmark.name)
    }

    // updatedBookmark returns a new Bookmark with the
    // contents of this activity
    private fun updatedBookmark(): Bookmark {
        val url = urlEditText.text.toString()
        val name = nameEditText.text.toString()
        return Bookmark(url, name)
    }

    // saveBookmark will fire the ACTION_SAVE_BOOKMARK intent
    private fun saveBookmark(bookmark: Bookmark) {
        val intent = Intent(BookmarkListActivity.ACTION_SAVE_BOOKMARK)
        intent.putExtra("bookmark", bookmark)
        startActivity(intent)
    }
}
