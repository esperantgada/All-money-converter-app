package eg.esperantgada.allmoneyconverter.networkapi

import eg.esperantgada.allmoneyconverter.model.ApiResponse
import eg.esperantgada.allmoneyconverter.util.Constant.Companion.BASE
import eg.esperantgada.allmoneyconverter.util.Constant.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET(ENDPOINT)
    suspend fun getCurrencyRate(
        @Query(BASE)
        base : String) : Response<ApiResponse>
}