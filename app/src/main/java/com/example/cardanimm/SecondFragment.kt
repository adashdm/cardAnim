package com.example.cardanimm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.secondfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sendButton: Button = view.findViewById(R.id.button)
        sendButton.setOnClickListener {
            val emailEditText = view.findViewById<EditText>(R.id.editTextTextEmailAddress2)
            val textMessageEditText = view.findViewById<EditText>(R.id.editTextTextMultiLine2)
            val recipientEmail = emailEditText.text.toString()
            val textMessage = textMessageEditText.text.toString()
            if (recipientEmail.isNotEmpty() && textMessage.isNotEmpty()) {
                sendEmail(recipientEmail, textMessage)
            } else {
                Toast.makeText(requireContext(), "Заповніть поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun sendEmail(recipientEmail: String, textMessage: String) {
        val uriText = "mailto:$recipientEmail" +
                "?subject=" + Uri.encode("Subject") +
                "&body=" + Uri.encode(textMessage)

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(uriText)
        }
        startActivity(Intent.createChooser(emailIntent, "Send feedback"))
    }
}
