package com.example.projettdmubunto

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.searchpage.*


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchpage)

        //code found
        val ayahDao = RoomService.getDatabase(context=this@SearchActivity).getAyahDo()
        val racineSuggest=ayahDao.getAllRacines().toSet().toList()



        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1 , racineSuggest)
        search_list.adapter = adapter
        search_list.setOnItemClickListener { parent, view, position, id ->
            val intent =Intent(this@SearchActivity,RacineActivity::class.java)

            intent.putExtra("searchQuery",racineSuggest[position])
            startActivity(intent)
        }
        /*
        val arrayAdapter:ArrayAdapter<*>
        val adapter = ArrayAdapter(this, search_list, racineSuggest)
        search_list.adapter = adapter


        */
        //search_list
        menu_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //submit logic
                //pass the query to racine activity
                val intent =Intent(this@SearchActivity,RacineActivity::class.java)

                intent.putExtra("searchQuery",query)
                startActivity(intent)

                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                // suggestion logic
                    //Start filtering the list as user start entering the characters
                    adapter.filter.filter(p0)
                    return false
                /*val itemsAdapter: ArrayAdapter<String> =
                    ArrayAdapter<String>(this, search_list, items)

                listView = (ListView) findViewById(R.id.lvItems);

                listView.setAdapter(itemsAdapter);
                val ayahDao = RoomService.getDatabase(context=this@SearchActivity).getAyahDo()
                val racineSuggest=ayahDao.getRacines(8)*/


            }
        })



    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.layout.searchpage.menu_search)
        return super.onCreateOptionsMenu(menu)
    }*/

}