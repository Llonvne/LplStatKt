package cn.llonvne.lpl.lpl2024

import cn.llonvne.lpl.*
import cn.llonvne.lpl.LplTeam.*
import cn.llonvne.lpl.PlayoffGameName.*
import cn.llonvne.lpl.mapToSummerSeasonList

fun main() {
    val total = calc(G1Match, mapOf(), lpl2024SummerRegular)
        .filterResult(G1, NIP, LGD)
        .filterResult(G2, FPX, TT)
        .filterResult(G3, NIP, JDG)
        .filterResult(G4, AL, FPX)
        .filterResult(G6, AL, TES)
        .apply {
            forEach { println(it.values) }
        }
        .map { it.mapToSummerSeasonList() }
        .map {
            calcAllSeason(lpl2024SpringPlayoffs, it).sortedDescending().subList(0, 6)
        }

    val aLin = total.filter { it.map { it.team }.toSet().contains(AL) }

    println("Al in Regional Qualifier probability is ${aLin.size}/${total.size}")

}