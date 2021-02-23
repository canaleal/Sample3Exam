package com.canaleal.sample3.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.canaleal.sample3.MainActivity
import com.canaleal.sample3.PetApplication
import com.canaleal.sample3.ui.FirstFragmentDirections
import com.canaleal.sample3.R
import com.canaleal.sample3.domain.Pet
import com.canaleal.sample3.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {


    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val entryViewModel: PetViewModel by activityViewModels()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        if(activity is AppCompatActivity){
            (activity as AppCompatActivity).supportActionBar?.title = "First"

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.viewModel = entryViewModel  //This is the ItemRollViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        val spinner: Spinner = binding.planetsSpinner
                // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                PetApplication.applicationContext(),
                R.array.hair_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        binding.sendButton.setOnClickListener { onSend() }

        return binding.root
    }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    private fun onSend(){

        val petBreed = binding.petNameInput.text.toString()

        if(petBreed == ""){
            val text = "Name cannot be empty!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
        else{


            val petHair = "short"
            val action =
                    FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                            Pet(petBreed, petHair)
                    )
            navController.navigate(action)
        }
    }
}