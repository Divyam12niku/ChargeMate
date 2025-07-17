package com.chargemate

import android.content.Context
import android.widget.Toast

object GlyphController {
    fun activatePreset(context: Context, preset: String) {
        Toast.makeText(context, "Preset $preset activated (simulated)", Toast.LENGTH_SHORT).show()
    }

    fun stop(context: Context) {
        Toast.makeText(context, "Glyph lights stopped", Toast.LENGTH_SHORT).show()
    }
}
