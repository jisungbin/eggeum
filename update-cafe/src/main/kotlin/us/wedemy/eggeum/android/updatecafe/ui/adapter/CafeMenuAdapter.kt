/*
 * Designed and developed by Wedemy 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/Wedemy/eggeum-android/blob/main/LICENSE
 */

package us.wedemy.eggeum.android.updatecafe.ui.adapter

import android.view.ViewGroup
import us.wedemy.eggeum.android.common.extension.layoutInflater
import us.wedemy.eggeum.android.common.ui.BaseRecyclerViewAdapter
import us.wedemy.eggeum.android.updatecafe.databinding.ItemUpdateCafeMenuBinding
import us.wedemy.eggeum.android.updatecafe.ui.adapter.viewholder.CafeMenuViewHolder
import us.wedemy.eggeum.android.updatecafe.ui.item.CafeMenuItem

class CafeMenuAdapter : BaseRecyclerViewAdapter<CafeMenuItem, ItemUpdateCafeMenuBinding>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    CafeMenuViewHolder(ItemUpdateCafeMenuBinding.inflate(parent.context.layoutInflater, parent, false))
}
