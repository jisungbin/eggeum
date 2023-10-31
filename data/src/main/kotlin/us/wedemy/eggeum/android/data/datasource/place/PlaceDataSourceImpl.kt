/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.data.datasource.place

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import us.wedemy.eggeum.android.data.model.place.PlaceResponse
import us.wedemy.eggeum.android.data.model.place.UpsertPlaceRequest
import us.wedemy.eggeum.android.data.paging.PlacePagingSource
import us.wedemy.eggeum.android.data.service.PlaceService
import us.wedemy.eggeum.android.data.util.Constants
import us.wedemy.eggeum.android.data.util.safeRequest

public class PlaceDataSourceImpl @Inject constructor(
  private val service: PlaceService,
) : PlaceDataSource {
  override suspend fun getPlace(placeId: Int): PlaceResponse? {
    return safeRequest {
      service.getPlace(placeId)
    }
  }

  override suspend fun getPlaceList(
    distance: Int?,
    endDate: String?,
    latitude: Int?,
    longitude: Int?,
    page: Int?,
    search: String?,
    size: Int?,
    sort: String?,
    startDate: String?,
    type: String?,
  ): Flow<PagingData<PlaceResponse>> {
    val pagingSourceFactory = { PlacePagingSource(service) }

    return Pager(
      config = PagingConfig(
        pageSize = Constants.PAGING_SIZE,
        enablePlaceholders = false,
        maxSize = Constants.PAGING_SIZE * 3,
      ),
      pagingSourceFactory = pagingSourceFactory,
    ).flow
  }

  override suspend fun upsertPlace(upsertPlaceRequest: UpsertPlaceRequest) {
    safeRequest {
      service.upsertPlace(upsertPlaceRequest)
    }
  }
}
