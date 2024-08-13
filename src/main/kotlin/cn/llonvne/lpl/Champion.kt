package cn.llonvne.lpl

fun calcAllSeason(
    springSeason: List<SpringSeason>,
    summerSeason: List<SummerSeason>
): List<AllSeason> {
    val teams = (springSeason.map { it.team } + summerSeason.map { it.team }).toSet()
    return teams.map { team ->
        val spring = springSeason.find { it.team == team }
        val summer = summerSeason.find { it.team == team }
        AllSeason(team, spring, summer)
    }
}