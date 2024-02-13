package es.uji.al416359

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import es.uji.al416359.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TennisInterface {
    private lateinit var binding: ActivityMainBinding
    private val tennisViewModel: viewModelTenis by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            radioButton.setOnClickListener{
                tennisViewModel.onChangeMaxSets(3)
                isMaxSetsEnabled = false
            }
            radioButton2.setOnClickListener{
                tennisViewModel.onChangeMaxSets(5)
                isMaxSetsEnabled = false
            }
            ButtonReset.setOnClickListener{
                tennisViewModel.onReset()
                isMaxSetsEnabled = true
                radioButton.isChecked = false
                radioButton2.isChecked = false
            }
            ButtonPointA.setOnClickListener {
                tennisViewModel.onAPointScored()
            }
            ButtonPointB.setOnClickListener {
                tennisViewModel.onBPointScored()
            }
        }
    }

    override fun displayScore(scoreA: String, scoreB: String) = with(binding) {
        textScore1.text = scoreA
        textScore2.text = scoreB
    }

    override fun displaySets(setA: String, setB: String) = with(binding) {
        textSet1.text = setA
        textSet2.text = setB
    }

    override fun displayGames(gamesA: String, gamesB: String) = with(binding)  {
        textGame.text = gamesA
        textGame2.text = gamesB
    }

    override fun displayPoints(pointsA: String, pointsB: String) = with(binding)  {
        textPoint1.text = pointsA
        textPoint2.text = pointsB
    }

    override var isMaxSetsEnabled: Boolean
        get() = binding.radioButton.isEnabled && binding.radioButton2.isEnabled
        set(value) {
            binding.radioButton.isEnabled=value
            binding.radioButton2.isEnabled=value
        }
    override var isPointsEnabled: Boolean
        get() = binding.ButtonPointA.isEnabled && binding.ButtonPointB.isEnabled
        set(value) {
            binding.ButtonPointA.isEnabled=value
            binding.ButtonPointB.isEnabled=value
        }

    override fun onResume() {
        super.onResume()
        tennisViewModel.view=this
    }

    override fun onPause() {
        super.onPause()
        tennisViewModel.view=null
    }
}