package br.com.samuelnunes.valorantpassbattle.ui.view.viewsCustom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewPropertyAnimator
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

open class HideBottomNavigation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {
    private var currentState = STATE_UP
    private var currentAnimator: ViewPropertyAnimator? = null

    fun slideUp() {
        if (currentState == STATE_UP) {
            return
        }
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            clearAnimation()
        }
        currentState = STATE_UP
        animateChildTo(
            0,
            ENTER_ANIMATION_DURATION.toLong(),
            AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR
        )
    }

    fun slideDown() {
        if (currentState == STATE_DOWN) {
            return
        }
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            clearAnimation()
        }
        currentState = STATE_DOWN
        animateChildTo(
            height,
            EXIT_ANIMATION_DURATION.toLong(),
            AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
        )
    }

    private fun animateChildTo(targetY: Int, duration: Long, interpolator: TimeInterpolator) {
        currentAnimator = animate()
            .translationY(targetY.toFloat())
            .setInterpolator(interpolator)
            .setDuration(duration)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        currentAnimator = null
                    }
                })
    }

    companion object {
        protected const val ENTER_ANIMATION_DURATION = 400
        protected const val EXIT_ANIMATION_DURATION = 300
        private const val STATE_DOWN = 1
        private const val STATE_UP = 2
    }
}
