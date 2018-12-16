package sk.brecka.constraintchaintest

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var constraintLayout: ConstraintLayout

    lateinit var redTextView: TextView
    lateinit var greenTextView: TextView
    lateinit var blueTextView: TextView

    lateinit var redSwitch: Switch
    lateinit var greenSwitch: Switch
    lateinit var blueSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintLayout = findViewById(R.id.constraintLayout)

        redTextView = findViewById(R.id.redTextView)
        greenTextView = findViewById(R.id.greenTextView)
        blueTextView = findViewById(R.id.blueTextView)

        redSwitch = findViewById(R.id.redSwitch)
        greenSwitch = findViewById(R.id.greenSwitch)
        blueSwitch = findViewById(R.id.blueSwitch)

        redSwitch.setOnCheckedChangeListener { _, isChecked ->
            foo(redTextView, R.id.beforeRedBarrier, isChecked)
        }

        greenSwitch.setOnCheckedChangeListener { _, isChecked ->
            foo(greenTextView, R.id.beforeGreenBarrier, isChecked)
        }

        blueSwitch.setOnCheckedChangeListener { _, isChecked ->
            foo(blueTextView, R.id.beforeBlueBarrier, isChecked)
        }
    }
}

private fun foo(view: View, @IdRes anchorId: Int, isChecked: Boolean, overshoot: Boolean = true) {
    val parent = view.parent as ConstraintLayout
    val viewId = view.id
    ConstraintSet().apply {
        clone(parent)
        if (isChecked) {
            clear(viewId, ConstraintSet.BOTTOM)
            connect(viewId, ConstraintSet.TOP, anchorId, ConstraintSet.BOTTOM)
        } else {
            clear(viewId, ConstraintSet.TOP)
            connect(viewId, ConstraintSet.BOTTOM, anchorId, ConstraintSet.TOP)
        }
    }.applyTo(parent)

    TransitionManager.beginDelayedTransition(parent, ChangeBounds().apply { interpolator = if (overshoot) OvershootInterpolator() else AccelerateDecelerateInterpolator() })
}
