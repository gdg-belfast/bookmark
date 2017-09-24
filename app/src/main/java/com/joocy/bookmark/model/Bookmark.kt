package com.joocy.bookmark.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by garet on 22/09/2017.
 */

data class Bookmark(val url: String, val name: String): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bookmark> {
        override fun createFromParcel(parcel: Parcel): Bookmark {
            return Bookmark(parcel)
        }

        override fun newArray(size: Int): Array<Bookmark?> {
            return arrayOfNulls(size)
        }

        val DEFAULT_NAME = "unnamed"
        val DEFAULT_URL = "https://joocy.com"

        fun withURL(url: String): Bookmark = Bookmark(url, DEFAULT_NAME)

        fun new(): Bookmark = Bookmark(DEFAULT_URL, DEFAULT_NAME)
    }

}