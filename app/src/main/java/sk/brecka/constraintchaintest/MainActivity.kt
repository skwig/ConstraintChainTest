package sk.brecka.constraintchaintest

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
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
            redTextView.visibility = if (isChecked) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(constraintLayout)
        }

        greenSwitch.setOnCheckedChangeListener { _, isChecked ->
            greenTextView.visibility = if (isChecked) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(constraintLayout)
        }

        blueSwitch.setOnCheckedChangeListener { _, isChecked ->
            blueTextView.visibility = if (isChecked) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(constraintLayout)
        }
    }
}
