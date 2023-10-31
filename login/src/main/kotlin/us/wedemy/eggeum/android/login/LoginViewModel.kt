/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import us.wedemy.eggeum.android.domain.model.login.LoginRequestEntity
import us.wedemy.eggeum.android.domain.usecase.LoginUseCase
import us.wedemy.eggeum.android.domain.usecase.SetAccessTokenUseCase
import us.wedemy.eggeum.android.domain.usecase.SetRefreshTokenUseCase
import us.wedemy.eggeum.android.domain.util.LoginApiResponseNotFound

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase,
  private val setAccessTokenUseCase: SetAccessTokenUseCase,
  private val setRefreshTokenUseCase: SetRefreshTokenUseCase,
) : ViewModel() {

  private val _navigateToMainEvent = MutableSharedFlow<Unit>(replay = 1)
  val navigateToMainEvent: SharedFlow<Unit> = _navigateToMainEvent.asSharedFlow()

  private val _navigateToOnBoardingEvent = MutableSharedFlow<Unit>(replay = 1)
  val navigateToOnBoardingEvent: SharedFlow<Unit> = _navigateToOnBoardingEvent.asSharedFlow()

  private val _showToastEvent = MutableSharedFlow<String>()
  val showToastEvent: SharedFlow<String> = _showToastEvent.asSharedFlow()

  fun login(idToken: String) {
    viewModelScope.launch {
      val result = loginUseCase(LoginRequestEntity(idToken = idToken))
      when {
        result.isSuccess && result.getOrNull() != null -> {
          val loginBody = result.getOrNull()
          setAccessTokenUseCase(loginBody!!.accessToken)
          setRefreshTokenUseCase(loginBody.refreshToken)
          _navigateToMainEvent.emit(Unit)
        }
        result.isSuccess && result.getOrNull() == null -> {
          Timber.e("Request succeeded but data validation failed.")
        }
        result.isFailure -> {
          val exception = result.exceptionOrNull()
          Timber.d(exception)
          if (exception == LoginApiResponseNotFound) {
            _navigateToOnBoardingEvent.emit(Unit)
          } else {
            _showToastEvent.emit(exception?.message ?: "Unknown Error Occured")
          }
        }
      }
    }
  }
}
