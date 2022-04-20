package com.example.retrofitpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitpractice.repository.Repository
import com.example.retrofitpractice.ui.theme.RetrofitPracticeTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val repository = Repository()
                    val viewModelFactory = MainViewModelFactory(repository = repository)
                    viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                    viewModel.getPost()
                    viewModel.myResponse.observe(this, Observer { response ->
                        if(response.isSuccessful) {
                            Log.d("Response", response.body()?.userId.toString())
                            Log.d("Response", response.body()?.id.toString())
                            Log.d("Response", response.body()?.title!!)
                            Log.d("Response", response.body()?.body!!)


                        } else {
                            Log.d("Response", response.errorBody().toString())
                        }
                    })
                }
            }
        }
    }
}

