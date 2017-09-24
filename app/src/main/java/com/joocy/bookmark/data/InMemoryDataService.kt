package com.joocy.bookmark.data

import com.joocy.bookmark.model.Bookmark

/**
 * Created by garet on 24/09/2017.
 */
class InMemoryDataService: DataService {

    var bookmarks: List<Bookmark> = listOf(
            Bookmark("https://google.com", "Google"),
            Bookmark("https://ycombinator.com", "Hacker News")
    )

    override fun getAllBookmarks(): List<Bookmark> {
        return listOf<Bookmark>(*bookmarks.toTypedArray())
    }

    override fun saveBookmark(bookmark: Bookmark) {
        bookmarks += bookmark
    }

    override fun count(): Int {
        return bookmarks.size
    }
}