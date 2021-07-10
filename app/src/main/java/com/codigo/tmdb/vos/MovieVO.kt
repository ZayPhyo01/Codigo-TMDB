package com.codigo.tmdb.vos

data class MovieVO (
    val id : String ,
    val title : String ,
    val overview : String ,
    var isFavourite : Boolean = false ,
    var imageUrl : String
)