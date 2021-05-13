package com.tibaes.search.data.entity

import com.squareup.moshi.Json
import com.tibaes.search.domain.entity.Github
import com.tibaes.search.domain.entity.Item
import com.tibaes.search.domain.entity.Owner

data class GithubRemote(
    @field:Json(name = "total_count") val totalCount: Int?,
    @field:Json(name = "items") val items: List<ItemRemote>?
)/* {
    fun transform() = Github(
        quantity = totalCount ?: 0,
        items = items?.mapNotNull { it?.transform() } ?: listOf()
    )

} */

data class ItemRemote(
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "full_name") val fullName: String?,
    @field:Json(name = "private") val private: Boolean?,
    @field:Json(name = "owner") val owner: OwnerRemote?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "watchers") val watchers: Int?,
    @field:Json(name = "url") val url: String?
) /*{
    fun transform() = Item(
        id = id ?: 0,
        name = name ?: "",
        fullName = fullName ?: "",
        private = private ?: false,
        owner = owner?.transform()
            ?: Owner(
                avatarUrl = "",
                login = "",
                url = ""
            ),
        description = description ?: "",
        watchers = watchers ?: 0,
        url = url ?: ""
    )
}*/

data class OwnerRemote(
    @field:Json(name = "avatar_url") val avatarUrl: String?,
    @field:Json(name = "login") val login: String?,
    @field:Json(name = "url") val url: String?
) /*{
    fun transform() = Owner(
        avatarUrl = avatarUrl ?: "",
        login = login ?: "",
        url = url ?: ""
    )
}
*/
