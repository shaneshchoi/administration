package com.example.administration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CompanyInfoFragmentThree : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //call from fragment_company_info_three.xml
        return inflater.inflate(R.layout.fragment_company_info_three, container, false)
    }
}