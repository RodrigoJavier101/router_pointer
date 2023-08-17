package com.rodrigo.javier.eox.hackermen.routerpointer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rodrigo.javier.eox.hackermen.routerpointer.R
import com.rodrigo.javier.eox.hackermen.routerpointer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding : ActivityMainBinding
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding =
				ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}