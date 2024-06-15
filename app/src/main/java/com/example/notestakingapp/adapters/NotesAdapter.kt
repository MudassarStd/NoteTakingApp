package com.example.notestakingapp.adapters

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.notestakingapp.R
import androidx.recyclerview.widget.RecyclerView
import com.example.notestakingapp.database.Note


class NotesAdapter() : RecyclerView.Adapter<NotesViewHolder>() {

    private var allNotes: List<Note> = emptyList()
    private var notesToShow: MutableList<Note> = mutableListOf()
    private lateinit var i_listener: onNoteItemClickListener

    interface onNoteItemClickListener {

        fun onNoteItemClick(itemPosition: Int)
        fun onNoteItemLongClick(itemPosition: Int)

    }
    // this method will trigger our interface
    fun setOnItemClickListener(listener: onNoteItemClickListener) {
        i_listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.notes_item, parent, false)
        return NotesViewHolder(view, i_listener)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

    }
    override fun getItemCount(): Int {
        return notesToShow.size
    }

    fun updateNotesData(notes : List<Note>)
    {
        allNotes = notes

        notesToShow.clear()
        notesToShow.addAll(notes)

        notifyDataSetChanged()
    }

    fun filterNotesBasedOnSearch(searchQuery : String)
    {

        notesToShow.clear()
        val lowerSQ = searchQuery.lowercase()
        notesToShow = allNotes.filter { it.noteTitle.lowercase().contains(lowerSQ) || it.noteDescription.lowercase().contains(lowerSQ)}.toMutableList()
        notifyDataSetChanged()
    }

    // implement
    private fun randomColor() : Int
    {
        return 0
    }
}
class NotesViewHolder(itemView : View, listener : NotesAdapter.onNoteItemClickListener): RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            listener.onNoteItemClick(adapterPosition)
        }

        itemView.setOnLongClickListener {
            listener.onNoteItemLongClick(adapterPosition)
            true
        }
    }
}