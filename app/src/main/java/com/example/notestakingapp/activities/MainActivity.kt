package com.example.notestakingapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notestakingapp.R
import com.example.notestakingapp.adapters.NotesAdapter
import com.example.notestakingapp.databinding.ActivityMainBinding
import com.example.notestakingapp.datamodels.NotesViewModel

class MainActivity : AppCompatActivity() , NotesAdapter.onNoteItemClickListener{

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : NotesViewModel
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var rvNotes: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        notesAdapter = NotesAdapter()
        notesAdapter.setOnItemClickListener(this)

        rvNotes = binding.rvNotes
        rvNotes.adapter = notesAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)

        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }



      observeNotesLiveData()

    }

    private fun observeNotesLiveData()
    {
        viewModel.notes.observe(this){
            notesAdapter.updateNotesData(it)
            Log.d("Obthsoigh","${it}")
        }
    }
    override fun onNoteItemClick(itemPosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onNoteItemLongClick(itemPosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        observeNotesLiveData()
    }
}