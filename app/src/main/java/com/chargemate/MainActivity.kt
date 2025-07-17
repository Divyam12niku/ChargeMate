package com.chargemate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var toggleA: Switch
    private lateinit var toggleB: Switch
    private lateinit var toggleC: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleA = findViewById(R.id.toggleA)
        toggleB = findViewById(R.id.toggleB)
        toggleC = findViewById(R.id.toggleC)

        findViewById<Button>(R.id.testA).setOnClickListener {
            GlyphController.activatePreset(this, "A")
        }
        findViewById<Button>(R.id.testB).setOnClickListener {
            GlyphController.activatePreset(this, "B")
        }
        findViewById<Button>(R.id.testC).setOnClickListener {
            GlyphController.activatePreset(this, "C")
        }

        val receiver = BatteryReceiver {
            val batteryPct = it
            when (batteryPct) {
                in 0..25 -> if (toggleA.isChecked) GlyphController.activatePreset(this, "A")
                in 26..75 -> if (toggleB.isChecked) GlyphController.activatePreset(this, "B")
                in 76..99 -> if (toggleC.isChecked) GlyphController.activatePreset(this, "C")
                100 -> GlyphController.stop(this)
            }
        }
        registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}
