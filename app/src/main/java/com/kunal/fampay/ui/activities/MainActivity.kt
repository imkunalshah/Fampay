package com.kunal.fampay.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import com.kunal.fampay.R
import com.kunal.fampay.data.prefs.Preferences
import com.kunal.fampay.data.network.models.CardGroups
import com.kunal.fampay.ui.adapters.CardsGroupAdapter
import com.kunal.fampay.ui.fragments.QuitDialogFragment
import com.kunal.fampay.ui.viewmodels.MainActivityViewModel
import com.kunal.fampay.utils.*
import com.saksham.customloadingdialog.hideDialog
import com.saksham.customloadingdialog.showDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getCardGroups()
        swipeRefresh.setOnRefreshListener {
            viewModel.getCardGroups()
            swipeRefresh.isRefreshing = true
        }
        preferences.resetGroupRemindLater()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCallStartEvent(event: CallStartEvent){
        showDialog(this,false,R.raw.loading)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCallSuccessEvent(event: CallSuccessEvent){
        swipeRefresh.isRefreshing = false
        hideDialog()
        viewModel.cardGroupsResp.observe(this){
            Log.i("response",it.toString())
            rv_group_cards.apply {
                val listHC1 : MutableList<CardGroups> = ArrayList()
                val listHC3 : MutableList<CardGroups> = ArrayList()
                val listHC5 : MutableList<CardGroups> = ArrayList()
                val listHC6 : MutableList<CardGroups> = ArrayList()
                val listHC9 : MutableList<CardGroups> = ArrayList()
                val list:MutableList<CardGroups> = ArrayList()
                for (i in it.indices){
                    when {
                        it[i].designType.lowercase() == "hc1" -> {
                            listHC1.add(it[i])
                        }
                        it[i].designType.lowercase() == "hc3" -> {
                            listHC3.add(it[i])
                        }
                        it[i].designType.lowercase() == "hc5" -> {
                            listHC5.add(it[i])
                        }
                        it[i].designType.lowercase() == "hc6" -> {
                            listHC6.add(it[i])
                        }
                        else -> {
                            listHC9.add(it[i])
                        }
                    }
                }
                list.addAll(listHC3)
                list.addAll(listHC6)
                list.addAll(listHC5)
                list.addAll(listHC9)
                list.addAll(listHC1)
                adapter = CardsGroupAdapter(list,this@MainActivity)
            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCallFailEvent(event: CallFailEvent){
        hideDialog()
        swipeRefresh.isRefreshing = false
        root.snackBar(event.message)
    }

    override fun onBackPressed() {
        val quitDialog = QuitDialogFragment()
        val fm: FragmentManager = this.supportFragmentManager
        quitDialog.show(fm,"quit")
    }
}