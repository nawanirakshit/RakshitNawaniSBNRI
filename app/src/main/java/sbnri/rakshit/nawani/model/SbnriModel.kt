package sbnri.rakshit.nawani.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TABLE_SNBRI")
data class SbnriModel(

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String,

    @ColumnInfo(name = "full_name")
    @SerializedName("full_name")
    @Expose
    var fullName: String,

    @ColumnInfo(name = "private")
    @SerializedName("private")
    @Expose
    var pvt: Boolean,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    @Expose
    var description: String? = "",

    @ColumnInfo(name = "open_issues_count")
    @SerializedName("open_issues_count")
    @Expose
    var openIssuesCount: Int = 0,

    @ColumnInfo(name = "license")
    @SerializedName("license")
    @TypeConverters(LicenceTypeConverter::class)
    @Expose
    var license: License?,

    @ColumnInfo(name = "forks")
    @SerializedName("forks")
    @Expose
    var forks: Int,

    @ColumnInfo(name = "open_issues")
    @SerializedName("open_issues")
    @Expose
    var openIssues: Int,

    @ColumnInfo(name = "watchers")
    @SerializedName("watchers")
    @Expose
    var watchers: Int,

    @ColumnInfo(name = "default_branch")
    @SerializedName("default_branch")
    @Expose
    var defaultBranch: String,

    @ColumnInfo(name = "permissions")
    @SerializedName("permissions")
    @TypeConverters(PermissionTypeConverter::class)
    @Expose
    var permissions: Permissions?

) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int = 0
}