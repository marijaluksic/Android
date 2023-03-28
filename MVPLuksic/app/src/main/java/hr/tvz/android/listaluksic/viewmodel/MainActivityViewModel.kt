package hr.tvz.android.listaluksic.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.tvz.android.listaluksic.ItemDTO
import hr.tvz.android.listaluksic.model.BazaPodataka
import hr.tvz.android.listaluksic.model.ToDo
import hr.tvz.android.listaluksic.network.Network
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    val items = MutableLiveData<ArrayList<ToDo>>()
    val itemsFromApi = MutableLiveData<ArrayList<ItemDTO>>()

    fun getAllItems(context: Context){
        viewModelScope.launch {
            items.value = BazaPodataka.getDatabase(context)?.todoDao()?.getAllItems() as ArrayList<ToDo>
        }
    }

    fun deleteAllItems(context: Context){
        viewModelScope.launch {
            BazaPodataka.getDatabase(context)?.todoDao()?.deleteAllItems()
        }
    }

    fun insertNewItem(context: Context, driver : ToDo){
        viewModelScope.launch {
            BazaPodataka.getDatabase(context)?.todoDao()?.insertNewItem(driver)
        }
    }

    /*   fun postDriver(driver : DriverDTOPost){
           viewModelScope.launch {
               Network().getService().postDriver(driver)
           }
       }*/

    fun getItemsFromAPI(){
        viewModelScope.launch {
            itemsFromApi.value = Network().getService().getAllItems()
        }
    }
}