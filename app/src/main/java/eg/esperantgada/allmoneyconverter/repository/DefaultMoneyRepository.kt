package eg.esperantgada.allmoneyconverter.repository

import eg.esperantgada.allmoneyconverter.model.ApiResponse
import eg.esperantgada.allmoneyconverter.networkapi.CurrencyApi
import eg.esperantgada.allmoneyconverter.util.HandleResponse
import java.lang.Exception
import javax.inject.Inject

/**
 * We inject an object of type [CurrencyApi] in this repository class
 * This inherites from [MoneyRepository] and implements [getRateFromApi] method
 * [result] variable contains all the content we got from the API and stored in [response]
 * [response.message] is the API error message if something went wrong
 */
class DefaultMoneyRepository @Inject constructor(
    private val currencyApi: CurrencyApi
) : MoneyRepository {
    override suspend fun getRateFromApi(base: String): HandleResponse<ApiResponse> {

        return try {
            val response = currencyApi.getCurrencyRate(base)
            val result = response.body()
            if (response.isSuccessful && result != null){
                HandleResponse.Success(result)
            }else{
                HandleResponse.Error(response.message())
            }
        }catch (e : Exception){
            HandleResponse.Error(e.message ?: "Ouf! An error occurred")
        }

    }
}