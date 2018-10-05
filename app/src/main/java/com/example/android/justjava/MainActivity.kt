package com.example.android.justjava


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import java.text.NumberFormat
import kotlin.text.Typography.less

/**
 * This app displays an order form to order coffee.
 */
class MainActivity : AppCompatActivity() {
    private var quantity = 2
    private var price = 5
    private var extrasPrice = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This method is called when the order button is clicked.
     */
    fun submitOrder(view: View) {
        val summary = createOrderSummary(calculatePrice())
        displayMessage(summary)
        sendOrder(summary)
    }

    fun increment(view: View) {
        if (quantity + 1 > 20) {
            val toast = Toast.makeText(applicationContext, "Cannot order more than 20 coffees", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            quantity += 1
            displayQuantity(quantity)
        }
    }

    fun decrement(view: View) {
        if (quantity - 1 < 1) {
            val toast = Toast.makeText(applicationContext, "Cannot order less than 1 coffee", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            quantity -= 1
            displayQuantity(quantity)
        }
    }

    private fun calculatePrice(): Int {
        if (hasWhippedCream() && hasChocolate()) {
            return quantity * (price + (extrasPrice * 2))
        } else if (hasWhippedCream() || hasChocolate()) {
            return quantity * (price + extrasPrice)
        }
        return quantity * price
    }

    private fun createOrderSummary(price: Int): String {
        var priceMessage = ""
        priceMessage += "Name: " + getCustomerName() + "\n"
        priceMessage += printHasWhippedCream() + " whipped cream\n"
        priceMessage += printHasChocolate() + " chocolate\n"
        priceMessage += "Quantity: $quantity\n"
        priceMessage += "Total: $ $price \n Thankyou!"
        return priceMessage
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun displayQuantity(number: Int) {
        val quantityTextView = findViewById<View>(R.id.quantity_text_view) as TextView
        quantityTextView.text = number.toString()
    }

    private fun hasWhippedCream(): Boolean {
        val checkBox = findViewById<View>(R.id.whipped_cream_checkbox) as CheckBox
        return checkBox.isChecked
    }

    private fun hasChocolate(): Boolean {
        val checkBox = findViewById<View>(R.id.chocolate_checkbox) as CheckBox
        return checkBox.isChecked
    }

    private fun printHasWhippedCream(): String {
       return when (hasWhippedCream()) {
           true -> "With"
           false -> "Without"
       }
    }

    private fun printHasChocolate(): String {
        return when (hasChocolate()) {
            true -> "With"
            false -> "Without"
        }
    }

    private fun getCustomerName(): String {
        val name = findViewById<View>(R.id.customer_name_view) as TextView
        return name.getText().toString()
    }

    /**
     * This method displays the given text on the screen.
     */
    private fun displayMessage(message: String) {
        val orderSummaryTextView = findViewById<View>(R.id.order_summary_text_view) as TextView
        orderSummaryTextView.text = message
    }

    private fun sendOrder(order: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_EMAIL, "test@test.com")
            putExtra(Intent.EXTRA_SUBJECT, "New coffee order incoming")
            putExtra(Intent.EXTRA_TEXT, order )
            type = "text/plain"
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
