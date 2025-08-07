package com.example.recordkeeper.editrecord

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable

const val INTENT_EXTRA_SCREEN_DATA = "screenData"

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding

    private val screenData: ScreenData by lazy {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                INTENT_EXTRA_SCREEN_DATA,
                ScreenData::class.java
            ) as ScreenData
        } else {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
        }
    }
    private val recordReferences: SharedPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferencesName, Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()
    }

    private fun setupUi() {
        title = "${screenData.record} Record"
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonClearRecord.setOnClickListener {
            clearRecord()
            finish()
        }

        binding.textInputLayoutRecord.hint = screenData.recordFieldHint
    }

    private fun clearRecord() {
        binding.editTextRecord.setText("")
        binding.editTextDate.setText("")
        recordReferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(
            recordReferences.getString(
                "${screenData.record} record", null
            )
        )
        binding.editTextDate.setText(recordReferences.getString("${screenData.record} date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        recordReferences.edit {
            putString("${screenData.record} record", record)
            putString("${screenData.record} date", date)
        }
    }

    data class ScreenData(
        val record: String, val sharedPreferencesName: String, val recordFieldHint: String
    ) : Serializable
}