package com.icc2024.recargapp.ui.recharge

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.icc2024.recargapp.R
import com.icc2024.recargapp.contract.RechargeContract
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.presenter.RechargePresenter

class RechargeFragment : Fragment(), RechargeContract.View {
    private var presenter : RechargePresenter? = null;
    private var callback : Callbacks? = null;
    interface Callbacks {
        fun showConfirmationScreen(req: RechargeRequestDto)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recharge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val companySpinner = activity?.findViewById<Spinner>(R.id.spinner_company)

        //Set event listener for the Companies spinner
        companySpinner?.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter?.selectCompany(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }


        val packageSpinner = activity?.findViewById<Spinner>(R.id.spinner_package)

        //Set event listener for the Package spinner
        packageSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter?.selectRechargeSku(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        val confirmButton = activity?.findViewById<Button>(R.id.button_confirmar)
        confirmButton?.setOnClickListener {
            val num1 = activity?.findViewById<EditText>(R.id.editText_numero)
            val num2 = activity?.findViewById<EditText>(R.id.editText_confirmar_numero)

            //Verify the number fields match
            if(num1?.text.toString() != num2?.text.toString()) {
                Toast.makeText(context, "Los números telefonicos  no coinciden", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }



            //Verify the fields arent empty
            if(num1?.text?.isEmpty() == true) {
                Toast.makeText(context, "Rellene todos los campos", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            //Move to confirmation window
            presenter?.promptConfirmation(num1?.text.toString())

        }



        //Process SKU and Company data
        presenter?.loadInitialData();

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RechargePresenter(this, requireContext())

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    //Contract.View

    override fun populateCompaniesSpinner(values: List<String>) {
        val spinner = activity?.findViewById<Spinner>(R.id.spinner_company)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, values);
        spinner?.adapter = adapter;
    }

    override fun populatePackageSpinner(values: List<String>) {
        Log.v("RechargeActivity", "populate spinner");
        val spinner = activity?.findViewById<Spinner>(R.id.spinner_package)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, values);
        spinner?.adapter = adapter;
    }

    @SuppressLint("SetTextI18n")
    override fun setPriceLabel(price: String) {
        val label = activity?.findViewById<TextView>(R.id.priceLabel)
        label?.text = "$${price}.00";
    }

    override fun changeFragment(req: RechargeRequestDto) {
        callback?.showConfirmationScreen(req)
    }
}