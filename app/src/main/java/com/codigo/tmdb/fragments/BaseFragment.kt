package com.codigo.tmdb.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codigo.appbase.vos.ReturnResult
import com.codigo.tmdb.R

abstract class BaseFragment : Fragment() {
    abstract fun initView()
    protected val loadingDialog: Dialog by lazy {
        Dialog(requireContext()).apply {
            this.setCancelable(false)
            this.setContentView(R.layout.dialog_loading_view)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    protected fun handleError(error: ReturnResult<Any>) {
        when (error) {
            is ReturnResult.NewVersion -> {

            }
            is ReturnResult.SessionExpired -> {

            }
            is ReturnResult.NetworkErrorResult -> {
                Toast.makeText(requireContext(), "Fail to load", Toast.LENGTH_SHORT).show()
            }
            else -> {

            }
        }
    }
}