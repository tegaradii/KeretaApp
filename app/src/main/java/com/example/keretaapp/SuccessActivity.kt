package com.example.keretaapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.keretaapp.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent
        val name = intent.getStringExtra("NAME")
        val time = intent.getStringExtra("TIME")
        val date = intent.getStringExtra("DATE")
        val tujuan = intent.getStringExtra("DESTINATION")

        // Tampilkan data ke TextView
        binding.nameTextView.text = "Nama : $name"
        binding.timeTextView.text = "Jam : $time"
        binding.dateTextView.text = "Tanggal : $date"
        binding.destinationTextView.text = "Tujuan : $tujuan"

    }
}
