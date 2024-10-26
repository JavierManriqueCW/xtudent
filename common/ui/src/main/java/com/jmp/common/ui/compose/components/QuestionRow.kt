package com.jmp.common.ui.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jmp.common.ui.theme.FilterTextBoxHeight
import com.jmp.common.ui.theme.HugeGeneralPadding
import com.jmp.common.ui.theme.Shapes
import com.jmp.common.ui.theme.SmallGeneralPadding

@Composable
fun QuestionRow(
    modifier: Modifier = Modifier,
    questionNumber: Int,
    questionState: String,
    correctAnswerState: String,
    wrongAnswerState1: String,
    wrongAnswerState2: String,
    wrongAnswerState3: String
) {
    ConstraintLayout(modifier = modifier) {
        val (indicator, content) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(indicator) {
                    top.linkTo(content.top)
                    bottom.linkTo(content.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = questionNumber.toString() ,
            fontSize = 56.sp,
            fontWeight = FontWeight.ExtraLight,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White.copy(alpha = 0.05f),
                    shape = Shapes.extraLarge
                )
                .padding(
                    horizontal = HugeGeneralPadding,
                    vertical = HugeGeneralPadding * 2)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = FilterTextBoxHeight),
                maxLines = 10,
                text = questionState
            )

            Spacer(modifier = Modifier.height(HugeGeneralPadding))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = FilterTextBoxHeight),
                fontWeight = FontWeight.ExtraLight,
                maxLines = 10,
                text = correctAnswerState
            )

            Spacer(modifier = Modifier.height(SmallGeneralPadding))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = FilterTextBoxHeight),
                fontWeight = FontWeight.ExtraLight,
                maxLines = 10,
                text = wrongAnswerState1
            )

            Spacer(modifier = Modifier.height(SmallGeneralPadding))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = FilterTextBoxHeight),
                fontWeight = FontWeight.ExtraLight,
                maxLines = 10,
                text = wrongAnswerState2
            )

            Spacer(modifier = Modifier.height(SmallGeneralPadding))

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.ExtraLight,
                maxLines = 10,
                text = wrongAnswerState3
            )
        }
    }
}
