package com.example.simplepostrequest

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiInterface=APIClient().getData()?.create(APIInterface::class.java)
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPost.setOnClickListener {
            val obUser=DataItem(edName.text.toString())
            post(obUser)
        }

    }
    fun post(obUser:DataItem){
        funProgressDialog()

        if (apiInterface!=null){
            apiInterface.addUser(obUser).enqueue(object : Callback<DataItem?> {
                override fun onResponse(call: Call<DataItem?>, response: Response<DataItem?>) {
                    progressDialog.dismiss()
                    edName.setText("")
                    Toast.makeText(applicationContext, "user add", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<DataItem?>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    fun funProgressDialog(){
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
    }
}