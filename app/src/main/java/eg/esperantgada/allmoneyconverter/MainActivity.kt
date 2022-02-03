package eg.esperantgada.allmoneyconverter

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.striyank.allmoneyconverter.databinding.ActivityMainBinding
import eg.esperantgada.allmoneyconverter.viewmodel.MoneyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * We use [AndroidEntryPoint] annotation because of the [viewModel] we injected in the activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : MoneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Call [convert] method from the viewModel class and pass in user's options
         */
        binding.convertButton.setOnClickListener {
            viewModel.convert(
                binding.userInput.text.toString(),
                binding.fromSpinner.selectedItem.toString(),
                binding.toSpinner.selectedItem.toString()
            )
        }

        /**
         * Handle responses states
         */
        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when(event){
                    is MoneyViewModel.CurrencyEvent.Success ->{
                        binding.progressBar.isVisible = false
                        binding.resultTextView.setTextColor(Color.BLACK)
                        binding.resultTextView.text = event.resultMessage
                    }

                    is MoneyViewModel.CurrencyEvent.Failure ->{
                        binding.progressBar.isVisible = false
                        binding.resultTextView.setTextColor(Color.RED)
                        binding.resultTextView.text = event.errorMessage
                    }

                    is MoneyViewModel.CurrencyEvent.Loading ->{
                        binding.progressBar.isVisible = true
                    }

                    else -> Unit
                }
            }
        }
    }
}