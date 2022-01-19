package org.tuwaiq.carwash.views.userViews.userMainActivity.homeFragment.step4AppointmentBooked

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import org.tuwaiq.carwash.R

const val notificationId = 1
const val channelId = "Channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"
class NotifyUser: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notification : Notification = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent?.getStringExtra(titleExtra))
            .setContentText(intent?.getStringExtra(messageExtra))
            .build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId,notification)
    }
}