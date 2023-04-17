package com.example.movixcomposeapp.core.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version BaseNetworkResponse, v 0.1 4/16/2023 5:44 AM by Rizky Alfikri Rachmat
 */
open class BaseNetworkResponse(
    @SerializedName("status_code")
    var statusCode: Long? = null,
    @SerializedName("status_message")
    var statusMessage: String? = null,
    @SerializedName("success")
    var success: Boolean? = null
)