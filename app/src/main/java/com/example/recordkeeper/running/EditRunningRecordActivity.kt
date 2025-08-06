package com.example.recordkeeper.running

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import androidx.core.content.edit

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val distance = intent.getStringExtra("Distance")
        title = "$distance Record"

        val applicationPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        applicationPreferences.edit {
            putString("someKey", "some value")
        }

        val activityPreferences = getPreferences(Context.MODE_PRIVATE)

        activityPreferences.edit {
            putInt("someKey2", 5)
        }

        val fileNamePreferences = getSharedPreferences("some_file_name", Context.MODE_PRIVATE)

        fileNamePreferences.edit {
            putBoolean("someKey3", false)
        }
    }
}