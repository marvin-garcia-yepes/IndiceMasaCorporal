package com.example.indicemasacorporal.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.indicemasacorporal.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val rvCategoryName: TextView = view.findViewById(R.id.rvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit){

        val color = if (taskCategory.isSelected){
            R.color.background_card
        }else{
            R.color.background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener { onItemSelected(layoutPosition) }
        when(taskCategory){
            TaskCategory.Business -> {
                rvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.business_category)
                )
            }
            TaskCategory.Other ->  {
                rvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.other_category)
                )
            }
            TaskCategory.Personal ->  {
                rvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.personal_category)
                )
            }
        }

    }
}