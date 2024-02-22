package com.intern.xtrade.Funds

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.intern.xtrade.R
import java.text.NumberFormat
import java.util.Locale

class WithDrawFunds : AppCompatActivity() {
    lateinit var withDrawBack : ImageView
    lateinit var withDrawFundsAmount: EditText
    lateinit var withDrawFundsWarning : TextView
    lateinit var withDrawButton : AppCompatButton
    lateinit var balance : TextView
    lateinit var warning : TextView
    lateinit var sharedPreferences: SharedPreferences
    var withDrawAmount =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)

        withDrawBack = findViewById(R.id.withdraw_back)
        withDrawFundsAmount = findViewById(R.id.WithDrawFunds_amount)
        withDrawButton = findViewById(R.id.withDrawButton)
        balance = findViewById(R.id.withDraw_balance)

        sharedPreferences = getSharedPreferences("MONEY", Context.MODE_PRIVATE)
        val availableMoney = sharedPreferences.getInt("AVAILABLEINR",0)
        val indiLocal = Locale("en", "in")
        balance.text = NumberFormat.getCurrencyInstance(indiLocal).format(availableMoney)
        warning = findViewById(R.id.withDraw_warning)



        warning.visibility = TextView.GONE


        withDrawButton.isClickable = false
        withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))

        withDrawFundsAmount.addTextChangedListener(object :TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {

                if(s!=null && s.toString()!="") {
                    val amount = s.toString().toInt()
                    if (amount > availableMoney) {
                        warning.visibility = TextView.VISIBLE
                        withDrawButton.isClickable = false
                        withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
                    }
                    else if ( amount==0) {
                        warning.visibility = TextView.GONE
                        withDrawButton.isClickable = false
                        withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
                    }
                    else{
                        warning.visibility = TextView.GONE
                        withDrawButton.isClickable = true
                        withDrawButton.setBackgroundColor(resources.getColor(R.color.card_blue))
                    }
                }
                else
                {
                    withDrawButton.isClickable = false
                    withDrawButton.setBackgroundColor(resources.getColor(R.color.grey))
                }

            }

        })

        withDrawButton.setOnClickListener {
            if(withDrawAmount>availableMoney){
                Toast.makeText(this,"Funds not sufficient",Toast.LENGTH_SHORT).show()
            }else{
                sharedPreferences.edit().putInt("AVAILABLEINR",availableMoney-withDrawAmount).apply()
                Toast.makeText(this,"WithDraw Successful ${availableMoney-withDrawAmount}",Toast.LENGTH_SHORT).show()
            }
        }


        withDrawBack.setOnClickListener {
            finish()
        }


    }
}