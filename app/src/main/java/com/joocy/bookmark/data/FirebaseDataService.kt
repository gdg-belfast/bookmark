package com.joocy.bookmark.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.joocy.bookmark.model.Bookmark

/**
 * Created by garet on 24/09/2017.
 */
class FirebaseDataService() : DataService {

    val database: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance().getReference("bookmark")
    }

    override fun getAllBookmarks(): List<Bookmark> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveBookmark(bookmark: Bookmark) {
        database.setValue(bookmark)
    }

    override fun count(): Int {
        return 0
    }
}