package sbnri.rakshit.nawani.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class License(
    @ColumnInfo(name = "key")
    @SerializedName("key")
    @Expose
    var key: String,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String,

    @ColumnInfo(name = "spdx_id")
    @SerializedName("spdx_id")
    @Expose
    var spdxId: String,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    @Expose
    var url: String,

    @ColumnInfo(name = "node_id")
    @SerializedName("node_id")
    @Expose
    var nodeId: String
)