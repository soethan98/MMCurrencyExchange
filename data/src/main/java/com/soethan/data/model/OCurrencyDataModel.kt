package com.soethan.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.soethan.data.util.Bank
import com.soethan.data.source.network.model.OCurrencyRate
import java.math.BigDecimal

@Entity(tableName = "other_bank_with_rate")
class OBankWithRate(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @Embedded val oBankEntity: OBankEntity,
    @Relation(
        parentColumn = "bankType",
        entityColumn = "bankType"
    )
    val currencyRates: List<OCurrencyRate>
)

@Entity(tableName = "other_bank_entity")
data class OBankEntity(
    @PrimaryKey val bankType: Bank,
    val updateTime: Long
)

@Entity(tableName = "other_bank_rate")
data class OBankRate(
    val buy: BigDecimal,
    val sell: BigDecimal,
    val countryCode: String,
    val bankType: Bank
)