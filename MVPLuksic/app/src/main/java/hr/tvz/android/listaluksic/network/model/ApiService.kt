package hr.tvz.android.listaluksic.network.model

import hr.tvz.android.listaluksic.ItemDTO
import retrofit2.http.GET

interface ApiService {

    @GET("86f8a3bc56e307136292")
    suspend fun getAllItems() : ArrayList<ItemDTO>

//    @POST("api/driver")
    //   suspend fun postDriver(@Body driverDto : DriverDTOPost) : Response<Unit>
}