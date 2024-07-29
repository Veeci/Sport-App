package com.example.sportapp.presentation.auth.fragment.onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.sportapp.R

class FirstScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        view.findViewById<Button>(R.id.btnNext).setOnClickListener {
            viewPager?.currentItem = 1
        }

        view.findViewById<Button>(R.id.btnSkip).setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginFragment)
        }

        return view
    }

}