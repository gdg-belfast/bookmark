package com.joocy.bookmark

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
class BookmarkAdapter(val dataservice: DataService): RecyclerView.Adapter<BookmarkViewholder>() {
    val bookmarks = dataservice.getAllBookmarks()

    override fun getItemCount(): Int = dataservice.count()

    override fun onBindViewHolder(holder: BookmarkViewholder?, position: Int) {
        Log.d("BookmarkAdapter", "Displaying bookmark at position " + position.toString())
        holder?.displayBookmark(bookmarks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookmarkViewholder {
        Log.d("BookmarkAdapter", "Creating view holder")
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_bookmark_item, parent, false)
        return BookmarkViewholder(view)
    }

}

class BookmarkViewholder(view: View): RecyclerView.ViewHolder(view) {
    val bookmarkName: TextView
    val bookmarkUrl: TextView

    init {
        bookmarkName = view.findViewById<TextView>(R.id.bookmarkName)
        bookmarkUrl = view.findViewById<TextView>(R.id.bookmarkURL)
    }

    fun displayBookmark(bookmark: Bookmark) {
        Log.d("BookmarkViewHolder", "Displaying bookmark")
        bookmarkName.setText(bookmark.name)
        bookmarkUrl.setText(bookmark.url)
    }
}