package com.jmp.onboardingfeature.viewmodel

sealed class OnboardingIntent {

    data object DisableShouldShowOnboarding : OnboardingIntent()
}
