package kt.ktbindadapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.tv1).setOnClickListener {
            MainActivity@ this.startActivity(Intent(MainActivity@ this, Sigle1Activity::class.java))
        }
        findViewById<View>(R.id.tv2).setOnClickListener {
            MainActivity@ this.startActivity(Intent(MainActivity@ this, Sigle2Activity::class.java))
        }
        findViewById<View>(R.id.tv3).setOnClickListener {
            MainActivity@ this.startActivity(Intent(MainActivity@ this, Multi1Activity::class.java))
        }
        findViewById<View>(R.id.tv4).setOnClickListener {
            MainActivity@ this.startActivity(Intent(MainActivity@ this, Multi2Activity::class.java))
        }

    }
}