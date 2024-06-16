package com.example.notestakingapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notestakingapp.R
import com.example.notestakingapp.database.Note
import com.example.notestakingapp.databinding.ActivityAddNoteBinding
import com.example.notestakingapp.datamodels.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNoteBinding
    private lateinit var viewModel : NotesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        binding.ivAddNote.setOnClickListener {
            val title = binding.etAddTitle.text.toString()
            val noteContent = binding.etAddNote.text.toString()

            val note = Note(0, title, noteContent,"null" )

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.insertNote(note)

            }

        }




    }
}