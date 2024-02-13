package es.uji.al416359

enum class ScoreType {
    Normal, Deuce, Advantage, AdvantageOther
}

internal class TennisScore {
    var type: ScoreType = ScoreType.Normal
        private set
    var points: Int = 0
        private set
    var games: Int = 0
        private set
    var sets: Int = 0
        private set
    private val prevSets = ArrayList<Int>()
    val previousSets: List<Int> = prevSets

    fun reset() {
        points = 0
        sets = 0
        games = 0
        prevSets.clear()
    }

    fun addPoint(other: TennisScore) {
        when(type) {
            ScoreType.Normal -> points = when (points) {
                0 -> 15
                15 -> 30
                30 -> {
                    if (other.points == 40) {
                        type = ScoreType.Deuce
                        other.type = ScoreType.Deuce
                    }
                    40
                }
                40 -> {
                    addGame(other)
                    0
                }
                else -> throw Exception("Impossible points: $points")
            }
            ScoreType.Deuce -> {
                type = ScoreType.Advantage
                other.type = ScoreType.AdvantageOther
            }
            ScoreType.Advantage -> addGame(other)
            ScoreType.AdvantageOther -> {
                type = ScoreType.Deuce
                other.type = ScoreType.Deuce
            }
        }
    }

    private fun clearPoints() {
        type = ScoreType.Normal
        points = 0
    }

    private fun clearGames() {
        clearPoints()
        prevSets.add(games)
        games = 0
    }

    private fun addGame(other: TennisScore) {
        clearPoints()
        other.clearPoints()
        games++
        if (games == 6)
            addSet(other)
    }

    private fun addSet(other: TennisScore) {
        clearGames()
        other.clearGames()
        sets++
    }

}

