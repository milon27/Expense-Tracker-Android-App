package com.m27lab.expensetracker.views.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.m27lab.expensetracker.R
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity
import com.m27lab.expensetracker.utils.Utils
import com.m27lab.expensetracker.viewmodels.ExpenseViewModel
import com.m27lab.expensetracker.views.adapters.NoteListAdapter
import com.m27lab.expensetracker.views.dialogs.AddItemDialog
import com.m27lab.expensetracker.views.dialogs.OnAddItemListener
import kotlinx.android.synthetic.main.fragment_note.*


class NoteFragment : Fragment(R.layout.fragment_note) {

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var adapter: NoteListAdapter
    private lateinit var contxt :Context
    private val TAG = "NoteFragment"
    override fun onAttach(context: Context) {
        super.onAttach(context)
        contxt=context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set status bar white
        Utils.setWhiteStatusBar(contxt)

        /*val db = ShoppingDB.invoke(context = contxt);
        val repo = ExpenseRepo(db);
        val factory = ShoppingViewModelFactory(repo);*/

        val list = ArrayList<ExpenseEntity>()

        //viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java);
        viewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java);
        adapter = NoteListAdapter(contxt,list, viewModel)

        //set :: recyclerView.layoutManager=LinearLayoutManager(this)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_shoppinglist)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.getAllItems(contxt).observe(viewLifecycleOwner, Observer {
            adapter.items = it
            Log.d(TAG, "onCreate: ${it.size}   ")
            adapter.notifyDataSetChanged()
        })

        //fab btton
        fabBtn.setOnClickListener{
            AddItemDialog(contxt, object : OnAddItemListener {
                override fun onAddItem(item: ExpenseEntity) {
                    //Log.d(TAG, "onAddItem: $item")
                    viewModel.set(contxt,item)
                    Toast.makeText(contxt, "Added Item", Toast.LENGTH_SHORT).show()
                }
            }).show()
        }

        //search button
        search_bar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text: String = s.toString().trim()
                if (!text.isEmpty() && text.length > 2) {
                    viewModel.searchNow(contxt,text).observe(viewLifecycleOwner, Observer
                    {
                        adapter.items = it
                        list.addAll(it)
                        adapter.notifyDataSetChanged()
                    })
                } else {
                    viewModel.getAllItems(contxt).observe(viewLifecycleOwner, Observer {
                        adapter.items = it
                        //Log.d("TAG", "onCreate: ${list.size}   "+it.get(0).title)
                        adapter.notifyDataSetChanged()
                    })

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


    }
}