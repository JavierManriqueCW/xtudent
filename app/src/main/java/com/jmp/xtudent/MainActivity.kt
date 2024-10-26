package com.jmp.xtudent

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.jmp.common.ui.theme.skeletonTheme
import com.jmp.xtudent.screens.ApplicationScreen
import com.valentinilk.shimmer.LocalShimmerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Array<String>>
    private var filePickerState: MutableStateFlow<Uri?> = MutableStateFlow(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureEdgeToEdgeMode()
        initActivityResultLauncher()

        setContent {
            CompositionLocalProvider(LocalShimmerTheme provides skeletonTheme) {
                ApplicationScreen(
                    viewModel = hiltViewModel(),
                    mainNavigationController = rememberNavController(),
                    getExamFileUri = ::getExamFile
                )
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val newOverride = Configuration(newBase?.resources?.configuration)
        if (newOverride.fontScale >= 1f)
            newOverride.fontScale = 1f
        applyOverrideConfiguration(newOverride)
        super.attachBaseContext(newBase)
    }

    private fun configureEdgeToEdgeMode() {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.White.value.toInt()),
            navigationBarStyle = SystemBarStyle.dark(Color.White.value.toInt())
        )
    }

    private fun initActivityResultLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            filePickerState.value = uri
        }
    }

    private suspend fun getExamFile(): Uri? = run {
        activityResultLauncher.launch(
            arrayOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        )
        filePickerState
            .first { uri -> uri != null }
            .also { filePickerState.value = null }
    }
}
