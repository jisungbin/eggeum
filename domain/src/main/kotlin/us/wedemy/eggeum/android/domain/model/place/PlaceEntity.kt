/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.domain.model.place

public data class PlaceEntity(
  val address1: String?,
  val address2: String?,
  val id: Int,
  val image: ImageEntity,
  val info: InfoEntity,
  val latitude: Double?,
  val longitude: Double?,
  val menu: MenuEntity,
  val name: String,
  val type: String?,
)
