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

package com.example.adoptcat.ui.cat

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.adoptcat.R

import com.example.adoptcat.model.Cat
import com.example.adoptcat.model.cats


private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp

@Composable
fun CatCard(
    cat: Cat,
    onCatClick: (Long) -> Unit,
    index: Int,
    scroll: Int,
    modifier: Modifier = Modifier
) {
    val featuredString = stringResource(id = R.string.featured)
    ConstraintLayout(
        modifier = Modifier
            .clickable(
                onClick = { onCatClick(cat.id) }
            )
            .semantics {
                contentDescription = featuredString
            }
    ) {
        val (avatar, subject, name, image) = createRefs()
        Row(
            modifier = Modifier
                .constrainAs(avatar) {
//                    centerHorizontallyTo(parent)
                    top.linkTo(parent.top)
                }
        ) {
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                val imageModifier = Modifier
                    .heightIn(min = 180.dp)
                    .fillMaxWidth()
                    .clip(shape = MaterialTheme.shapes.medium)
                Image(
                    painter = painterResource(R.drawable.bled_castle),
                    contentDescription = null,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text("梅山烟雨", fontWeight = FontWeight.Bold)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text("3 minutes ago", style = MaterialTheme.typography.body2)
                }
            }
        }

        Text(
            text = cat.name,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(name) {
                    centerHorizontallyTo(parent)
                    top.linkTo(avatar.bottom)
                }
        )

        val imageModifier = Modifier
            .padding(10.dp)
            .heightIn(min = 150.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
            .constrainAs(image) {
                centerHorizontallyTo(parent)
                top.linkTo(name.bottom)
            }

        Image(
            painter = painterResource(cat.imageUrl),
            contentDescription = null, // decorative
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

    }
}


@Preview("Highlight snack card")
@Composable
fun SnackCardPreview() {
    val cat = cats.first()
    CatCard(
        cat = cat,
        onCatClick = { },
        index = 0,
        scroll = 0
    )
}

@Preview("Highlight snack card • Dark Theme")
@Composable
fun SnackCardDarkPreview() {
    val snack = cats.first()
    CatCard(
        cat = snack,
        onCatClick = { },
        index = 0,
        scroll = 0
    )
}

