package arm.android.rxjavaoperatorstimer.timer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import arm.android.rxjavaoperatorstimer.databinding.ItemTimerBinding
import arm.android.rxjavaoperatorstimer.timer.model.MainViewModel

class TimerAdapter(private val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<TimerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemTimerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTimerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val timerVM = mainViewModel.timers.value?.get(position) ?: return // Исправлено
        holder.binding.viewModel = timerVM

        holder.binding.startStop.setOnClickListener {
            timerVM.toggleTimer()
        }

        holder.binding.reset.setOnClickListener {
            timerVM.timerData.value?.let { data ->
                data.elapsedSeconds = 0
                data.isRunning = false
                timerVM.timerData.postValue(data)
            }
        }

        holder.binding.toggleFormat.setOnClickListener {
            timerVM.timerData.value?.let { data ->
                data.showSeconds = !data.showSeconds
                timerVM.timerData.postValue(data)
            }
        }

        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = mainViewModel.timers.value?.size ?: 0 // Исправлено
}