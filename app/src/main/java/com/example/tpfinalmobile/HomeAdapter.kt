import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalmobile.HomeItem
import com.example.tpfinalmobile.R

class HomeAdapter(private val items: List<HomeItem>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val iconImage: ImageView = view.findViewById(R.id.itemIcon)
        private val textView: TextView = view.findViewById(R.id.itemText)
        private val container: View = view.findViewById(R.id.containerItem)

        fun bind(item: HomeItem) {
            iconImage.setImageResource(item.iconResId)
            textView.text = item.text
            textView.setTextColor(ContextCompat.getColor(itemView.context, item.textColorResId))

            if (item.backgroundResId != 0) {
                container.setBackgroundResource(item.backgroundResId)
            } else {
                container.background = null
            }
        }
    }
}
