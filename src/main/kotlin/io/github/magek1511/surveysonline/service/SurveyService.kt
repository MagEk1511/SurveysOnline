package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dao.ChoiceDao
import io.github.magek1511.surveysonline.database.dao.SurveyDao
import io.github.magek1511.surveysonline.database.dao.SurveyResponseDao
import io.github.magek1511.surveysonline.database.dto.request.SurveyAnswerRequest
import io.github.magek1511.surveysonline.database.dto.request.SurveyCreationRequest
import io.github.magek1511.surveysonline.database.entity.survey.*
import io.github.magek1511.surveysonline.database.enums.QuestionEnum
import io.github.magek1511.surveysonline.util.exceptions.SurveyNotFoundException
import org.springframework.stereotype.Service

@Service
class SurveyService(
    private val surveyDao: SurveyDao,
    private val surveyResponseDao: SurveyResponseDao,
    private val choiceDao: ChoiceDao,
    private val jwtService: JwtService,
    private val userService: UserService,
) {

    fun createSurvey(request: SurveyCreationRequest): Survey {
        val survey = Survey(name = request.name, questions = request.questions.map { question ->
            Question(title = question.title, type = question.type, choices = question.choices.map { choice ->
                Choice(
                    text = choice.text
                )
            })
        })

        return surveyDao.save(survey)
    }

    fun getAllSurveys(): List<Survey> {
        return surveyDao.findAll()
    }

    fun getSurveyById(id: Long): Survey {
        return surveyDao.findEntityById(id) ?: throw SurveyNotFoundException("Survey not found by Id:$id}")
    }

    fun answerSurvey(request: SurveyAnswerRequest, token: String): SurveyResponse {
        val username = jwtService.getUsername(token)
        val user = userService.getByUsername(username)

        val survey = getSurveyById(request.surveyId)

        val surveyQuestionsMap = survey.questions.associateBy { it.id }

        request.answers.forEach { questionAnswer ->
            val surveyQuestion = surveyQuestionsMap[questionAnswer.questionId]
                ?: throw Exception("Question ID ${questionAnswer.questionId} not found in survey.")

            // Validate that all choices provided in the answer are valid for this question
            val validChoiceIds = surveyQuestion.choices.map { it.id }.toSet()
            if (!questionAnswer.choicesId.all { it in validChoiceIds }) {
                throw Exception("Invalid choices for question ID ${questionAnswer.questionId}")
            }
        }


        val surveyAnswer = SurveyResponse(user.id, survey, survey.questions.map { question ->
            when (question.type) {
//                QuestionEnum.FREE_ANSWER -> QuestionResponse(
//                    question,
//                    request.answers.filter { it.questionId == question.id }[0].choicesId[0]
//
//                )
                QuestionEnum.ONE_ANSWER -> QuestionResponse(
                    question,
                    listOf(choiceDao.findById(request.answers.filter { it.questionId == question.id }[0].choicesId[0]).get())
                )
                QuestionEnum.MULTIPLE_ANSWER -> {
                    val temp = mutableListOf<Choice>()
                    choiceDao.findAllById(request.answers.filter { it.questionId == question.id }[0].choicesId).toCollection(temp)
                    QuestionResponse(
                        question,
                        temp
                    )
                }

            }
        })

        surveyResponseDao.save(surveyAnswer)

        return surveyAnswer
    }

    fun getResponsesForSurvey(surveyId: Long): List<SurveyResponse> {
        return surveyResponseDao.findAllBySurveyId(surveyId)
    }
}