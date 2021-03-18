package com.hyperclock.instant.keepfit.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyperclock.instant.keepfit.R
import com.hyperclock.instant.keepfit.model.Activity
import kotlinx.android.synthetic.main.activities_row_layout.view.*

class ActivitiesAdapter(private val context: Context) :
    RecyclerView.Adapter<ActivitiesAdapter.MyViewHolder>() {

    private var activityList = listOf<Activity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activities_row_layout, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val activity = activityList[position]

        holder.itemView.activeDuration.text = activity.activeDuration.toString()
        holder.itemView.activityName.text = activity.activityName
        holder.itemView.calories.text = activity.calories.toString()
        holder.itemView.distance.text = activity.distance.toString()
        holder.itemView.distanceUnit.text = activity.distanceUnit
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    fun setData(newIngredients: List<Activity>) {
        Log.d("ActivitiesAdapter", "Loading new list")
        activityList = newIngredients
        notifyDataSetChanged()
    }
}