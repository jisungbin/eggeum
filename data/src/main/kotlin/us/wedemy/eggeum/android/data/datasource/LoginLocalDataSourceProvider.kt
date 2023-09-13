/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.data.datasource

import javax.inject.Inject
import us.wedemy.eggeum.android.data.datastore.TokenDataStoreProvider

public class LoginLocalDataSourceProvider @Inject constructor(
  // private val dataStore: TokenDataStore
  private val dataStore: TokenDataStoreProvider,
): LoginLocalDataSource {
  override suspend fun setAccessToken(accessToken: String) {
    dataStore.setAccessToken(accessToken)
  }

  override suspend fun setRefreshToken(refreshToken: String) {
    dataStore.setRefreshToken(refreshToken)
  }

  override suspend fun getAccessToken(): String {
    return dataStore.getAccessToken()
  }

  override suspend fun getRefreshToken(): String {
    return dataStore.getRefreshToken()
  }

  override suspend fun clearLoginToken() {
    dataStore.clearDataStore()
  }
}
