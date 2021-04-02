package com.app.notificationalert

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Handler

class NotificationService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val title = intent?.getStringExtra("title") ?: ""
        val content = intent?.getStringExtra("content") ?: ""
        val delayMins = intent?.getLongExtra("delay", 0) ?: 0L


        val runner = Runnable {
            val notifier = Notifier(this)
            notifier.sendNotification(title, content)
        }

        val handler = Handler()
        handler.postDelayed(runner,1000*delayMins)

        return START_STICKY
    }
}