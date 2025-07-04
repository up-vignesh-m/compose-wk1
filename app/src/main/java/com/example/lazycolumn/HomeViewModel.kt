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
                val percentageChange = Random.nextDouble(-1.0, 1.0)
                val points = indices[i].points - (indices[i].points * (percentageChange / 100))
                return@mapIndexed indexItem.copy(
                    percentageChange = percentageChange,
                    points = points,
                    pointsChange = indices[i].points - points
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