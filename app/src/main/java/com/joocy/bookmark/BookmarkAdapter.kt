package com.joocy.bookmark

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.joocy.bookmark.data.DataService
import com.joocy.bookmark.model.Bookmark


/**
 * Created by garet on 24/09/2017.
 */
class BookmarkAdapter(val context: Context, val dataservice: DataService): RecyclerView.Adapter<BookmarkViewholder>() {
    val bookmarks = dataservice.getAllBookmarks()

    override fun getItemCount(): Int = dataservice.count()

    override fun onBindViewHolder(holder: BookmarkViewholder?, position: Int) {
        Log.d("BookmarkAdapter", "Displaying bookmark at position " + position.toString())
        holder?.displayBookmark(bookmarks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookmarkViewholder {
        Log.d("BookmarkAdapter", "Creating view holder")
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_bookmark_item, parent, false)
        return BookmarkViewholder(context, view)
    }

}

class BookmarkViewholder(context: Context, view: View): RecyclerView.ViewHolder(view) {
    val bookmarkName: TextView
    val bookmarkUrl: TextView
    val bookmarkRow: View

    init {
        bookmarkName = view.findViewById<TextView>(R.id.bookmarkName)
        bookmarkUrl = view.findViewById<TextView>(R.id.bookmarkURL)
        bookmarkRow = view.findViewById<View>(R.id.bookmarkRow)
        bookmarkRow.setOnClickListener { _ ->
            val intent = Intent(BookmarkListActivity.ACTION_DISPLAY_BOOKMARK)
            intent.putExtra("bookmark", Bookmark(bookmarkUrl.text.toString(), bookmarkName.text.toString()))
            context.startActivity(intent)
        }
    }

    fun displayBookmark(bookmark: Bookmark) {
        Log.d("BookmarkViewHolder", "Displaying bookmark")
        bookmarkName.setText(bookmark.name)
        bookmarkUrl.setText(bookmark.url)
    }

}