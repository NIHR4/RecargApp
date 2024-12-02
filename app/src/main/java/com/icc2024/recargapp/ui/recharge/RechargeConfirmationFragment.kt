package com.icc2024.recargapp.ui.recharge
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.icc2024.recargapp.R
import com.icc2024.recargapp.contract.RechargeConfirmationContract
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.data.model.RechargeResponse
import com.icc2024.recargapp.presenter.RechargeConfirmationPresenter
import com.icc2024.recargapp.ui.recharge.RechargeFragment.Callbacks
import com.icc2024.recargapp.util.formatPhoneNumber

private const val ARG_TRANSACTION_REQ = "param_transaction_request"

/**
 * A simple [Fragment] subclass.
 * Use the [RechargeConfirmationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RechargeConfirmationFragment : Fragment(), RechargeConfirmationContract.View {

    interface Callbacks{
        fun changeToSuccessScreen(response: RechargeResponse)
        fun changeToErrorScreen()
    }


    private var presenter : RechargeConfirmationContract.Presenter? = null
    private var callback : Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val transactionRequest = it.getParcelable(ARG_TRANSACTION_REQ) as RechargeRequestDto?
            presenter = RechargeConfirmationPresenter(this, requireContext(), transactionRequest!!)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recharge_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assert(presenter != null, { "Presenter must not be null at thsis stage" })
        //set button listener
        var confirmBtn = activity?.findViewById<Button>(R.id.button_confirmar_recarga)
        confirmBtn?.setOnClickListener {
            Log.v("RechargeConfirmationFragment.onViewCreated", "confirm button clicked")
            presenter?.performRecharge()
        }


        presenter?.initializeUi()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    companion object {
        @JvmStatic
        fun newInstance(req : RechargeRequestDto) =
            RechargeConfirmationFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_TRANSACTION_REQ, req)
                }
            }
    }

    override fun updateNumberLabel(value: String) {
        var textView = activity?.findViewById<TextView>(R.id.textView_numero)
        textView?.text = formatPhoneNumber(value)
    }

    override fun updateRechargeAmountLabel(value: String) {
        var textView = activity?.findViewById<TextView>(R.id.textView_importe)
        textView?.text = "$${value}.00"
    }

    override fun changeToSuccessScreen(rechargeResponse: RechargeResponse) {
        callback?.changeToSuccessScreen((rechargeResponse))
    }

    override fun changeToErrorScreen() {

    }
}