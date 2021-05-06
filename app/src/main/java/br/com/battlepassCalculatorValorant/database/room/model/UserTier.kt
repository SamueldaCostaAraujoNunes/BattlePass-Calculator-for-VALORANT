package br.com.battlepassCalculatorValorant.database.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserTier(
    val tierCurrent: Int,
    var tierExpMissing: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    constructor() : this(15, 0, -1)
}

