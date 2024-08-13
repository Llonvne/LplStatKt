package cn.llonvne.lpl.lpl2024

import cn.llonvne.lpl.LplTeam.*
import cn.llonvne.lpl.SpringSeason
import cn.llonvne.lpl.SpringSeasonPoint

val lpl2024SpringPlayoffs: List<SpringSeason> = listOf(
    BLG,
    TES,
    JDG,
    NIP,
    FPX,
    WBG,
    WE,
    LNG,
    OMG,
    IG
).mapIndexed { index, team ->
    val rank = index + 1
    SpringSeason(team, SpringSeasonPoint.ofRank(rank))
}