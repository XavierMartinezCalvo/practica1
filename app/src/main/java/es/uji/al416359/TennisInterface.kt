package es.uji.al416359

interface TennisInterface {
    fun displaySets(setA:String, setB:String)
    fun displayGames(gamesA:String, gamesB:String)
    fun displayPoints(pointsA:String, pointsB:String)
    fun displayScore(scoreA:String, scoreB:String)


    var isMaxSetsEnabled: Boolean
    var isPointsEnabled: Boolean
}