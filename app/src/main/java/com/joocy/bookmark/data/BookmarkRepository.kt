package com.joocy.bookmark.data

import com.joocy.bookmark.model.Bookmark

/**
 * Created by garet on 24/09/2017.
 */
interface BookmarkRepository {

    // returns a list of all bookmarks saved
    // in the backing store
    fun getAllBookmarks(): List<Bookmark>

    // saves a bookmark to the backing store
    fun saveBookmark(bookmark: Bookmark)

    // returns the number of bookmarks in the
    // backing store
    fun count(): Int

    // registers a listener to be notified when the the repository
    // is updated.
    fun addRepositoryListener(listener: BookmarkRepositoryListener)

}