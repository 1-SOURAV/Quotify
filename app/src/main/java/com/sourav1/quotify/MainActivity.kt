package com.sourav1.quotify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteTxt)

    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)

    private val prevBtn: ImageView
        get() = findViewById(R.id.prevBtn)

    private val nextBtn: ImageView
        get() = findViewById(R.id.nextBtn)

    private val shareBtn: ImageView
        get() = findViewById(R.id.shareBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]

        setQuote(mainViewModel.getQuote())

        prevBtn.setOnClickListener {
            setQuote(mainViewModel.prevQuote())
        }

        nextBtn.setOnClickListener {
            setQuote(mainViewModel.nextQuote())
        }

        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
            startActivity(intent)
        }

    }

    fun setQuote(quote: Quotes) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }
}