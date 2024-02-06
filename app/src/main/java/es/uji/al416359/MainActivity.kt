package es.uji.al416359

import TennisInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.uji.al416359.databinding.ActivityMainBinding
import androidx.activity.ViewModel
import viewModelTenis

class MainActivity : AppCompatActivity(), TennisInterface {
    private lateinit var binding: ActivityMainBinding
    private val tennisViewModel: viewModelTenis by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}