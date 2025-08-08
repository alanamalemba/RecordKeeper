package com.example.recordkeeper

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.example.recordkeeper.cycling.CyclingFragment
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.example.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.reset_running -> {
            showDeletionConfirmationDialog(RUNNING_DISPLAY_VALUE)
            true
        }

        R.id.reset_cycling -> {
            showDeletionConfirmationDialog(CYCLING_DISPLAY_VALUE)
            true
        }

        R.id.reset_all -> {
            showDeletionConfirmationDialog(ALL_DISPLAY_VALUE)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showDeletionConfirmationDialog(selection: String) {
        AlertDialog.Builder(this).setTitle("Reset $selection records")
            .setMessage("Are you sure you want to delete $selection records?")
            .setPositiveButton("Yes") { _, _ ->
                when (selection) {
                    ALL_DISPLAY_VALUE -> {
                        getSharedPreferences(
                            CyclingFragment.FILE_NAME, Context.MODE_PRIVATE
                        ).edit { clear() }
                        getSharedPreferences(
                            RunningFragment.FILE_NAME, Context.MODE_PRIVATE
                        ).edit { clear() }
                    }

                    RUNNING_DISPLAY_VALUE -> {
                        getSharedPreferences(
                            RunningFragment.FILE_NAME, Context.MODE_PRIVATE
                        ).edit { clear() }
                    }

                    CYCLING_DISPLAY_VALUE -> {
                        getSharedPreferences(
                            CyclingFragment.FILE_NAME, Context.MODE_PRIVATE
                        ).edit { clear() }
                    }
                }
                refreshCurrentFragment()
                showDeletionConfirmationSnackBar()
            }.setNegativeButton("No", null).show()
    }

    private fun showDeletionConfirmationSnackBar() {
        val snackBar = Snackbar.make(
            binding.root, "Records cleared successfully!", Snackbar.LENGTH_LONG
        )
        snackBar.anchorView = binding.bottomNav
        snackBar.setAction("Undo") {
            // some code to restore the records
        }
        snackBar.show()
    }

    private fun refreshCurrentFragment() {
        when (binding.bottomNav.selectedItemId) {
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
        }
    }

    private fun onRunningClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
        return true
    }

    private fun onCyclingClicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.nav_cycling -> onCyclingClicked()

        R.id.nav_running -> onRunningClicked()

        else -> false
    }

    companion object {
        const val RUNNING_DISPLAY_VALUE = "running"
        const val CYCLING_DISPLAY_VALUE = "cycling"
        const val ALL_DISPLAY_VALUE = "all"
    }
}