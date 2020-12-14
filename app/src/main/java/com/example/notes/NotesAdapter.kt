package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context: Context,val listener:INotes): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val noteViewHolder= NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))

        noteViewHolder.ivDelete.setOnClickListener {
            listener.onItemclicked(allNotes[noteViewHolder.adapterPosition])
        }

        return noteViewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val currentNote = allNotes[position]
        holder.tvData.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
    }


    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvData = itemView.findViewById<TextView>(R.id.tvData)
        val ivDelete = itemView.findViewById<ImageView>(R.id.ivDelete)

    }
}

interface INotes{
    fun onItemclicked(note: Note)
}