   package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

   class MainActivity : AppCompatActivity(),INotes {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvShow.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        rvShow.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
            it?.let {
                adapter.updateList(it)
                adapter.notifyDataSetChanged()
            }
        })


    }

       override fun onItemclicked(note: Note) {
            viewModel.delete(note)
           Toast.makeText(this,"deleted",Toast.LENGTH_SHORT).show()
       }

       fun submitButton(view: View) {
           val noteText = etInput.text.toString()
           if(noteText.isNotEmpty()){
               viewModel.insert(Note(noteText))
               Toast.makeText(this,"inserted",Toast.LENGTH_SHORT).show()
           }

       }


   }