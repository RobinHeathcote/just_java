package com.example.android.justjava


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import java.text.NumberFormat

/**
 * This app displays an order form to order coffee.
 */
class MainActivity : AppCompatActivity() {
    internal var quantity = 2
    internal var price = 5

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
    }

    fun increment(view: View) {
        quantity += 1
        displayQuantity(quantity)
    }

    fun decrement(view: View) {
        quantity -=  1
        displayQuantity(quantity)
    }

    private fun calculatePrice(): Int {
        return quantity * price
    }

    private fun createOrderSummary(price: Int): String {
        var priceMessage = ""
        priceMessage += "Name: " + getCustomerName() + "\n"
        priceMessage += hasWhippedCream() + " whipped cream\n"
        priceMessage += hasChocolate() + " chocolate\n"
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

    private fun hasWhippedCream(): String {
        val whippedCream = findViewById<View>(R.id.whipped_cream_checkbox) as CheckBox
       return when (whippedCream.isChecked) {
           true -> "With"
           false -> "Without"
       }
    }

    private fun hasChocolate(): String {
        val whippedCream = findViewById<View>(R.id.chocolate_checkbox) as CheckBox
        return when (whippedCream.isChecked) {
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
}
