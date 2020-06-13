package sbnri.rakshit.nawani.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PermissionTypeConverter {

    @TypeConverter
    fun fromObjectToString(countryLang: Permissions?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Permissions?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun fromStringToObject(countryLangString: String?): Permissions? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Permissions?>() {}.type
        return gson.fromJson<Permissions>(countryLangString, type)
    }

}