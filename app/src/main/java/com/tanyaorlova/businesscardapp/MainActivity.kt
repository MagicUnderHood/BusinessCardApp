package com.tanyaorlova.businesscardapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var sendMessageButton: Button
    private lateinit var githubButton: ImageButton
    private lateinit var linkedInButton: ImageButton
    private lateinit var telegramButton: ImageButton
    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageTextView = findViewById(R.id.editTextEnterMessage)

        sendMessageButton = findViewById(R.id.bttnSendImage)
        sendMessageButton.setOnClickListener { sendingMessage() }

        githubButton = findViewById(R.id.bttnGithub)
        githubButton.setOnClickListener{openGithub()}

        linkedInButton = findViewById(R.id.bttnLinkedIn)
        linkedInButton.setOnClickListener { openLinkedIn() }

        telegramButton = findViewById(R.id.bttnTelegram)
        telegramButton.setOnClickListener { openTelegram() }
    }

    private fun openTelegram() {
        val uri = Uri.parse("https://t.me/TanyaOrlova")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openLinkedIn() {
        val uri = Uri.parse("https://www.linkedin.com/in/tanyaorlova/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openGithub() {
        val uri = Uri.parse("https://github.com/TanyaOrlova")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun sendingMessage() {
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "tanya.orlova17@gmail.com", null
            )
        )
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.messageSubject))
        intent.putExtra(Intent.EXTRA_TEXT, messageTextView.text)
        startActivity(Intent.createChooser(intent, getString(R.string.chooseEmailClient)))
    }


}
