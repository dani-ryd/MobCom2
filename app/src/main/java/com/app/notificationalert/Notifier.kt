package com.app.notificationalert

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Content
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notifier(val context: Context) {

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= BUILD.VERSION_CODES.O){

            val channelName = context.getString(R.string.channelName)
            val channelDescription = context.getString(R.string.channelDescription)
            val channelId = channelName
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(channelId, channelName, importance).apply{
                description = channelDescription
            }

            val notificationManager : NotificationManager = context.getSystemService(context.NOTIFICATION_SERVICE) as NotificationManager
            notificationMananger.createNotificationChannel(channel)
        }
    }

    private fun notification(title: String, content: String) : Notification{
        val intent = Intent(context, ViewReminderActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        intent.putExtra("tittle",tittle)

        val requestCode = tittle.hashCode()
        val pendingIntent = PendingIntent.getActivity(context,requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, context.getString(R.string.channelName))
        builder.setSmallIcon(R.drawable.baseline_done_outline_black_18)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setContentIntent(pendingIntent)
        return builder.build()
    }

    fun sendNotification(title: String, content: String){
        val notification = notification(title, content)

        with(NotificationManagerCompat.from(context)){
            val notificationId = title.hashCode()
            notify(notificationId, notification)
        }

    }
}