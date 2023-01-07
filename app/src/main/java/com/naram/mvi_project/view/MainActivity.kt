package com.naram.mvi_project.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.naram.mvi_project.BR
import com.naram.mvi_project.MovieSideEffect
import com.naram.mvi_project.MovieState
import com.naram.mvi_project.R
import com.naram.mvi_project.databinding.ActivityMainBinding
import com.naram.mvi_project.iface.IView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IView<MovieState, MovieSideEffect> {
    companion object {
        private val TAG = MainActivity::class.java.name
    }

    private val mainVm: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setVariable(BR.mainVm, mainVm)

        initObserver()
    }

    private fun initObserver() {
        mainVm.state.observe(this) {
            render(it)
        }

        mainVm.navigation.onEach {
            navigate(it)
        }
    }

    override fun render(state: MovieState) {
        with(state) {
            Log.d(TAG, "movie: $movies")

            if (movies.isNotEmpty()) {
                binding.tvOutputResult.text = movies[0].toString()
            }

            errorMessage?.let {
                Toast.makeText(this@MainActivity, "$errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun navigate(from: String) {
        when(from) {
            "main" -> {
//                startActivity(Intent(this, ))
            }
        }
    }
}