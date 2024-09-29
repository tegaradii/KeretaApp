package com.example.keretaapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.keretaapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tujuan: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tujuan = resources.getStringArray(R.array.tujuan)
        val adapterTujuan = ArrayAdapter(
            this@MainActivity,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tujuan
        )
        binding.spinnerTujuan.adapter = adapterTujuan

        binding.tanggalKeberangkatan.setOnClickListener {
            showDatePickerDialog()
        }

        binding.jamKeberangkatan.setOnClickListener {
            showTimePickerDialog()
        }

        binding.btnPesan.setOnClickListener {
            // Membuat AlertDialog untuk konfirmasi pembelian
            val builder = AlertDialog.Builder(this@MainActivity)
            val nama = binding.namaPemesan.text.toString()
            val jam = binding.jamKeberangkatan.text.toString()
            val tanggal = binding.tanggalKeberangkatan.text.toString()
            val tujuan = binding.spinnerTujuan.selectedItem.toString()
            builder.setTitle("Konfirmasi Pemesanan")
            builder.setMessage("Tarif Tiket\nRp.200.000")

            // Opsi untuk tombol "Beli"
            builder.setPositiveButton("Beli") { dialog, _ ->
                // Intent untuk berpindah ke SuccessActivity
                val intent = Intent(this@MainActivity, SuccessActivity::class.java)
                intent.putExtra("NAME", nama)
                intent.putExtra("TIME", jam)
                intent.putExtra("DATE", tanggal)
                intent.putExtra("DESTINATION", tujuan)
                startActivity(intent) // Memulai SuccessActivity
                dialog.dismiss() // Tutup dialog setelah berhasil
            }

            // Opsi untuk tombol "Batal"
            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss() // Tutup dialog tanpa melakukan apa-apa
            }

            // Menampilkan dialog
            val dialog = builder.create()
            dialog.show()
        }
    }

    // Fungsi untuk menampilkan DatePicker
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Format tanggal yang dipilih
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.tanggalKeberangkatan.setText(selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }

    // Fungsi untuk menampilkan TimePicker
    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                // Format waktu yang dipilih
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.jamKeberangkatan.setText(selectedTime)
            }, hour, minute, true
        )
        timePickerDialog.show()
    }
}