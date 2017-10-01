package com.joocy.bookmark.model

import android.os.Parcel
import android.os.Parcelable

// data classes in Kotlin are a convenience to do away with a lot
// of boilerplate code we're used to in Java. No need to write
// getters and setters, or hashCode, or equals, or toString.
// Parcels in Android are an efficient way to send data between
// activities. There is a bit of ceremony required to make a
// class parcelable, but just let the IDE do that for you! Remember
// though that any changes you make to the class may require
// changes to the Parcelable implementation.
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