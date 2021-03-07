package com.example.mvvmgithubsampleapp.model

import android.os.Parcel
import android.os.Parcelable

class FullReadyOwner() : Parcelable {
    var nickName: String? = null
    var avatarUrl: String? = null

    constructor(parcel: Parcel) : this() {
        nickName = parcel.readString()
        avatarUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nickName)
        parcel.writeString(avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FullReadyOwner> {
        override fun createFromParcel(parcel: Parcel): FullReadyOwner {
            return FullReadyOwner(parcel)
        }

        override fun newArray(size: Int): Array<FullReadyOwner?> {
            return arrayOfNulls(size)
        }
    }
}