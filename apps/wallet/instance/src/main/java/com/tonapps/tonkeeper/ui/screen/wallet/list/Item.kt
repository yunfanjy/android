package com.tonapps.tonkeeper.ui.screen.wallet.list

import android.net.Uri
import android.util.Log
import androidx.collection.ArrayMap
import com.tonapps.uikit.list.BaseListItem
import com.tonapps.uikit.list.ListCell
import com.tonapps.wallet.api.entity.TokenEntity
import com.tonapps.wallet.data.account.WalletType
import com.tonapps.wallet.data.push.entities.AppPushEntity
import com.tonapps.wallet.data.tonconnect.entities.DAppEntity

sealed class Item(type: Int): BaseListItem(type) {

    companion object {
        const val TYPE_BALANCE = 0
        const val TYPE_ACTIONS = 1
        const val TYPE_TOKEN = 2
        const val TYPE_SPACE = 3
        const val TYPE_SKELETON = 4
        const val TYPE_PUSH = 5
    }

    enum class Status {
        Default,
        Updating,
        NoInternet,
        SendingTransaction,
        TransactionConfirmed,
        Unknown,
    }

    data class Balance(
        val balance: CharSequence,
        val address: String,
        val walletType: WalletType,
        val status: Status,
        val hiddenBalance: Boolean
    ): Item(TYPE_BALANCE)

    data class Actions(
        val address: String,
        val token: TokenEntity,
        val walletType: WalletType,
        val swapUri: Uri,
    ): Item(TYPE_ACTIONS)

    data class Token(
        val position: ListCell.Position,
        val iconUri: Uri,
        val address: String,
        val symbol: String,
        val name: String,
        val balance: Float,
        val balanceFormat: CharSequence,
        val fiat: Float,
        val fiatFormat: CharSequence,
        val rate: CharSequence,
        val rateDiff24h: String,
        val verified: Boolean,
        val testnet: Boolean,
        val hiddenBalance: Boolean
    ): Item(TYPE_TOKEN)

    data object Space: Item(TYPE_SPACE)

    data object Skeleton: Item(TYPE_SKELETON)

    data class Push(
        val events: List<AppPushEntity>,
        val apps: List<DAppEntity>
    ): Item(TYPE_PUSH) {

        val text = events.first().message

        val iconUris: List<Uri> by lazy {
            apps.map { Uri.parse(it.manifest.iconUrl) }
        }
    }
}