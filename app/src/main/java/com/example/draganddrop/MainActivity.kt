package com.example.draganddrop
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.draganddrop.adapters.UserAdapter
import com.example.draganddrop.databinding.ActivityMainBinding
import com.example.draganddrop.models.User

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var list:ArrayList<User>
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        userAdapter = UserAdapter(this, list)
        binding.rv.adapter = userAdapter

        rvActions()
    }

    private fun rvActions() {
        val itemTouchHelper = object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags =  ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
        list = ArrayList()
        list.add(User("I can Fly", "https://i1.sndcdn.com/artworks-000562103676-ygjh1u-t500x500.jpg"))
        list.add(User("Memories", "https://images.genius.com/7e73f3a0eb6e93c41e89e3eab9f3c45f.1000x1000x1.jpg"))
        list.add(User("Believer", "https://i1.sndcdn.com/artworks-s3zOCWcV8XQVtQcv-0emq8A-t500x500.jpg"))
        list.add(User("Follow you", "https://i.ytimg.com/vi/1DoI5WTjd3w/maxresdefault.jpg"))
        list.add(User("Natural", "https://i.scdn.co/image/ab67616d0000b273da6f73a25f4c79d0e6b4a8bd"))
        list.add(User("I'm calling you", "https://i.ytimg.com/vi/oALvLDSFuZQ/hqdefault.jpg"))
    }
}