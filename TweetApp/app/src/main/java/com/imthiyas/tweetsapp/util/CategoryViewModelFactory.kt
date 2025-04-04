import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imthiyas.tweetsapp.repository.TweetRepository
import com.imthiyas.tweetsapp.viewmodel.CategoryViewModel

class CategoryViewModelFactory(private val repository: TweetRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
