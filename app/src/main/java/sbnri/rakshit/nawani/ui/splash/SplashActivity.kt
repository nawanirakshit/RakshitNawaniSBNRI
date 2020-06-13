package sbnri.rakshit.nawani.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_splash.*
import sbnri.rakshit.nawani.R
import sbnri.rakshit.nawani.ui.dashboard.MainActivity

class SplashActivity : AppCompatActivity() {

    val STARTUP_DELAY = 300
    val ANIM_ITEM_DURATION = 1000
    val ITEM_DELAY = 300

    private val animationStarted = false

    private lateinit var context: Context

    private var th: Thread? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        context = this

        startThread()
    }

    private fun startThread() {

        th = Thread(Runnable {
            Thread.sleep(2000)
            startActivity(Intent(context, MainActivity::class.java))
            finish()
        })
        th!!.start()
    }

    override fun onBackPressed() { //Stopping the thread if the back pressed is clicked
        if (th != null) {
            th!!.interrupt()
        }
        super.onBackPressed()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (!hasFocus || animationStarted) {
            return
        }
        animate()
        super.onWindowFocusChanged(hasFocus)
    }

    private fun animate() {
        val logoImageView: CircleImageView = findViewById<View>(R.id.img_logo) as CircleImageView
        val container = findViewById<View>(R.id.container) as ViewGroup
        ViewCompat.animate(logoImageView)
            .translationY(-250f)
            .setStartDelay(STARTUP_DELAY.toLong())
            .setDuration(ANIM_ITEM_DURATION.toLong()).setInterpolator(
                DecelerateInterpolator(1.2f)
            ).start()
        for (i in 0 until container.childCount) {
            val v = container.getChildAt(i)
            var viewAnimator: ViewPropertyAnimatorCompat
            viewAnimator = if (v !is Button) {
                ViewCompat.animate(v)
                    .translationY(50f).alpha(1f)
                    .setStartDelay(ITEM_DELAY * i + 500.toLong())
                    .setDuration(1000)
            } else {
                ViewCompat.animate(v)
                    .scaleY(1f).scaleX(1f)
                    .setStartDelay(ITEM_DELAY * i + 500.toLong())
                    .setDuration(1000)
            }
            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
        }
    }
}