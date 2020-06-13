package sbnri.rakshit.nawani.io

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sbnri.rakshit.nawani.model.SbnriModel

interface RequestAPIs {

    @GET("repos")
    fun getRepos(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): Single<List<SbnriModel>>
}
