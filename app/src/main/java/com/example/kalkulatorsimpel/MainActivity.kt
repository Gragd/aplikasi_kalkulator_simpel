package com.example.kalkulatorsimpel

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etResult: EditText
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etResult = findViewById(R.id.editResult)
        dbHelper = DatabaseHelper(this)

        val numberButtons = listOf<Button>(
            findViewById(R.id.button_0),
            findViewById(R.id.button_1),
            findViewById(R.id.button_2),
            findViewById(R.id.button_3),
            findViewById(R.id.button_4),
            findViewById(R.id.button_5),
            findViewById(R.id.button_6),
            findViewById(R.id.button_7),
            findViewById(R.id.button_8),
            findViewById(R.id.button_9)
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                val currentNumber = etResult.text.toString()
                val newNumber = currentNumber + button.text.toString()
                etResult.setText(newNumber)
            }
        }
    }

    fun eventHapus(view: View) {
        etResult.setText("0")
    }

    fun input(view: View) {
        val number = etResult.text.toString().trim()

        if (number.isNotEmpty()) {
            saveCalculation(number)
            etResult.text.clear()
            Toast.makeText(this, "Berhasil Tersimpan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Silakan masukkan angka", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveCalculation(number: String) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_NUMBER, number)
        }

        db.insert(DatabaseHelper.TABLE_NAME, null, values)
        db.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbHelper.close()
    }

    fun laporanButtonClick(view: View) {
        val intent = Intent(this, LaporanActivity::class.java)
        startActivity(intent)
    }

    fun homeButtonClick(view: View) {
    }
}