package com.tibaes.search.domain.entity

import java.io.Serializable

data class Github(
    val total_count: Int,
    val items: List<Item>
)

data class Item(
    val id: Int,
    val node_id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
  //  val owner: Owner,
    val description: String,
    val watchers: Int,
    val url: String
) : Serializable

data class Owner(
    val avatarUrl: String,
    val login: String,
    val url: String
)
