package com.jmp.onboardingfeature.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.ViewModel
import com.jmp.common.ui.theme.FirstOnboardingImageSize
import com.jmp.common.ui.theme.SecondOnboardingImageSize
import com.jmp.common.ui.theme.ThirdOnboardingImageSize
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.FIRST_ONBOARDING_LOTTIE
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.SECOND_ONBOARDING_LOTTIE
import com.jmp.onboardingfeature.OnboardingCarouselScreenTestTags.THIRD_ONBOARDING_LOTTIE
import com.jmp.onboardingfeature.R
import com.jmp.onboardingfeature.state.OnboardingState
import com.jmp.storage.preferences.domain.interactor.DisableShouldShowOnboardingPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
open class OnboardingViewModel @Inject constructor(
    private val disableShouldShowOnboardingPreference: DisableShouldShowOnboardingPreference
) : ViewModel() {

    private val _uiState = MutableStateFlow(provideOnboardingState())
    val uiState: StateFlow<OnboardingState> = _uiState

    fun sendIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.DisableShouldShowOnboarding -> disableShouldShowOnboarding()
        }
    }

    private fun disableShouldShowOnboarding() = disableShouldShowOnboardingPreference()

    private fun provideOnboardingState(): OnboardingState =
        OnboardingState(
            pages = 3,
            imageIdList = listOf(
                R.raw.anim_first_onboarding_step,
                R.raw.anim_second_onboarding_step,
                R.raw.anim_third_onboarding_step
            ),
            titleResList = listOf(
                R.string.first_onboarding_step_title,
                R.string.second_onboarding_step_title,
                R.string.third_onboarding_step_title
            ),
            descriptionResList = listOf(
                R.string.first_onboarding_step_description,
                R.string.second_onboarding_step_description,
                R.string.third_onboarding_step_description
            ),
            backgroundImageList = listOf(
                R.drawable.bg_onboarding_first_step,
                R.drawable.bg_onboarding_second_step,
                R.drawable.bg_onboarding_third_step
            ),
            imageSizeList = listOf(
                FirstOnboardingImageSize,
                SecondOnboardingImageSize,
                ThirdOnboardingImageSize
            ),
            imageContentScaleList = listOf(
                ContentScale.Crop,
                ContentScale.Crop,
                ContentScale.FillBounds
            ),
            lottieTestTags = listOf(
                FIRST_ONBOARDING_LOTTIE,
                SECOND_ONBOARDING_LOTTIE,
                THIRD_ONBOARDING_LOTTIE
            ),
            buttonColor = Color.White,
            selectedDotColor = Color.White
        )
}
