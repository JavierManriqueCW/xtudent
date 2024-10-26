package com.jmp.storage.database.mapper

import com.jmp.commons.utils.model.Exam
import com.jmp.commons.utils.model.ExamQuestion
import com.jmp.storage.database.entity.ExamEntity
import com.jmp.storage.database.entity.ExamQuestionEntity
import com.jmp.storage.database.entity.ExamWithQuestions
import javax.inject.Inject

open class ExamEntityMapper @Inject constructor() {

    fun mapExamsFromEntity(examWithQuestions: List<ExamWithQuestions>): List<Exam> =
        examWithQuestions.map {
            Exam(
                id = it.exam.examId,
                name = it.exam.name,
                description = it.exam.description,
                image = it.exam.image,
                examQuestions = mapQuestionsFromEntity(it.questions)
            )
        }

    fun mapQuestionsFromEntity(questions: List<ExamQuestionEntity>): List<ExamQuestion> =
        questions.map {
            ExamQuestion(
                question = it.question,
                rightAnswer = it.rightAnswer,
                wrongAnswers = it.wrongAnswers
            )
        }

    fun mapExamToEntity(exam: Exam): ExamEntity =
        ExamEntity(
            examId = exam.id,
            name = exam.name,
            description = exam.description,
            image = exam.image
        )

    fun mapQuestionsToEntity(questions: List<ExamQuestion>, examId: Long): List<ExamQuestionEntity> =
        questions.map {
            ExamQuestionEntity(
                question = it.question,
                rightAnswer = it.rightAnswer,
                wrongAnswers = it.wrongAnswers,
                examIdFk = examId
            )
        }
}
