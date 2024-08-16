package cn.llonvne.lpl

data class AllSeason(
    val team: LplTeam,
    val springSeason: SpringSeason?,
    val summerSeason: SummerSeason?
) : Comparable<AllSeason> {

    val springScore = springSeason?.point?.point ?: 0
    val summerScore = summerSeason?.point?.point ?: 0
    val totalScore = springScore + summerScore

//    override fun toString(): String {
//        return "${team.name} | $totalScore"
//    }

    override fun compareTo(other: AllSeason): Int {
        if (totalScore > other.totalScore) {
            return 1
        }
        if (totalScore < other.totalScore) {
            return -1
        }
        return if (summerScore > other.summerScore) {
            1
        } else {
            -1
        }
    }
}
