/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.adoptcat.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.adoptcat.R

@Immutable
data class Cat(
    val id: Long,
    val name: String,
    @DrawableRes val imageUrl: Int,
    val tagline: String = "",
    val tags: Set<String> = emptySet()
)

/**
 * A fake repo
 */
object CatRepo {
    fun getCat(catId: Long): Cat = cats.find { it.id == catId }!!
    fun getRelated(@Suppress("UNUSED_PARAMETER") catId: Long): List<Cat> = cats
}

/**
 * Static data
 */

val cats = listOf(
    Cat(
        id = 1,
        name = "大橘",
        tagline = "A tag line",
        imageUrl = R.drawable.cat_1,
    ),
    Cat(
        id = 2,
        name = "小美",
        tagline = "A tag line",
        imageUrl = R.drawable.cat_2,
    ),
    Cat(
        id = 3,
        name = "花猫",
        tagline = "A tag line",
        imageUrl = R.drawable.cat_3,
    )
)
