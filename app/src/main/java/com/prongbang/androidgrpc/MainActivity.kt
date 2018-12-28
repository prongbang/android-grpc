package com.prongbang.androidgrpc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.prongbang.androidgrpc.feature.greeter.di.Injector
import com.prongbang.androidgrpc.feature.greeter.ui.GreeterViewModel
import com.prongbang.androidgrpc.result.Result
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GreeterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Injector.provideGreeterViewModelFactory().let { factory ->
            viewModel = ViewModelProviders.of(this, factory).get(GreeterViewModel::class.java)
        }

        btnSend.setOnClickListener {

            onSend(etMessage.text.toString())

        }

    }

    private fun onSend(message: String) {

        etMessage.setText("")

        viewModel.sayHello(message).observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    tvResult.text = ("Sending...")
                }
                is Result.Success -> {
                    tvResult.text = it.data
                }
                is Result.Error -> {
                    tvResult.text = ("Not receive")
                }
            }
        })

    }
}
