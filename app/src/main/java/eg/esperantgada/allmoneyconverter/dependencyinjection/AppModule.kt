package eg.esperantgada.allmoneyconverter.dependencyinjection

import eg.esperantgada.allmoneyconverter.networkapi.CurrencyApi
import eg.esperantgada.allmoneyconverter.repository.MoneyRepository
import eg.esperantgada.allmoneyconverter.repository.DefaultMoneyRepository
import eg.esperantgada.allmoneyconverter.util.Constant.Companion.BASE_URL
import eg.esperantgada.allmoneyconverter.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Dependency injection
 * This class contains the retrofit object that will be injected in the app
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrency() : CurrencyApi = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)


    @Singleton
    @Provides
    fun provideCurrencyRepository(api : CurrencyApi) :
            MoneyRepository = DefaultMoneyRepository(api)

    @Singleton
    @Provides
    fun dispatcherProvider() : DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}