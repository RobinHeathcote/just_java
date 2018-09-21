package com.example.android.justjava


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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
        var summary = createOrderSummary(calculatePrice())
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
        return "Name: Robin Heathcote" + "\n Quantity: $quantity \n Total: $price \n Thankyou!"
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private fun displayQuantity(number: Int) {
        val quantityTextView = findViewById<View>(R.id.quantity_text_view) as TextView
        quantityTextView.text = "" + number
    }

    /**
     * This method displays the given price on the screen.
     */
    private fun displayPrice(number: Int) {
        val priceTextView = findViewById<View>(R.id.price_text_view) as TextView
        priceTextView.text = NumberFormat.getCurrencyInstance().format(number.toLong())
    }

    /**
     * This method displays the given text on the screen.
     */
    private fun displayMessage(message: String) {
        val priceTextView = findViewById<View>(R.id.price_text_view) as TextView
        priceTextView.text = message
    }
}
