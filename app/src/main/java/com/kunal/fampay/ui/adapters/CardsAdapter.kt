package com.kunal.fampay.ui.adapters

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Handler
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.kunal.fampay.R
import com.kunal.fampay.data.network.models.Cards
import com.kunal.fampay.utils.ImageLoader
import com.kunal.fampay.utils.TextFormatter

class CardsAdapter(
    private val context:Context,
    private val cards:MutableList<Cards>,
    private val cardType:String,
    private val height:Int = 0,
    private val listener:OnActionListener
): RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {

    interface OnActionListener{
        fun onDismiss()
        fun onRemindLater()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return when(cardType.lowercase()){
            "hc1"->{
                CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_hc1, parent, false))
            }
            "hc3"->{
                CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_hc3, parent, false))
            }
            "hc5"->{
                CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_hc5, parent, false))
            }
            "hc6"->{
                CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_hc6, parent, false))
            }
            else ->{
                CardViewHolder(LayoutInflater.from(context).inflate(R.layout.card_hc9, parent, false))
            }
        }
    }


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        when(cardType.lowercase()){
            "hc1"->{
                holder.renderHC1Card(card)
            }
            "hc3"->{
                holder.renderHC3Card(card)
            }
            "hc5"->{
                holder.renderHC5Card(card)
            }
            "hc6"->{
                holder.renderHC6Card(card)
            }
            "hc9"->{
                holder.renderHC9Card(card)
            }
        }
    }

    override fun getItemCount(): Int = cards.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun renderHC1Card(card:Cards){
            val root:CardView = itemView.findViewById(R.id.card)
            val title:TextView = itemView.findViewById(R.id.title)
            val subTitle:TextView = itemView.findViewById(R.id.subTitle)
            val image:ImageView = itemView.findViewById(R.id.image)

            TextFormatter.applyFormattedText(
                card.formattedTitle, title, card.title
            )

            TextFormatter.applyFormattedText(
                card.formattedDescription, subTitle, card.description
            )

            if (!card.bgColor.isNullOrBlank() && !card.bgColor.isNullOrEmpty()){
                val colorInt = Color.parseColor(card.bgColor)
                val csl = ColorStateList.valueOf(colorInt)
                root.backgroundTintList = csl
            }



            card.icon.imageUrl.let {
                ImageLoader.loadImage(it, itemView, image)
            }

            itemView.setOnClickListener {
                if (!card.url.isNullOrBlank() && !card.url.isNullOrEmpty()) {
                    performClick(card.url)
                }
            }
        }

        fun renderHC3Card(card:Cards){
            val title:TextView = itemView.findViewById(R.id.title)
            val description:TextView = itemView.findViewById(R.id.description)
            val image:ImageView = itemView.findViewById(R.id.image)
            val actionBtn:AppCompatButton = itemView.findViewById(R.id.actionBtn)
            val options:LinearLayout = itemView.findViewById(R.id.options)
            val btnRemindLater:CardView = itemView.findViewById(R.id.btnRemindLater)
            val btnDismissNow:CardView = itemView.findViewById(R.id.btnDismissNow)



            TextFormatter.applyFormattedText(
                card.formattedTitle, title, card.title
            )

            TextFormatter.applyFormattedText(
                card.formattedDescription, description, card.description
            )

            card.bgImage.imageUrl.let {
                ImageLoader.loadImage(it,itemView,image)
            }

            card.cta[0].let { cta ->
                val colorInt = Color.parseColor(cta.bgColor)
                val csl = ColorStateList.valueOf(colorInt)
                actionBtn.backgroundTintList = csl
                actionBtn.text = cta.text
                actionBtn.setTextColor(Color.parseColor(cta.textColor))
                actionBtn.setOnClickListener {
                    if (!card.url.isNullOrBlank() && !card.url.isNullOrEmpty()) {
                        performClick(card.url)
                    }
                }
            }

            itemView.setOnClickListener {
                if (options.isVisible){
                    options.visibility = View.GONE
                }
            }
            itemView.setOnLongClickListener {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                val handler = Handler()
                val runnable = Runnable {
                    if (options.isVisible){
                        options.visibility = View.GONE
                    }else{
                        options.visibility = View.VISIBLE
                    }
                    vibrator.vibrate(150L)
                }
                handler.postDelayed(runnable,200)
                true
            }

            // Handle the lists nextlaunch and never
            btnDismissNow.setOnClickListener {
                listener.onDismiss()
            }

            btnRemindLater.setOnClickListener {

                listener.onRemindLater()
            }

        }

        fun renderHC5Card(card:Cards){
            val root:CardView = itemView.findViewById(R.id.card)
            val image:ImageView = itemView.findViewById(R.id.image)
            card.bgImage.imageUrl.let {
                ImageLoader.loadImage(it,itemView,image)
            }

            root.setOnClickListener {
                if (!card.url.isNullOrBlank() && !card.url.isNullOrEmpty()) {
                    performClick(card.url)
                }
            }
        }

        fun renderHC6Card(card:Cards){
            val root:CardView = itemView.findViewById(R.id.card)
            val icon:ImageView = itemView.findViewById(R.id.icon)
            val title:TextView = itemView.findViewById(R.id.title)
            TextFormatter.applyFormattedText(
                card.formattedTitle,title, card.title
            )

            if (!card.bgColor.isNullOrBlank() && !card.bgColor.isNullOrEmpty()){
                val colorInt = Color.parseColor(card.bgColor)
                val csl = ColorStateList.valueOf(colorInt)
                root.backgroundTintList = csl
            }

            ImageLoader.loadImage(card.icon.imageUrl, itemView, icon)

            root.setOnClickListener {
                if (!card.url.isNullOrBlank() && !card.url.isNullOrEmpty()) {
                    performClick(card.url)
                }
            }
        }

        fun renderHC9Card(card:Cards){
            val image:ImageView = itemView.findViewById(R.id.image)

            val params: ViewGroup.LayoutParams = itemView.layoutParams
            params.height = height
            itemView.layoutParams = params
            ImageLoader.loadImage(card.bgImage.imageUrl,itemView,image)

            itemView.setOnClickListener {
                if (!card.url.isNullOrBlank() && !card.url.isNullOrEmpty()) {
                    performClick(card.url)
                }
            }
        }

        private fun performClick(url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

}