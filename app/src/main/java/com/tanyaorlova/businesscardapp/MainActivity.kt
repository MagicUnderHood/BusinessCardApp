package com.tanyaorlova.businesscardapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
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
        githubButton.setOnClickListener{ openLink(getString(R.string.my_github)) }

        linkedInButton = findViewById(R.id.bttnLinkedIn)
        linkedInButton.setOnClickListener { openLink(getString(R.string.my_linkedin)) }

        telegramButton = findViewById(R.id.bttnTelegram)
        telegramButton.setOnClickListener { openLink(getString(R.string.my_telegram)) }
    }

    private fun openLink(link: String){
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        else{
            Toast.makeText(this, R.string.error_no_link_app, Toast.LENGTH_LONG).show()
        }
    }

    private fun sendingMessage() {
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", getString(R.string.email_address), null
            )
        )
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.messageSubject))
        intent.putExtra(Intent.EXTRA_TEXT, messageTextView.text)
        if (intent.resolveActivity(packageManager) != null){
            startActivity(Intent.createChooser(intent, getString(R.string.chooseEmailClient)))
        }
        else{
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show()
        }
    }


}
