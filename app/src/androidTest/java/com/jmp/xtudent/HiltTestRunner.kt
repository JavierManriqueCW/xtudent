package com.jmp.xtudent

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import com.karumi.shot.ShotTestRunner
import dagger.hilt.android.testing.HiltTestApplication

open class HiltTestRunner : ShotTestRunner() {

    override fun onCreate(args: Bundle) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(args)
    }

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
