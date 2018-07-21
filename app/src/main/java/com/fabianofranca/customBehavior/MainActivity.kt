package com.fabianofranca.customBehavior

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CustomToolbar.OnClickItemListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = Adapter(arrayOf(1, 2, 3, 4, 5))
        }


        toolbar?.let {
            setSupportActionBar(toolbar)
            it.updateQuickFilter(createFilters())
        }

        toolbar?.listener = this
    }

/*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }*/


    private fun createFilters(): List<CustomToolbar.HeaderQuickFilterItem> {

        val fakeData: ArrayList<CustomToolbar.HeaderQuickFilterItem> = arrayListOf()

        for (idx in 1..7) {
            fakeData.add(CustomToolbar.HeaderQuickFilterItem(
                    "label ${idx}")
            )
        }

        return fakeData
    }

    override fun onClickFilter(headerQuickFilterItem: ArrayList<CustomToolbar.HeaderQuickFilterItem>) {
        val bottomSheetDialogFragment = BottomSheetDialogFragment()
        bottomSheetDialogFragment.show(supportFragmentManager, BottomSheetDialogFragment::javaClass.name)
    }

    override fun onClickMenu(headerQuickFilterItem: CustomToolbar.HeaderQuickFilterItem) {

    }

}

class Adapter(private val dataSet: Array<Int>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView =
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(cardView)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }
}