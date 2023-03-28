package hr.tvz.android.listaluksic

import java.io.Serializable

data class ItemDTO(
    val id : Long,
    val title : String,
    val description : String,
    val image : Int
) : Serializable