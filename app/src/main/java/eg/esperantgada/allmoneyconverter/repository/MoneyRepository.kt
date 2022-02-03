package eg.esperantgada.allmoneyconverter.repository

import eg.esperantgada.allmoneyconverter.model.ApiResponse
import eg.esperantgada.allmoneyconverter.util.HandleResponse

interface MoneyRepository {

    suspend fun getRateFromApi(base : String) : HandleResponse<ApiResponse>
}