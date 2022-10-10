package com.example.roomapp.fragments.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import com.example.roomapp.R
import com.example.roomapp.compose.ui.theme.Black200
import com.example.roomapp.compose.ui.theme.Red200
import com.example.roomapp.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

//        binding.logincompose.setContent {
////            SimpleTextComponent("Example of Simple Material Text Field")
//            SimpleMaterialTextFieldComponent()
//            Divider(color = Color.Gray)
//            }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("DATA_INFO", Context.MODE_PRIVATE)


        binding.btnlogin.setOnClickListener {
            val str_email = binding.etemail.text.toString()
            val str_pass = binding.etpassword.text.toString()

            if(str_email.isNullOrEmpty() || str_pass.isNullOrEmpty()){
                Toast.makeText(activity, "Masukan Informasi", Toast.LENGTH_SHORT).show()
            }else{
                val email_id = sharedPreferences.getString("email", "defaultEmail")
                val password = sharedPreferences.getString("pass", "defaultPass")

                if (email_id.equals(str_email) && password.equals(str_pass)){
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.apply()
                   it.findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                }else{
                    Toast.makeText(activity, "Data Kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btntvreg.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @ExperimentalFoundationApi
    @Composable
    fun SimpleMaterialTextFieldComponent(){
        var text by savedInstanceState { "" }
        // TextField is a Material Design implementation of EditText in Compose.
        // label is the text that is displayed inside the TextField when no text is there
        TextField(
            value = text,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            onValueChange = { text = it },
            label = { Text("Label") }
        )
    }
}