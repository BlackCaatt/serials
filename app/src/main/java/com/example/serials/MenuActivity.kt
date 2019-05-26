package com.example.serials

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.example.serials.FirebaseHelper
import com.example.serials.ValueEventListenerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.serial_row.view.*



class MenuActivity : BaseActivity(1) {
    private val TAG = "MenuActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setupBottomNavigation()

        fetchSerials()

    }

    private fun fetchSerials() {
        val ref = FirebaseDatabase.getInstance().getReference("/serials")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(p0: DataSnapshot){
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach{
                    Log.d("newMessage", it.toString())
                    val serial = it.getValue(Serial::class.java)
                    if (serial != null) {
                    adapter.add(SerialItem(serial))

                 }
                }
                recyclerview_serials.adapter = adapter

            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

    }

}


class SerialItem(val serial: Serial): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.textview_serials.text = serial.name

        Picasso.get().load(serial.image).into(viewHolder.itemView.imageview_serials)
    }

    override fun getLayout(): Int {
        return R.layout.serial_row
    }
}

class Serial(val name: String, val image:String) {
    constructor():this("", "")
}


