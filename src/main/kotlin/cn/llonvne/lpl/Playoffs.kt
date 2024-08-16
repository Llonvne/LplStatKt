package cn.llonvne.lpl

import cn.llonvne.lpl.PlayoffGameName.*
import cn.llonvne.lpl.PlayoffMember.*
import cn.llonvne.lpl.SummerSeasonPoint.*

enum class PlayoffGameName {
    G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12
}

data class PlayoffMatch(
    val name: PlayoffGameName,
    val blue: PlayoffMember,
    val red: PlayoffMember
) {
//    override fun toString(): String {
//        return "Match"
//    }
}

data class PlayoffMatchResult(
    val game: PlayoffMatch,
    val winner: LplTeam,
    val loser: LplTeam
) {
//    override fun toString(): String {
//        return "${game.name}: ${winner.name} > ${loser.name}"
//    }
}

sealed interface PlayoffMember {
    data class SummerSeasonOf(val summerSeasonPoint: SummerSeasonPoint) : PlayoffMember

    data class LoserOf(val playoffMatch: PlayoffMatch) : PlayoffMember

    data class WinnerOf(val playoffMatch: PlayoffMatch) : PlayoffMember
}

// SINGLE
val G1Match = PlayoffMatch(G1, SummerSeasonOf(place_8), SummerSeasonOf(place_9))
val G2Match = PlayoffMatch(G2, SummerSeasonOf(place_7), SummerSeasonOf(place_10))
val G3Match = PlayoffMatch(G3, SummerSeasonOf(place_5), WinnerOf(G1Match))
val G4Match = PlayoffMatch(G4, SummerSeasonOf(place_6), WinnerOf(G2Match))
val G5Match = PlayoffMatch(G5, SummerSeasonOf(place_4), WinnerOf(G3Match))
val G6Match = PlayoffMatch(G6, SummerSeasonOf(place_3), WinnerOf(G4Match))
val G7Match = PlayoffMatch(G7, SummerSeasonOf(place_1), WinnerOf(G5Match))
val G8Match = PlayoffMatch(G8, SummerSeasonOf(place_2), WinnerOf(G6Match))

// DOUBLE
val G9Match = PlayoffMatch(G9, WinnerOf(G7Match), WinnerOf(G8Match))
val G10Match = PlayoffMatch(G10, LoserOf(G7Match), LoserOf(G8Match))
val G11Match = PlayoffMatch(G11, LoserOf(G9Match), WinnerOf(G10Match))
val G12Match = PlayoffMatch(G12, WinnerOf(G9Match), WinnerOf(G11Match))

fun PlayoffMatch.nextOrNull(): PlayoffMatch? {
    return when (this.name) {
        G1 -> G2Match
        G2 -> G3Match
        G3 -> G4Match
        G4 -> G5Match
        G5 -> G6Match
        G6 -> G7Match
        G7 -> G8Match
        G8 -> G9Match
        G9 -> G10Match
        G10 -> G11Match
        G11 -> G12Match
        G12 -> null
    }
}


val playoffs: Map<PlayoffGameName, PlayoffMatch> = listOf(
    G1Match, G2Match, G3Match, G4Match, G5Match, G6Match, G7Match, G8Match, G9Match, G10Match, G11Match, G12Match
).associateBy { it.name }