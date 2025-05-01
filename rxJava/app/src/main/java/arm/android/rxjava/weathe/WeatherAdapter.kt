package arm.android.rxjava.weathe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import arm.android.rxjava.databinding.ItemWeatherBinding
class WeatherAdapter : ListAdapter<WeatherResponse, WeatherAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemWeatherBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: WeatherResponse) {
            binding.textCity.text = data.name
            binding.textTemp.text = "${data.main.temp}°C"
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherResponse>() {
            override fun areItemsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse) =
                oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: WeatherResponse, newItem: WeatherResponse) =
                oldItem == newItem
        }
    }
}