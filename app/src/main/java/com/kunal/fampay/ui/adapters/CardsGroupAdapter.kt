package com.kunal.fampay.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.fampay.R
import com.kunal.fampay.data.prefs.Preferences
import com.kunal.fampay.data.network.models.CardGroups

class CardsGroupAdapter(
    private val cardGroups: MutableList<CardGroups>,
    private val context:Context
): RecyclerView.Adapter<CardsGroupAdapter.GroupCardViewHolder>() {

    private val preferences: Preferences = Preferences(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupCardViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return  GroupCardViewHolder(layoutInflater.inflate(R.layout.layout_card, parent, false))
    }

    override fun onBindViewHolder(holder: GroupCardViewHolder, position: Int) {
        val pos = position
        val cards = cardGroups[position].cards
        val groupId = cardGroups[position].id.toString()
        if (!preferences.excludeGroupDismissNow(groupId) && !preferences.excludeGroupRemindLater(groupId)){
            if (cardGroups[position].isScrollable){
                holder.rvCardsVertical.visibility = View.GONE
                holder.rvCardsHorizontal.visibility = View.VISIBLE
                holder.rvCardsHorizontal.apply {
                    adapter = CardsAdapter(context,cards.toMutableList(),cardGroups[position].designType,(cardGroups[position].height * 3),object : CardsAdapter.OnActionListener{
                        override fun onDismiss() {
                            preferences.addGroupIdDismissNow(groupId)
                            notifyDataSetChanged()
                            cardGroups.removeAt(pos)
                        }

                        override fun onRemindLater() {
                            preferences.addGroupIdRemindLater(groupId)
                            notifyDataSetChanged()
                            cardGroups.removeAt(pos)
                        }
                    })
                }
            }else{
                holder.rvCardsVertical.visibility = View.VISIBLE
                holder.rvCardsHorizontal.visibility = View.GONE
                holder.rvCardsVertical.apply {
                    adapter = CardsAdapter(context,cards.toMutableList(),cardGroups[position].designType,(cardGroups[position].height * 3),object : CardsAdapter.OnActionListener{
                        override fun onDismiss() {
                            preferences.addGroupIdDismissNow(groupId)
                            notifyDataSetChanged()
                            cardGroups.removeAt(pos)
                        }

                        override fun onRemindLater() {
                            preferences.addGroupIdRemindLater(groupId)
                            notifyDataSetChanged()
                            cardGroups.removeAt(pos)
                        }
                    })
                }
            }
        }

    }
    override fun getItemCount(): Int = cardGroups.size

    class GroupCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvCardsVertical:RecyclerView = itemView.findViewById(R.id.rv_cards_vertical)
        val rvCardsHorizontal:RecyclerView = itemView.findViewById(R.id.rv_cards_horizontal)
    }

}