package org.zakaria.nfiller

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    // array of fake messages
    private val fakeMessages = arrayOf<String>(
        "Hey, you up?",
        "heyyy can you do me a favour?",
        "yea exactly",
        "yo",
        "Bruh he said that?",
        "amongus? xp",
        "yeah thanks!",
        "brb talk later",
        "Feel free to give some feedback on that.",
        "You called?",
        "That's what I was saying"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup notif channel
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(
                    applicationContext.getString(R.string.notif_channel_id),
                    getString(R.string.notif_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            } else {
                // TODO ?
                TODO("VERSION.SDK_INT < O")
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
        // get notification manager
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // get views
        val fillBtn    = findViewById<Button>(R.id.start_button)
        val clearBtn   = findViewById<Button>(R.id.clear_button)
        val numberText = findViewById<EditText>(R.id.notif_count)

        fillBtn.setOnClickListener {
            val notifNumber = numberText.text.toString().toInt()

            // intent stuff
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                0, notificationIntent, 0
            )

            for (i in 0..notifNumber) {

                // build notification
                val builder = NotificationCompat.Builder(applicationContext,
                    applicationContext.getString(R.string.notif_channel_id)
                )
                    .setSmallIcon(R.drawable.ic_chat_bubble)
                    .setContentTitle(getString(R.string.notif_title))
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setContentText(fakeMessages[Random.nextInt(0, fakeMessages.size)]) // randomly choose a fake message
                    .setPriority(NotificationCompat.PRIORITY_HIGH)

                // send the notification
                manager.notify(i, builder.build())

                // random sleep 100-200ms
                Thread.sleep((Random.nextInt(0, 2) * 100).toLong())
            }
        }

        // clear all app notifications
        clearBtn.setOnClickListener {
            manager.cancelAll()
        }
    }
}