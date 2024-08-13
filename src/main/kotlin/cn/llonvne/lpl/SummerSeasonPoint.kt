package cn.llonvne.lpl


enum class SummerSeasonPoint(val point: Int) {
    place_1(200),
    place_2(110),
    place_3(80),
    place_4(60),
    place_5(40),
    place_6(40),
    place_7(10),
    place_8(10),
    place_9(0),
    place_10(0),
    place_11_17(0);

    companion object {
        fun ofRank(rank: Int): SummerSeasonPoint {
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
                in 11..17 -> place_11_17
                else -> throw RuntimeException("Rank $rank not in 1..17")
            }
        }
    }
}