package eg.esperantgada.allmoneyconverter.model

import eg.esperantgada.allmoneyconverter.data.Rates

data class ApiResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)