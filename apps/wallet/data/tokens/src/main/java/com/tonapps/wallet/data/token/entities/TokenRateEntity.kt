package com.tonapps.wallet.data.token.entities

import com.tonapps.wallet.data.core.WalletCurrency

data class TokenRateEntity(
    val currency: WalletCurrency,
    val fiat: Float,
    val rate: Float,
    val rateDiff24h: String
)