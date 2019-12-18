package org.icar.dss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import org.icar.dss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:MainAcivityViewModel
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
//        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainAcivityViewModel::class.java)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOf("Crop1","Crop2","Crop3"))
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        binding.spinnerCrop.adapter = adapter



    }
}
