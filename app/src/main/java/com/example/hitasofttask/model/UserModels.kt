package com.example.hitasofttask.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @SerializedName("id"       ) var id       : Int,
    @SerializedName("email"    ) var email    : String? = null,
    @SerializedName("password" ) var password : String? = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("role"     ) var role     : String? = null,
    @SerializedName("avatar"   ) var avatar   : String? = null
)

data class RequUser(
    @SerializedName("name"     ) var name     : String,
    @SerializedName("email"    ) var email    : String,
    @SerializedName("password" ) var password : String,
    @SerializedName("avatar"   ) var avatar   : String
)

data class ChildItem(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
)

data class ParentItem(
    val id: Int,
    val name: String,
    var isExpanded: Boolean = false,
    var isChecked: Boolean = false,
    val childList: MutableList<ChildItem>
)

