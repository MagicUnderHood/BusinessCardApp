package com.tanyaorlova.businesscardapp

import android.app.ActionBar
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginEnd


class MainActivity : AppCompatActivity() {

    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageTextView = findViewById(R.id.editTextEnterMessage)

        val sendMessageButton: Button = findViewById(R.id.bttnSendImage)
        sendMessageButton.setOnClickListener { sendingMessage() }

        val githubButton: ImageButton = findViewById(R.id.bttnGithub)
        githubButton.setOnClickListener { openLink(getString(R.string.my_github)) }

        val linkedInButton: ImageButton = findViewById(R.id.bttnLinkedIn)
        linkedInButton.setOnClickListener { openLink(getString(R.string.my_linkedin)) }

        val telegramButton: ImageButton = findViewById(R.id.bttnTelegram)
        telegramButton.setOnClickListener { openLink(getString(R.string.my_telegram)) }

        val disclaimerTextView = TextView(this)
        disclaimerTextView.layoutParams =
            LinearLayout.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        disclaimerTextView.text = getString(R.string.disclaimer)
        disclaimerTextView.gravity = Gravity.END
        disclaimerTextView.setPadding(0, 0, 16, 0)
        val activityLayout = findViewById<LinearLayout>(R.id.activity_layout)
        activityLayout.addView(disclaimerTextView)
    }

    private fun openLink(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
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
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, getString(R.string.chooseEmailClient)))
        } else {
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show()
        }
    }
}
