package com.example.recordkeeper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentCyclingBinding

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.containerLongestClimb.setOnClickListener { launchEditCyclingRecordScreen("Longest Climb") }
        binding.containerBiggestClimb.setOnClickListener { launchEditCyclingRecordScreen("Biggest Climb") }
        binding.containerBestAverageSpeed.setOnClickListener { launchEditCyclingRecordScreen("Best Average Speed") }
    }

    private fun launchEditCyclingRecordScreen(title: String) {
        val intent = Intent(context, EditCyclingRecordActivity::class.java)

        intent.putExtra("Title", title)
        startActivity(intent)
    }
}