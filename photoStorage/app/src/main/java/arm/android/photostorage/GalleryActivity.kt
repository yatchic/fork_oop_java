package arm.android.photostorage

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = GalleryAdapter(loadImages())
    }

    private fun loadImages(): List<File> {
        val imagesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return imagesDir?.listFiles()?.filter { it.extension == "jpg" } ?: emptyList()
    }
}

class GalleryAdapter(private val images: List<File>) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(images[position])
            .into(holder.imageView)
    }

    override fun getItemCount() = images.size
}