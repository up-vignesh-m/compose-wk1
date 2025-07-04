package com.example.lazycolumn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

val indices = listOf(
    IndexItem("Nifty", 25000.0, 0.0, 0.0),
    IndexItem("Sensex", 80000.0, 0.0, 0.0),
)

class HomeViewModel : ViewModel() {

    private val _ticks = flow {
        while (true) {
            val updatedList = indices.mapIndexed { i, indexItem ->
                val points = indexItem.points + Random.nextDouble(-10.0, 10.0)
                val pointsChange = points - indices[i].points
                val percentageChange = (pointsChange / indices[i].points) * 100.0
                return@mapIndexed indexItem.copy(
                    percentageChange = percentageChange,
                    points = points,
                    pointsChange = pointsChange
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