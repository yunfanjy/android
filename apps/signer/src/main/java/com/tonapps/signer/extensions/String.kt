package com.tonapps.signer.extensions

import android.net.Uri

val String.short8: String
    get() {
        if (length < 16) return this
        return substring(0, 8) + "…" + substring(length - 8, length)
    }

val String.short4: String
    get() {
        if (length < 8) return this
        return substring(0, 4) + "…" + substring(length - 4, length)
    }

val String.uriOrNull: Uri?
    get() {
        if (this.isEmpty()) {
            return null
        }

        return try {
            Uri.parse(this)
        } catch (e: Exception) {
            null
        }
    }