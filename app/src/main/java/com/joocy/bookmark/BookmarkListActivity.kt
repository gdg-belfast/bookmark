package com.joocy.bookmark

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.joocy.bookmark.data.InMemoryBookmarkRepository
import com.joocy.bookmark.model.Bookmark
import kotlinx.android.synthetic.main.activity_boookmark_list.bookmarkList
import kotlinx.android.synthetic.main.activity_boookmark_list.addBookmarkButton

class BookmarkListActivity : AppCompatActivity() {

    companion object {
        val ACTION_SAVE_BOOKMARK = "com.joocy.action.SAVE_BOOKMARK"
        val ACTION_DISPLAY_BOOKMARK = "com.joocy.action.DISPLAY_BOOKMARK"
        val REQUEST_SAVE_BOOKMARK = 1
    }

    val dataService = InMemoryBookmarkRepository()

    // onCreate will be called by Android wehn this activity is created. This is
    // usually where any configuration is done to get the activity ready to display.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 'when' in Kotlin is like the switch statement in Java. This block
        // is checking the action of the intent that started this activity
        // and acting accordingly.
        when (this.intent.action) {
            Intent.ACTION_SEND -> handleSendIntent(this.intent)
            ACTION_SAVE_BOOKMARK -> handleSaveBookmarkIntent(this.intent)
        }

        setContentView(R.layout.activity_boookmark_list)
        // remember this line!! If you don't set the layoutmanager, the recyclerview won't
        // display anything.
        bookmarkList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bookmarkList.adapter = BookmarkAdapter(this, dataService)

        addBookmarkButton.setOnClickListener { _ ->
            val intent = Intent(this, NewBookmarkActivity::class.java)
            startActivityForResult(intent, REQUEST_SAVE_BOOKMARK)
        }
    }

    // onActivityResult is the callback that Android uses to return the response
    // from an activity that was started using the startActivityForResult method.
    // requestCode will match the code passed to the start method and the result
    // will be either RESULT_OK or RESULT_CANCELLED. The Intent that is passed
    // will contain the response from the other activity. Careful, the other activity
    // may not always send an intent back (hence it's optional here) so always check
    // that it's not null.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SAVE_BOOKMARK) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    handleSaveBookmarkIntent(data)
                }
            }
        }
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
    // Why didn't I just set the NewSharedBookmarkActivity as the intent-filter
    // rather than start it here? No reason. As an exercise, see what it would
    // take to change the application so that NewSharedBookmarkActivity is the
    // intent-filter rather than BookmarkListActivity. (This will need a change
    // in the AndroidManifest.xml file as well as code changes in both activities)
    private fun handleSharedURL(url: String) {
        val newBookmarkIntent = Intent(this, NewSharedBookmarkActivity::class.java)
        newBookmarkIntent.putExtra(NewSharedBookmarkActivity.URL, url)
        startActivity(newBookmarkIntent)
    }


}
