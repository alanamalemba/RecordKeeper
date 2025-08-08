package com.example.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentCyclingBinding
import com.example.recordkeeper.editrecord.EditRecordActivity
import com.example.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences =
            requireContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

        binding.textViewLongestClimbValue.text = runningPreferences.getString(
            "Longest Climb ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}",
            null
        )
        binding.textViewLongestClimbDate.text = runningPreferences.getString(
            "Longest Climb ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}",
            null
        )
        binding.textViewLongestClimbValue.text = runningPreferences.getString(
            "Longest Climb ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}",
            null
        )
        binding.textViewBiggestClimbValue.text = runningPreferences.getString(
            "Biggest Climb ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}",
            null
        )
        binding.textViewBiggestClimbDate.text = runningPreferences.getString(
            "Biggest Climb ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}",
            null
        )
        binding.textViewBestAverageSpeedValue.text = runningPreferences.getString(
            "Best Average Speed ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}",
            null
        )
        binding.textViewBestAverageSpeedDate.text = runningPreferences.getString(
            "Best Average Speed ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}",
            null
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.containerLongestClimb.setOnClickListener {
            launchEditCyclingRecordScreen(
                "Longest Climb", "Distance"
            )
        }
        binding.containerBiggestClimb.setOnClickListener {
            launchEditCyclingRecordScreen(
                "Biggest Climb", "Height"
            )
        }
        binding.containerBestAverageSpeed.setOnClickListener {
            launchEditCyclingRecordScreen(
                "Best Average Speed", "Average speed"
            )
        }
    }

    private fun launchEditCyclingRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)

        intent.putExtra(
            INTENT_EXTRA_SCREEN_DATA,
            EditRecordActivity.ScreenData(record, FILE_NAME, recordFieldHint)
        )
        startActivity(intent)
    }

    companion object {
        const val FILE_NAME = "cycling"
    }
}