package com.hh.testapponlineshop.domain.models

import java.util.UUID

data class UserDomain(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val surname: String,
    val phoneNumber: String,
    var favourites: List<String> = emptyList()
)