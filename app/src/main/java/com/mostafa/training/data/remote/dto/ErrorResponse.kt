package com.mostafa.training.data.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
open class ErrorResponse(
    val data: String,
    val message: String,
    val status: Boolean
)