package cn.llonvne.lpl

import cn.llonvne.lpl.PlayoffGameName.*
import cn.llonvne.lpl.PlayoffMember.*

fun resolveSummerPlayoffTeamFromPlayoffMember(
    playoffMember: PlayoffMember,
    registry: Map<PlayoffGameName, PlayoffMatchResult>,
    summerRanks: List<LplTeam>
): LplTeam {
    return when (playoffMember) {
        is LoserOf -> registry[playoffMember.playoffMatch.name]?.loser ?: throw RuntimeException()
        is WinnerOf -> registry[playoffMember.playoffMatch.name]?.winner ?: throw RuntimeException()
        is SummerSeasonOf -> summerRanks[playoffMember.summerSeasonPoint.ordinal]
    }
}

fun calc(
    match: PlayoffMatch,
    registry: Map<PlayoffGameName, PlayoffMatchResult>,
    summerRanks: List<LplTeam>
): List<Map<PlayoffGameName, PlayoffMatchResult>> {
    val blue = resolveSummerPlayoffTeamFromPlayoffMember(match.blue, registry, summerRanks)
    val red = resolveSummerPlayoffTeamFromPlayoffMember(match.red, registry, summerRanks)

    val blueWin = PlayoffMatchResult(match, blue, red)
    val blueWinRegistry = registry.toMutableMap().apply { put(match.name, blueWin) }

    val redWin = PlayoffMatchResult(match, red, blue)
    val redWinRegistry = registry.toMutableMap().apply { put(match.name, redWin) }


    val nextMatch = match.nextOrNull() ?: return listOf(blueWinRegistry, redWinRegistry)

    val blueWinCalc = calc(nextMatch, blueWinRegistry, summerRanks)
    val redWinCalc = calc(nextMatch, redWinRegistry, summerRanks)

    return blueWinCalc + redWinCalc
}

fun List<Map<PlayoffGameName, PlayoffMatchResult>>.filterResult(
    gameName: PlayoffGameName, winner: LplTeam?, loser: LplTeam?
) = filter { registry ->
    val match = registry[gameName] ?: throw RuntimeException()
    if (winner != null && match.winner != winner) {
        return@filter false
    }
    if (loser != null && match.loser != loser) {
        return@filter false
    }
    return@filter true
}


fun Map<PlayoffGameName, PlayoffMatchResult>.ranked(): List<LplTeam> {
    return listOf(
        getValue(G12).winner,
        getValue(G12).loser,
        getValue(G11).loser,
        getValue(G10).loser,
        getValue(G5).loser,
        getValue(G6).loser,
        getValue(G3).loser,
        getValue(G4).loser,
        getValue(G1).loser,
        getValue(G2).loser,
    )
}

