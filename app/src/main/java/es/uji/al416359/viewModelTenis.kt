package es.uji.al416359

import androidx.lifecycle.ViewModel
class viewModelTenis: ViewModel() {
    var view: TennisInterface? = null
        set(value){
            field = value
            beginGame()
        }
    init{
        beginGame()
    }
    private var scoreA = TennisScore()
    private var scoreB = TennisScore()

    private val isMatchEnded get() = scoreA.sets > maxSets / 2 || scoreB.sets > maxSets / 2
    private var atGameBeginning = true

    private var maxSets = 3
        set(value){
            field = value
            beginGame()
        }
    fun beginGame() = view?.apply {
        displayPoints(pointsA = " ", pointsB = " ")
        displayGames(gamesA = " ", gamesB = " ")
        displayScore(scoreA = " ", scoreB = " ")
        displaySets(setA = " ", setB = " ")
    }

    fun gameStart(numSets: Int) = view?.apply {
        displayPoints(pointsA = "0", pointsB = "0")
        displayGames(gamesA = "0", gamesB = "0")
        displayScore(scoreA = "0", scoreB = "0")
        if (numSets == 3){
            displaySets(setA = "_ _ _", setB = "_ _ _")
        } else if (numSets == 5){
            displaySets(setA = "_ _ _ _ _", setB = "_ _ _ _ _")
        }
    }

    fun onChangeMaxSets(numSets: Int){
        if(atGameBeginning){
            maxSets = numSets
            gameStart(maxSets)
            reset()
        }
    }
    private fun reset(){
        scoreA.reset()
        scoreB.reset()
        atGameBeginning = true
    }

    fun onReset(){
        beginGame()
        reset()
    }

    fun onAPointScored(){
        if(isMatchEnded) return
        atGameBeginning = false
        scoreA.addPoint(scoreB)
        upDateView()
    }

    fun onBPointScored(){
        if(isMatchEnded) return
        atGameBeginning = false
        scoreB.addPoint(scoreA)
        upDateView()
    }

    private fun upDateView() = view?.apply {
        displayGames(scoreA.games.toString(), scoreB.games.toString())
        val pointsA = pointsToString(scoreA.points, scoreA.type)
        val pointsB = pointsToString(scoreB.points, scoreB.type)

        displayPoints(pointsA, pointsB)
        displaySets(scoreA.sets.toString(), scoreB.sets.toString())

        isPointsEnabled = isMatchEnded
        isMaxSetsEnabled = atGameBeginning
    }

    private fun pointsToString(points: Int, type: ScoreType): String{
        return when (type){
            ScoreType.Normal -> when (points){
                0 -> "00"
                else -> points.toString()
            }
            ScoreType.Deuce -> "40"
            ScoreType.Advantage -> "Ad"
            ScoreType.AdvantageOther -> "__"
        }
    }
}