import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _itemList = MutableStateFlow<List<QiitaItem>>(listOf())
    val itemList: StateFlow<List<QiitaItem>> get() = _itemList

    init {
        viewModelScope.launch {
            QiitaComponent().getItems().collect { item ->
                _itemList.update { _ -> item }
            }
        }
    }
}