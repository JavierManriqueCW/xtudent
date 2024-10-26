package com.jmp.splashfeature

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jmp.common.ui.navigation.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: ActivityNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureSplashScreen(this)
    }

    private fun configureSplashScreen(currentActivity: Activity) {
        installSplashScreen().run {
            setOnExitAnimationListener { vp ->
                navigator.navigateToMainActivity(currentActivity)

                val animatorSet = AnimatorSet()
                val interpolator = AccelerateInterpolator()
                val scaleXAnimator = ObjectAnimator.ofFloat(
                    vp.iconView,
                    View.SCALE_X,
                    EXIT_ANIMATION_X_START_SCALING_FACTOR,
                    EXIT_ANIMATION_X_END_SCALING_FACTOR
                )
                val scaleYAnimator = ObjectAnimator.ofFloat(
                    vp.iconView,
                    View.SCALE_Y,
                    EXIT_ANIMATION_Y_START_SCALING_FACTOR,
                    EXIT_ANIMATION_Y_END_SCALING_FACTOR
                )
                val alphaAnimator = ObjectAnimator.ofFloat(
                    vp.iconView,
                    View.ALPHA,
                    EXIT_ANIMATION_START_ALPHA_FACTOR,
                    EXIT_ANIMATION_END_ALPHA_FACTOR
                )
                val duration = EXIT_ANIMATION_DURATION

                scaleXAnimator.duration = duration
                scaleYAnimator.duration = duration
                alphaAnimator.duration = duration


                scaleXAnimator.interpolator = interpolator
                scaleYAnimator.interpolator = interpolator
                alphaAnimator.interpolator = interpolator

                animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)
                animatorSet.start()
            }
        }
    }

    companion object {
        private const val EXIT_ANIMATION_DURATION = 800L
        private const val EXIT_ANIMATION_X_START_SCALING_FACTOR = 1f
        private const val EXIT_ANIMATION_X_END_SCALING_FACTOR = 5f
        private const val EXIT_ANIMATION_Y_START_SCALING_FACTOR = 1f
        private const val EXIT_ANIMATION_Y_END_SCALING_FACTOR = 5f
        private const val EXIT_ANIMATION_START_ALPHA_FACTOR = 1f
        private const val EXIT_ANIMATION_END_ALPHA_FACTOR = 0f
    }
}
