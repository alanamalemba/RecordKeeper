package com.example.recordkeeper.cycling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recordkeeper.R
import com.example.recordkeeper.databinding.ActivityEditCyclingRecordBinding

class EditCyclingRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCyclingRecordBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingRecordBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_edit_cycling_record)

        val titlePrefix = intent.getStringExtra("Title")

        title = "$titlePrefix Record"
    }
}