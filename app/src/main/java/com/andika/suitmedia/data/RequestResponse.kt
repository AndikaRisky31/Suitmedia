package com.andika.suitmedia.data

data class RequestResponse(
    val data:Int
)

data class UserResponse(
    val page : Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data:List<User>
)
