package hr.tvz.android.giggle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GiggleType(val id: Long, val type: String?, val title: String?, val content : String?): Parcelable