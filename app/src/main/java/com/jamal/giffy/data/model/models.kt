package com.jamal.giffy.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class GiffyResponse(
    @SerializedName("data")
    val data: List<GiffyImage>
)

@Parcelize
data class GiffyImage(
    @SerializedName("source")
    val source: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("images")
    val images: Images
) : Parcelable

@Parcelize
data class User(
    @SerializedName("display_name")
    val displayName: String
) : Parcelable

@Parcelize
data class Images(
    @SerializedName("downsized")
    val downSized: Image
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("url")
    val url: String
) : Parcelable