package cn.llonvne.lpl

data class AllSeason(
    val team: LplTeam,
    val springSeason: SpringSeason?,
    val summerSeason: SummerSeason?
) : Comparable<AllSeason> {
    fun totalScore(): Int {
        return (springSeason?.point?.point ?: 0) + (summerSeason?.point?.point ?: 0)
    }

    override fun toString(): String {
        return "${team.name} at ${totalScore()}"
    }

    override fun compareTo(other: AllSeason): Int {
        if (totalScore() > other.totalScore()) {
            return 1
        }
        if (totalScore() < other.totalScore()) {
            return -1
        }

        return if ((summerSeason?.point?.point ?: 0) > (other.springSeason?.point?.point ?: 0)) {
            1
        } else {
            -1
        }
    }
}
