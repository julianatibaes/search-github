package com.tibaes.search.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tibaes.search.databinding.ItemDetailBinding
import com.tibaes.search.domain.entity.Item

class ItemGithubBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val ITEM = "item_detail"

        operator fun invoke(
            item: Item? = null
        ) = ItemGithubBottomSheet().apply {
            arguments = Bundle().apply {
                putSerializable(ITEM, item)
            }
        }
    }

    private var dismissListener: (() -> (Unit))? = null

    fun setDismissListener(listener: () -> (Unit)) {
        dismissListener = listener
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding =  ItemDetailBinding.inflate( inflater, container, false)

        dialog?.setCancelable(true)
        dialog?.setCanceledOnTouchOutside(true)

        renderData(binding)

        return binding.root
    }

    private fun renderData(binding: ItemDetailBinding) {
        val item = arguments?.getSerializable(ITEM) as Item
        binding.name.text = item.name
        binding.description.text = item.description
        binding.fullName.text = item.fullName
        binding.url.text = item.url
    }
}