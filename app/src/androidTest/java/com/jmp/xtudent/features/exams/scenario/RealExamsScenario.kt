package com.jmp.xtudent.features.exams.scenario

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.Intent.ACTION_OPEN_DOCUMENT
import android.net.Uri
import androidx.core.net.toUri
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.platform.app.InstrumentationRegistry
import com.jmp.examsfeature.domain.exams.repository.ExamsRepository
import java.io.File
import javax.inject.Inject

class RealExamsScenario @Inject constructor(
    private val examsRepository: ExamsRepository
) {

    suspend fun givenThatThereIsOneExam() = apply {
        examsRepository.saveExam(FakeExamsFactory.makeExam())
    }

    suspend fun givenThatThereAreSeveralExams() = apply {
        repeat(FakeExamsFactory.SEVERAL_EXAMS_AMOUNT) {
            examsRepository.saveExam(FakeExamsFactory.makeExam())
        }
    }

    fun givenTheUserSelectsARightFormatXlsxFile(): RealExamsScenario = apply {
        mockOpenDocumentIntentForUri(getAssetUri(RIGHT_FILENAME))
    }

    fun givenTheUserSelectsAnExamWithAnUndefinedImage(): RealExamsScenario = apply {
        mockOpenDocumentIntentForUri(getAssetUri(RIGHT_FILENAME_WITH_UNDEFINED_IMAGE))
    }

    fun givenTheUserSelectsAWrongFormatXlsxFile(): RealExamsScenario = apply {
        mockOpenDocumentIntentForUri(getAssetUri(WRONG_FILENAME))
    }

    private fun mockOpenDocumentIntentForUri(uri: Uri) {
        Intents.intending(hasAction(ACTION_OPEN_DOCUMENT))
            .respondWith(
                Instrumentation.ActivityResult(
                    Activity.RESULT_OK,
                    Intent().apply { data = uri }
                )
            )
    }

    private fun getAssetUri(fileName: String): Uri =
        File.createTempFile(fileName, EXTENSION)
            .apply { setContentFromAsset(fileName + EXTENSION) }
            .toUri()

    private fun File.setContentFromAsset(fileName: String) {
        InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .assets
            .open(fileName)
            .use { input ->
                outputStream().use { output ->
                    input.copyTo(output)
                }
            }
    }

    companion object {
        private const val WRONG_FILENAME = "wrong_solar_system_exam"
        private const val RIGHT_FILENAME = "correct_solar_system_exam"
        private const val RIGHT_FILENAME_WITH_UNDEFINED_IMAGE = "solar_system_exam_with_undefined_image"
        private const val EXTENSION = ".xlsx"
    }
}
