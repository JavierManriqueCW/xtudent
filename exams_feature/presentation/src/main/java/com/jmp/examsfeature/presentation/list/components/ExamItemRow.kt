package com.jmp.examsfeature.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jmp.common.ui.theme.ExamRowDescriptionColor
import com.jmp.common.ui.theme.Shapes
import com.jmp.common.ui.theme.SmallBodyTextSize
import com.jmp.commons.utils.model.Exam
import com.jmp.examsfeature.presentation.creation.components.Image.BABY_BUDGIE
import com.jmp.examsfeature.presentation.creation.components.Image.valueOf

@Composable
fun ExamItemRow(
    modifier: Modifier = Modifier,
    exam: Exam,
) {
    Column(modifier = modifier) {
        ExamRowItemHeader(
            modifier = Modifier.fillMaxWidth(),
            title = exam.name,
            questionCount = exam.examQuestions.size,
            imageRes = exam.image.toImageRes(),
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = ExamRowDescriptionColor,
                    shape = Shapes.small
                )
                .padding(16.dp)
        ) {
            Text(
                text = exam.description,
                fontSize = SmallBodyTextSize,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        }
    }
}

private fun String.toImageRes(): Int =
    runCatching { valueOf(this) }
        .getOrElse { BABY_BUDGIE }.getDrawable()
