/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.domain.usecase

import androidx.paging.PagingData
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import us.wedemy.eggeum.android.domain.model.place.PlaceEntity
import us.wedemy.eggeum.android.domain.model.place.UpsertPlaceEntity
import us.wedemy.eggeum.android.domain.repository.PlaceRepository
import us.wedemy.eggeum.android.domain.util.PlaceApiResponseIsNull
import us.wedemy.eggeum.android.domain.util.runSuspendCatching

@Singleton
public class GetPlaceUseCase @Inject constructor(
  private val repository: PlaceRepository,
) {
  public suspend operator fun invoke(placeId: Int): Result<PlaceEntity> =
    runSuspendCatching {
      repository.getPlace(placeId) ?: throw PlaceApiResponseIsNull
    }
}

@Singleton
public class GetPlaceListUseCase @Inject constructor(
  private val repository: PlaceRepository,
) {
  public operator fun invoke(type: String? = null): Flow<PagingData<PlaceEntity>> {
    return repository.getPlaceList()
  }
}

@Singleton
public class UpsertPlaceUseCase @Inject constructor(
  private val repository: PlaceRepository,
) {
  public suspend operator fun invoke(upsertPlaceEntity: UpsertPlaceEntity): Result<Unit> =
    runSuspendCatching {
      repository.upsertPlace(upsertPlaceEntity)
    }
}

@Singleton
public class InsertRecentSearchPlaceUseCase @Inject constructor(
  private val repository: PlaceRepository,
) {
  public suspend operator fun invoke(placeEntity: PlaceEntity) {
    repository.insertRecentSearchPlace(placeEntity)
  }
}

@Singleton
public class DeleteRecentSearchPlaceUseCase @Inject constructor(
  private val repository: PlaceRepository,
) {
  public suspend operator fun invoke(placeEntity: PlaceEntity) {
    repository.deleteRecentSearchPlace(placeEntity)
  }
}

@Singleton
public class GetRecentSearchPlaceListUseCase @Inject constructor(
  private val repository: PlaceRepository,
) {
  public operator fun invoke(name: String? = null): Flow<PagingData<PlaceEntity>> {
    return repository.getRecentSearchPlaceList(name)
  }
}
