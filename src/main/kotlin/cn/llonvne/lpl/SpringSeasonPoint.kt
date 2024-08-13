package cn.llonvne.lpl

enum class SpringSeasonPoint(val point: Int) {
    place_1(90),
    place_2(70),
    place_3(50),
    place_4(30),
    place_5(20),
    place_6(20),
    place_7(10),
    place_8(10),
    place_9(0),
    place_10(0),
    place_11_17(0);

    companion object {
        fun ofRank(rank: Int): SpringSeasonPoint {
            return when (rank) {
                1 -> place_1
                2 -> place_2
                3 -> place_3
                4 -> place_4
                5 -> place_5
                6 -> place_6
                7 -> place_7
                8 -> place_8
                9 -> place_9
                10 -> place_10
                else -> throw RuntimeException("Rank $rank not in 1..17")
            }
        }
    }
}
