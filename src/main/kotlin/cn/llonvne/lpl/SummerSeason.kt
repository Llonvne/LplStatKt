package cn.llonvne.lpl

data class SummerSeason(
    val team: LplTeam,
    val point: SummerSeasonPoint
)

fun Map<PlayoffGameName, PlayoffMatchResult>.mapToSummerSeasonList(): List<SummerSeason> {
    return ranked().mapIndexed { index, team ->
        SummerSeason(team, SummerSeasonPoint.ofRank(index + 1))
    }
}