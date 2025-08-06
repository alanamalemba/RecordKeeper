package com.example.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()

        displayRecords()
    }

    private fun displayRecords() {
        val runningPreferences =
            requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPreferences.getString("5km_record", null)
        binding.textView5kmDate.text = runningPreferences.getString("5km_date", null)
        binding.textView10kmValue.text = runningPreferences.getString("10km_record", null)
        binding.textView10kmDate.text = runningPreferences.getString("10km_date", null)
        binding.textView20kmValue.text = runningPreferences.getString("20km_record", null)
        binding.textView20kmDate.text = runningPreferences.getString("20km_date", null)
        binding.textView25kmValue.text = runningPreferences.getString("25km_record", null)
        binding.textView25kmDate.text = runningPreferences.getString("25km_date", null)
    }

    private fun setUpClickListeners() {
        binding.container5km.setOnClickListener { launchRunningRecordScreen("5km") }
        binding.container10km.setOnClickListener { launchRunningRecordScreen("10km") }
        binding.container20km.setOnClickListener { launchRunningRecordScreen("20km") }
        binding.container25km.setOnClickListener { launchRunningRecordScreen("25km") }
    }

    private fun launchRunningRecordScreen(distance: String) {

        val intent = Intent(context, EditRunningRecordActivity::class.java)

        intent.putExtra("Distance", distance)


        startActivity(intent)
    }
}