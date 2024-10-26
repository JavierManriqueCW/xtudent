package com.jmp.examsfeature.presentation.creation.components

import com.jmp.examsfeature.presentation.R

enum class Image {
    NIGHT_SKY { override fun getDrawable(): Int = R.drawable.im_night_sky },
    SPACE_ROCKET { override fun getDrawable(): Int = R.drawable.im_space },
    BURGER { override fun getDrawable(): Int = R.drawable.im_burger },
    BABY_BUDGIE { override fun getDrawable(): Int = R.drawable.im_budgie },
    FOREST { override fun getDrawable(): Int = R.drawable.im_forest },
    BEACH { override fun getDrawable(): Int = R.drawable.im_beach },
    VIDEO_GAME { override fun getDrawable(): Int = R.drawable.im_videogame_character },
    BOOKS { override fun getDrawable(): Int = R.drawable.im_books },
    CUPCAKE { override fun getDrawable(): Int = R.drawable.im_cupcake },
    FIRST_ROBOT { override fun getDrawable(): Int = R.drawable.im_robot_1 },
    SECOND_ROBOT { override fun getDrawable(): Int = R.drawable.im_robot_2 },
    CAR { override fun getDrawable(): Int = R.drawable.im_car },
    CUPCAKE_BRIGHT { override fun getDrawable(): Int = R.drawable.im_cupcake_bright },
    MIC { override fun getDrawable(): Int = R.drawable.im_mic },
    CANDY { override fun getDrawable(): Int = R.drawable.im_candy };

    abstract fun getDrawable(): Int
}
