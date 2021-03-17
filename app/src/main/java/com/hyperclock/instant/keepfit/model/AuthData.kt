package com.hyperclock.instant.keepfit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthData(
    var userId: String = "",
    var accessToken: String = "",
    var tokenType: String = ""
) : Parcelable