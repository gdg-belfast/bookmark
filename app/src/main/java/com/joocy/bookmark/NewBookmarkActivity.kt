package com.joocy.bookmark

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.joocy.bookmark.model.Bookmark

import kotlinx.android.synthetic.main.activity_new_bookmark.*

class NewBookmarkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_bookmark)

        val bookmark: Bookmark = Bookmark.new()
        displayBookmark(bookmark)
        saveButton.setOnClickListener { _ ->
            saveBookmark(updatedBookmark())
        }
        cancelButton.setOnClickListener { _ ->
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun displayBookmark(bookmark: Bookmark) {
        urlEditText.setText(bookmark.url)
        nameEditText.setText(bookmark.name)
    }

    private fun updatedBookmark(): Bookmark {
        val url = urlEditText.text.toString()
        val name = nameEditText.text.toString()
        return Bookmark(url, name)
    }

    private fun saveBookmark(bookmark: Bookmark) {
        val intent = Intent(BookmarkListActivity.ACTION_SAVE_BOOKMARK)
        intent.putExtra("bookmark", bookmark)
        setResult(RESULT_OK, intent)
        finish()
    }
}