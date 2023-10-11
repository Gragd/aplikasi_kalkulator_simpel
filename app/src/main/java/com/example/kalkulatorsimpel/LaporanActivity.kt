package com.example.kalkulatorsimpel

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LaporanActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        recyclerView = findViewById(R.id.recyclerView)
        dbHelper = DatabaseHelper(this)

        val calculationList = getAllCalculations()
        val adapter = HistoryAdapter(calculationList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val buttonHapusSemua = findViewById<Button>(R.id.buttonHapusSemua)
        buttonHapusSemua.setOnClickListener {
            hapusSemuaItem()
        }
    }

    private fun hapusSemuaItem() {
        dbHelper.hapusSemuaItem()

        val adapter = recyclerView.adapter as HistoryAdapter
        val itemCount = adapter.itemCount
        adapter.clearData()
        adapter.notifyItemRangeRemoved(0, itemCount)
    }

    private fun getAllCalculations(): List<String> {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        val projection = arrayOf(DatabaseHelper.COLUMN_NUMBER)
        val cursor: Cursor = db.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null, null)

        val calculationList = mutableListOf<String>()
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NUMBER)
            do {
                val number = cursor.getString(columnIndex)
                calculationList.add(number)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return calculationList
    }

    fun laporanButtonClick(view: View) {
    }

    fun homeButtonClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}