package hr.tvz.android.giggle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class GiggleData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String
) : Serializable {
    constructor() : this (0, "", "", "")
}
