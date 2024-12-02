package com.icc2024.recargapp.ui.recharge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.icc2024.recargapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionSuccessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionSuccessFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_success, container, false)
    }


}