/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.domain.usecase

import javax.inject.Inject
import javax.inject.Singleton
import us.wedemy.eggeum.android.domain.model.user.UpdateUserInfoBody
import us.wedemy.eggeum.android.domain.model.user.UserInfoBody
import us.wedemy.eggeum.android.domain.repository.UserRepository
import us.wedemy.eggeum.android.domain.util.CheckNicknameExistResponseIsNull
import us.wedemy.eggeum.android.domain.util.GetUserInfoApiResponseIsNull
import us.wedemy.eggeum.android.domain.util.runSuspendCatching

@Singleton
public class GetUserInfoUseCase @Inject constructor(
  private val repository: UserRepository,
) {
  public suspend fun execute(): Result<UserInfoBody> =
    runSuspendCatching {
      repository.getUserInfo() ?: throw GetUserInfoApiResponseIsNull
    }
}

@Singleton
public class UpdateUserInfoUseCase @Inject constructor(
  private val repository: UserRepository,
) {
  public suspend fun execute(updateUserInfoBody: UpdateUserInfoBody): Result<Unit> =
    runSuspendCatching {
      repository.updateUserInfo(updateUserInfoBody)
    }
}

@Singleton
public class WithdrawUseCase @Inject constructor(
  private val repository: UserRepository,
) {
  public suspend fun execute(): Result<Unit> =
    runSuspendCatching {
      repository.withdraw()
    }
}

@Singleton
public class UpdateUserNicknameUseCase @Inject constructor(
  private val repository: UserRepository,
) {
  public suspend fun execute(nickname: String): Result<Unit> =
    runSuspendCatching {
      repository.updateUserNickname(nickname)
    }
}

@Singleton
public class CheckNicknameExistUseCase @Inject constructor(
  private val repository: UserRepository,
) {
  public suspend fun execute(nickname: String): Result<Boolean> =
    runSuspendCatching {
      repository.checkNicknameExist(nickname) ?: throw CheckNicknameExistResponseIsNull
    }
}
