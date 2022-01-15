package com.kunal.fampay.data.prefs

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class Preferences
@Inject
constructor(
    context: Context
){
    val preferences =
        PreferenceManager.getDefaultSharedPreferences(context)
    private val REMIND_LATER_KEY = "remind_later"
    private val DISMISS_NOW_KEY = "dismiss_now"

    fun addGroupIdRemindLater(groupId: String) {
        if (preferences != null) {
            var storedString = preferences.getString(REMIND_LATER_KEY, "").toString()
            storedString = storedString.plus(",").plus(groupId)
            val editor = preferences.edit()
            editor.putString(REMIND_LATER_KEY, storedString)
            editor.apply()
        }
    }

    fun excludeGroupRemindLater(groupId: String): Boolean {
        if (preferences != null) {
            val storedString = preferences.getString(REMIND_LATER_KEY, "")
            val stringList = storedString?.split(",")
            if (stringList != null) {
                for (id in stringList) {
                    if (id == groupId) {
                        return true
                    }
                }
            } else {
                return false
            }
        }
        return false
    }

    fun addGroupIdDismissNow(groupId: String) {
        if (preferences != null) {
            var storedString = preferences.getString(DISMISS_NOW_KEY, "").toString()
            storedString = storedString.plus(",").plus(groupId)
            val editor = preferences.edit()
            editor.putString(DISMISS_NOW_KEY, storedString)
            editor.apply()
        }
    }

    fun excludeGroupDismissNow(groupId: String): Boolean {
        if (preferences != null) {
            val storedString = preferences.getString(DISMISS_NOW_KEY, "")
            val stringList = storedString?.split(",")
            if (stringList != null) {
                for (id in stringList) {
                    if (id == groupId) {
                        return true
                    }
                }
            } else {
                return false
            }
        }
        return false
    }

    fun resetGroupRemindLater(){
        if (preferences != null) {
            val editor = preferences.edit()
            editor.putString(REMIND_LATER_KEY, "")
            editor.apply()
        }
    }
}
