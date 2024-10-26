package com.jmp.xtudent.screens

import android.net.Uri
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jmp.common.ui.compose.slideEnterTransitionHorizontally
import com.jmp.common.ui.compose.slideExitTransitionHorizontally
import com.jmp.common.ui.compose.slidePopExitTransitionHorizontally
import com.jmp.common.ui.navigation.Screen
import com.jmp.common.ui.navigation.ScreenNavigationParameters
import com.jmp.examsfeature.presentation.detail.screens.ExamDetailScreen
import com.jmp.onboardingfeature.OnboardingCarouselScreen
import com.jmp.xtudent.components.ApplicationSurface
import com.jmp.xtudent.viewmodel.MainNavigationViewModel

private const val ENTER_TRANSITION_DURATION_IN_MILLIS = 600

@Composable
fun ApplicationScreen(
    viewModel: MainNavigationViewModel,
    mainNavigationController: NavHostController,
    getExamFileUri: suspend () -> Uri?,
) {
    ApplicationSurface {
        NavHost(
            modifier = Modifier,
            navController = mainNavigationController,
            startDestination = viewModel.getStartDestination(),
        ) {
            mainNavigationController.let {
                onboardingScreenComposable(it)
                mainScreenComposable(it, getExamFileUri)
                examDetailScreenComposable(it)
            }
        }
    }
}

fun NavGraphBuilder.onboardingScreenComposable(mainNavigationController: NavHostController) {
    composable(
        route = Screen.Onboarding.route,
        enterTransition = { scaleIn() },
        exitTransition = { scaleOut() }
    ) {
        OnboardingCarouselScreen(
            viewModel = hiltViewModel(),
            mainNavigationController = mainNavigationController
        )
    }
}

fun NavGraphBuilder.mainScreenComposable(
    mainNavigationController: NavHostController,
    getExamFileUri: suspend () -> Uri?
) {
    composable(
        route = Screen.Main.route,
        enterTransition = { slideEnterTransitionHorizontally() },
        exitTransition = { slideExitTransitionHorizontally() },
        popEnterTransition = { fadeIn(tween(ENTER_TRANSITION_DURATION_IN_MILLIS)) }
    ) {
        MainScreen(
            viewModel = hiltViewModel(),
            mainNavigationController = mainNavigationController,
            getExamFileUri = getExamFileUri
        )
    }
}

fun NavGraphBuilder.examDetailScreenComposable(mainNavigationController: NavHostController) {
    composable(
        route = Screen.ExamDetail.route,
        arguments = listOf(navArgument(ScreenNavigationParameters.EXAM_ID_KEY) {
            nullable = false
            type = NavType.LongType
        }),
        enterTransition = { slideEnterTransitionHorizontally() },
        exitTransition = { slidePopExitTransitionHorizontally() },
    ) {
        val examId = it.arguments?.getLong(ScreenNavigationParameters.EXAM_ID_KEY)

        examId?.let {
            ExamDetailScreen(
                viewModel = hiltViewModel(),
                mainNavigationController = mainNavigationController,
                examId = examId
            )
        } ?: mainNavigationController.navigate(Screen.Main.route)
    }
}
