package com.example.lazycolumn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val indices = listOf(
        IndexItem("Nifty", 25000.0, 0.0, 0.0),
        IndexItem("Sensex", 80000.0, 0.0, 0.0),
    )

    private val _ticks = flow {
        while (true) {
            val updatedList = indices.map { indexItem ->
                val percentageChange = Random.nextDouble(-3.0, 3.0)
                val points = indexItem.points - (indexItem.points * (percentageChange / 100))
                return@map indexItem.copy(
                    percentageChange = percentageChange,
                    points = points,
                    pointsChange = indexItem.points - points
                )
            }
            emit(updatedList)
            delay(500)
        }
    }

    val ticks = _ticks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), indices)
}

data class IndexItem(
    val name: String,
    val points: Double,
    val pointsChange: Double,
    val percentageChange: Double
)

private const val TAG = "HomeViewModel"