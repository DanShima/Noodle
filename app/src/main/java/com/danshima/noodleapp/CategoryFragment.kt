package com.danshima.noodleapp

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danshima.noodleapp.data.Noodle
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * A subclass displayed in the main page that shows a list of noodle per category.
 */
class CategoryFragment : androidx.fragment.app.Fragment() {
    private lateinit var cursor: Cursor
    private lateinit var database: SQLiteDatabase
    private lateinit var viewmodel: NoodleViewModel
    private lateinit var listAdapter: NoodleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(NoodleViewModel::class.java)
        viewmodel.getAllNoodles().observe(this, Observer<List<Noodle>> {
            listAdapter.setNoodles(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerview()
    }

    private fun initRecyclerview() {
        list_recyclerview.apply {
            this.layoutManager = LinearLayoutManager(activity).apply {
                orientation = RecyclerView.VERTICAL
            }
        }
        listAdapter = NoodleListAdapter(this::openNoodle)
        list_recyclerview.adapter = listAdapter
    }

    private fun openNoodle(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.CHOSEN_NOODLE_ITEM, position)
        startActivity(intent)
        Toast.makeText(requireContext(), "ha $position", Toast.LENGTH_SHORT).show()
    }

    /**
     * This method is called when the database and cursor need to be closed.
     * They are closed when the cursor adapter doesn't need them anymore.
     */
    override fun onDestroy() {
        super.onDestroy()
        cursor.close()
        database.close()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoryFragment()
    }
}
















