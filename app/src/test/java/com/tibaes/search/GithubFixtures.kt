package com.tibaes.search

import com.tibaes.search.data.entity.GithubRemote
import com.tibaes.search.data.entity.ItemRemote
import com.tibaes.search.data.entity.OwnerRemote
import com.tibaes.search.domain.entity.Github
import com.tibaes.search.domain.entity.Item
import com.tibaes.search.domain.entity.Owner
import retrofit2.Response

val customGithub = Github(
    quantity = 2,
    items = listOf(
        Item(
            id = 1,
            name = "teste",
            fullName = "teste2",
            private = false,
            owner = Owner(
                avatarUrl = "",
                login = "",
                url = ""
            ),
            description = "Tste",
            watchers = 1,
            url = ""
        ),
        Item(
            id = 1,
            name = "teste",
            fullName = "teste2",
            private = false,
            owner = Owner(
                avatarUrl = "",
                login = "",
                url = ""
            ),
            description = "Tste",
            watchers = 1,
            url = ""
        )
    )
)

val customResponse = Response.success(
    GithubRemote(
        totalCount = 2,
        items = listOf(
            ItemRemote(
                id = 1,
                name = "teste",
                fullName = "teste2",
                private = false,
                owner = OwnerRemote(
                    avatarUrl = "",
                    login = "",
                    url = ""
                ),
                description = "Tste",
                watchers = 1,
                url = ""
            ),
            ItemRemote(
                id = 1,
                name = "teste",
                fullName = "teste2",
                private = false,
                owner = OwnerRemote(
                    avatarUrl = "",
                    login = "",
                    url = ""
                ),
                description = "Tste",
                watchers = 1,
                url = ""
            )
        )
    )
)