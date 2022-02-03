package eg.esperantgada.allmoneyconverter.util

/**
 * This class will be inherited only by those two classes it contains
 * It's responsible for handling the response gotten from the Api.
 * It will know if the response is gotten successfully or with error
 */
sealed class HandleResponse<T>(val data : T?, val message : String?){

    class Success<T>(data : T) : HandleResponse<T>(data, null)
    class Error<T>(message: String) : HandleResponse<T>(null, message)
}
