package sbnri.rakshit.nawani.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class LicenceTypeConverter {

    @TypeConverter
    fun fromObjectToString(countryLang: License?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<License?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun fromStringToObject(countryLangString: String?): License? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<License?>() {}.type
        return gson.fromJson<License>(countryLangString, type)
    }

}