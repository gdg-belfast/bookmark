package com.joocy.bookmark

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.joocy.bookmark.data.BookmarkRepositoryListener

import com.joocy.bookmark.data.BookmarkRepository
import com.joocy.bookmark.model.Bookmark


/**
 * BookmarkAdapter is the class responsible for telling the Recyclerview on the main activity
 * how to display bookmarks. It sits between the Recyclerview component and the data source
 * as well as providing the view holder that the Recyclerview uses to actually display the
 * bookmarks.
 *
 * When the Recyclerview is displayed, it will ask the adapter how many items there are to
 * display (by calling getItemCount()), and ask the adapter to create a viewholder (the callback
 * onCreateViewHolder(ViewGroup?, int) will be called). Then, for every item that the Recyclerview
 * needs to display, it will call the onBindViewHolder(ViewHolder?, int) method so that the
 * adapter can bind the data at the position requested to the viewholder supplied.
 */
class BookmarkAdapter(val context: Context, val dataservice: BookmarkRepository): RecyclerView.Adapter<BookmarkViewholder>(),BookmarkRepositoryListener
{
    override fun getItemCount(): Int = dataservice.count()

    override fun onBindViewHolder(holder: BookmarkViewholder?, position: Int) {
        Log.d("BookmarkAdapter", "Displaying bookmark at position " + position.toString())
        val bookmarks = dataservice.getAllBookmarks()
        holder?.displayBookmark(bookmarks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookmarkViewholder {
        Log.d("BookmarkAdapter", "Creating view holder")
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_bookmark_item, parent, false)
        return BookmarkViewholder(context, view)
    }

    override fun onRepositoryUpdate() {
        notifyDataSetChanged()
    }


}

/**
 * BookmarkViewholder is a wrapper around the view used by the RecyclerView to display
 * a line in the list. The ViewHolder is responsible for taking a Bookmark and binding
 * its members to the components in the view.
 */
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