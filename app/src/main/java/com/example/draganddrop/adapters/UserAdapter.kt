package com.example.draganddrop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.draganddrop.databinding.LayoutBinding
import com.example.draganddrop.models.User
import com.example.draganddrop.utils.ItemTouchHelperAdapter
import com.squareup.picasso.Picasso
import java.util.Collections

class UserAdapter(val list:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.Vh>(),
    ItemTouchHelperAdapter {
    inner class Vh(val itemRvBinding: LayoutBinding):RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(user: User, position: Int) {
            itemRvBinding.itemTxt.text = user.name
            Picasso.get().load(user.img).into(itemRvBinding.itemImg)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list,i, i+1 )
            }
        }else{
            for (i in fromPosition until toPosition-1){
                Collections.swap(list,i,i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}