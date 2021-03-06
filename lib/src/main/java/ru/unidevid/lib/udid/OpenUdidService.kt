package ru.unidevid.lib.udid

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.Parcel

internal class OpenUdidService : Service() {

    override fun onBind(arg0: Intent): IBinder? {
        return object : Binder() {
            public override fun onTransact(
                code: Int,
                data: Parcel,
                reply: Parcel?,
                flags: Int
            ): Boolean {
                val preferences = getSharedPreferences(OpenUuidManager.PREFS_NAME, MODE_PRIVATE)
                reply?.writeInt(data.readInt()) //Return to the sender the input random number
                reply?.writeString(preferences.getString(OpenUuidManager.PREF_KEY, null))
                return true
            }
        }
    }
}