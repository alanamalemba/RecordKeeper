package com.example.recordkeeper.running

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import androidx.core.content.edit

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding
    private val runningPreferences: SharedPreferences by lazy {
        getSharedPreferences(
            "running", Context.MODE_PRIVATE
        )
    }
    private val distance: String? by lazy { intent.getStringExtra("Distance") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "$distance Record"
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonClearRecord.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    private fun clearRecord() {
        binding.editTextRecord.setText("")
        binding.editTextDate.setText("")
        runningPreferences.edit {
            remove("${distance}_record")
            remove("${distance}_date")
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(runningPreferences.getString("${distance}_record", null))
        binding.editTextDate.setText(runningPreferences.getString("${distance}_date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        runningPreferences.edit {
            putString("${distance}_record", record)
            putString("${distance}_date", date)
        }
    }
}