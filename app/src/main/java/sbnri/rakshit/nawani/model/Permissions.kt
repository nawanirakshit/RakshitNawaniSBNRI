package sbnri.rakshit.nawani.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Permissions(
    @ColumnInfo(name = "admin")
    @SerializedName("admin")
    @Expose
    var admin: Boolean,

    @ColumnInfo(name = "push")
    @SerializedName("push")
    @Expose
    var push: Boolean,

    @ColumnInfo(name = "pull")
    @SerializedName("pull")
    @Expose
    var pull: Boolean
)