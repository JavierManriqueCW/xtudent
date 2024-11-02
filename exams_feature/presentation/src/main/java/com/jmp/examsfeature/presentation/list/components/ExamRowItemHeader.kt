package com.jmp.examsfeature.presentation.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jmp.examsfeature.presentation.R
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EXAM_ROW_IMAGE
import com.jmp.examsfeature.presentation.list.screens.ExamsScreenTestTags.EXAM_ROW_IMAGE_LOADED_SEMANTICS

@Composable
fun ExamRowItemHeader(
    modifier: Modifier = Modifier,
    title: String,
    questionCount: Int,
    imageRes: Int
) {
    val imageLoaded = remember { mutableStateOf(false) }
    Row(modifier = modifier) {
        Card(shape = CircleShape) {
            AsyncImage(
                modifier = Modifier
                    .size(40.dp)
                    .testTag(EXAM_ROW_IMAGE)
                    .semantics {
                        if (imageLoaded.value) {
                            contentDescription = EXAM_ROW_IMAGE_LOADED_SEMANTICS
                        }
                    },
                contentScale = ContentScale.Crop,
                model = imageRes,
                contentDescription = null,
                onSuccess = { imageLoaded.value = true }
            )
        }

        Column(modifier = Modifier
            .padding(horizontal = 16.dp)
            .align(Alignment.CenterVertically)
            .weight(1f)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = 20.sp,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.exams_screen_list_row_questions, questionCount),
                style = MaterialTheme.typography.bodySmall,
                color = Color.LightGray
            )
        }

        ExamRowIndicator()
    }
}

@Composable
private fun ExamRowIndicator(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally),
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.Filled.Info,
                contentDescription = null
            )
        }
    }
}
