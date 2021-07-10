package com.codigo.tmdb.data.network.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page : Int ,
    @SerializedName("results")
    val results : List <MovieResponseList>
)

data class MovieResponseList(
    val id : String ,
    @SerializedName("title")
    val title : String ,
    @SerializedName ("overview")
    val overview : String ,
    @SerializedName("poster_path")
    val imageUrl : String
)
