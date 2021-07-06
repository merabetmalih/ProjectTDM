package com.example.projettdmubunto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.projettdmubunto.Ayah
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class AyahAdapter(val context:Context, var data:List<Ayah>, val onClick :()->Unit):
    RecyclerView.Adapter<MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return  MyViewHolder(LayoutInflater.from(context).inflate(R.layout.ayah, parent, false))

    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.Ayahar.text=data[position].textAR
        holder.Ayahen.text=data[position].englishWord
        holder.Ayahar.setOnClickListener {
            onClick()
        }
        holder.Ayahen.setOnClickListener {
            onClick
        }
        holder.saveImage.setOnClickListener {

            val database = Firebase.database
            val Ref = database.getReference("message")

            Ref.setValue("Hello, World!")
            val myRef = database.getReference("ayas_data").push()
            Toast.makeText(context, myRef.key, Toast.LENGTH_SHORT).show()
            myRef.child("textAr").setValue(data[position].textAR)
            myRef.setValue(data[position].IDAya).addOnCompleteListener {
                Toast.makeText(context, "SAVED", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount()=data.size
    }


class MyViewHolder(view:View):RecyclerView.ViewHolder(view){

    val Ayahar=view.findViewById<TextView>(R.id.ayahAr) as TextView
    val Ayahen=view.findViewById<TextView>(R.id.ayahen) as TextView
    val saveImage  = view.findViewById<ImageView>(R.id.imageView3) as ImageView

}
const val TAG= "AYAH ADAPTER"

