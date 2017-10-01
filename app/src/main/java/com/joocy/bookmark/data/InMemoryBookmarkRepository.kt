package com.joocy.bookmark.data

import com.joocy.bookmark.model.Bookmark

/**
 * Created by garet on 24/09/2017.
 */
class InMemoryBookmarkRepository : BookmarkRepository {

    var bookmarks = listOf<Bookmark>(
            Bookmark("https://google.com", "Google"),
            Bookmark("https://ycombinator.com", "Hacker News")
    )

    var listeners = listOf<BookmarkRepositoryListener>()

    override fun getAllBookmarks(): List<Bookmark> {
        return listOf<Bookmark>(*bookmarks.toTypedArray())
    }

    override fun saveBookmark(bookmark: Bookmark) {
        bookmarks += bookmark
        notifyListeners()
    }

    override fun count(): Int {
        return bookmarks.size
    }

    override fun addRepositoryListener(listener: BookmarkRepositoryListener) {
        listeners += listener
    }

    private fun notifyListeners() {
        for (listener in listeners) {
            listener.onRepositoryUpdate()
        }
    }
}