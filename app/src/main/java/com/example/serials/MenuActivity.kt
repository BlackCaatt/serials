package com.example.serials

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.serial_row.view.*



class MenuActivity : BaseActivity(1) {
    private val TAG = "MenuActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        setupBottomNavigation()
        recyclerview_serials.layoutManager= GridLayoutManager(this,2)
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

class SquareImageItem(context: Context, attrs:AttributeSet) : ImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}
