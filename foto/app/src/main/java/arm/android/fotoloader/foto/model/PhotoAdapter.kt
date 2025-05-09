package arm.android.fotoloader.foto.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import arm.android.fotoloader.databinding.ItemPhotoBinding
import com.bumptech.glide.Glide


class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private var items = emptyList<Photo>()

    inner class ViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = items[position]
        holder.binding.tvTitle.text = photo.title

        Glide.with(holder.itemView.context)
            .load(photo.url)
            .into(holder.binding.ivPhoto)


    }

    fun setPhotos(photos: List<Photo>) {
        items = photos
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}