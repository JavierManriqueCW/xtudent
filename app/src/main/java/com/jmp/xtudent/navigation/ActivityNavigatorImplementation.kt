package com.jmp.xtudent.navigation

import android.app.Activity
import android.content.Intent
import com.jmp.common.ui.navigation.ActivityNavigator
import com.jmp.xtudent.MainActivity
import com.jmp.xtudent.R
import javax.inject.Inject

class ActivityNavigatorImplementation @Inject constructor() : ActivityNavigator {

    override fun navigateToMainActivity(currentActivity: Activity) {
        currentActivity.apply {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}
