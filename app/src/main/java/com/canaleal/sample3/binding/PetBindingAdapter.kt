package com.canaleal.sample3.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.canaleal.sample3.domain.Pet

/**
 * Assignment2 created by alexc
 * student ID: 991514796
 * on 2021-02-16 */

@SuppressLint("SetTextI18n")
@BindingAdapter("petName")
fun bindName(textView: TextView, pet: Pet?){
    if(pet is Pet){


        textView.text = "Breed: " + pet.petBreed

    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("petType")
fun bindType(textView: TextView, pet: Pet?){
    if(pet is Pet){


        textView.text = "Hair Length: " + pet.petHair

    }
}