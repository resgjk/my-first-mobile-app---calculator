package com.example.calculator

import android.media.VolumeShaper.Operation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.plus.setOnClickListener(::plusFunction)
        bindingClass.minus.setOnClickListener(::minusFunction)
        bindingClass.multiply.setOnClickListener(::multiplyFunction)
        bindingClass.divide.setOnClickListener(::divideFunction)
        bindingClass.getResult.setOnClickListener(::getResultFunction)

        bindingClass.dot.setOnClickListener {
            if ("." !in bindingClass.textView.text) {
                bindingClass.textView.setText("${bindingClass.textView.text}.")
            }
        }

        bindingClass.delete.setOnClickListener {
            if (bindingClass.textView.text.length > 0) {
                bindingClass.textView.setText(
                    bindingClass.textView.text.toString()
                        .substring(0, bindingClass.textView.text.length - 1)
                )
            }
        }

        bindingClass.clear.setOnClickListener {
            bindingClass.textView.setText("")
            bindingClass.textView2.setText("")
        }

        val numbersButtonsArray = arrayOf(
            bindingClass.one,
            bindingClass.two,
            bindingClass.three,
            bindingClass.four,
            bindingClass.five,
            bindingClass.six,
            bindingClass.seven,
            bindingClass.eight,
            bindingClass.nine,
            bindingClass.zero
        )

        for (btn in numbersButtonsArray) {
            btn.setOnClickListener {
                if (bindingClass.textView.text.length < 22) {
                    bindingClass.textView.setText("${bindingClass.textView.text}${btn.text}")
                }
            }
        }

    }

    fun getResultFunction(view: View) {
        if (bindingClass.textView.text != "" && bindingClass.textView2.text != "") {
            bindingClass.textView.setText(
                completeOperation(
                    bindingClass.textView2.text.toString(),
                    bindingClass.textView.text.toString(),
                    bindingClass.textView2.text.toString().split(" ")[1]
                )
            )
            bindingClass.textView2.setText("")
        }
    }

    fun setNewOperation(view: TextView, newOperation: String) {
        var viewText = view.text.toString()
        when {
            "+" in viewText -> {
                viewText = viewText.split(" +")[0]
            }
            "-" in viewText -> {
                viewText = viewText.split(" -")[0]
            }
            "*" in viewText -> {
                viewText = viewText.split(" *")[0]
            }
            "/" in viewText -> {
                viewText = viewText.split(" /")[0]
            }
        }
        view.setText("${viewText} $newOperation")

    }

    fun completeOperation(firstPart: String, secondPart: String, operation: String): String {
        var firstNumber = firstPart.split(" ${operation}")[0].toDouble()
        var secondNumber = secondPart.toDouble()
        var res: Double = 0.0
        when (operation) {
            "+" -> {
                res = firstNumber + secondNumber
            }
            "-" -> {
                res = firstNumber - secondNumber
            }
            "*" -> {
                res = firstNumber * secondNumber
            }
            "/" -> {
                if (secondNumber != 0.0) {
                    res = firstNumber / secondNumber
                } else return "Division by zero error"
            }
        }
        if (res.toString().split(".")[1] == "0") {
            return res.toString().split(".")[0]
        }
        return res.toString()
    }

    fun plusFunction(view: View) {
        if (bindingClass.textView2.text == "" && bindingClass.textView.text != "") {
            bindingClass.textView2.setText("${bindingClass.textView.text} +")
            bindingClass.textView.setText("")
        } else {
            if (bindingClass.textView2.text != "" && bindingClass.textView.text == "") {
                setNewOperation(bindingClass.textView2, "+")
            } else {
                bindingClass.textView2.setText(
                    completeOperation(
                        bindingClass.textView2.text.toString(),
                        bindingClass.textView.text.toString(),
                        bindingClass.textView2.text.toString().split(" ")[1]
                    ) + " +"
                )
                bindingClass.textView.setText("")
            }
        }
    }

    fun minusFunction(view: View) {
        if (bindingClass.textView2.text == "" && bindingClass.textView.text != "") {
            bindingClass.textView2.setText("${bindingClass.textView.text} -")
            bindingClass.textView.setText("")
        } else {
            if (bindingClass.textView2.text != "" && bindingClass.textView.text == "") {
                setNewOperation(bindingClass.textView2, "-")
            } else {
                bindingClass.textView2.setText(
                    completeOperation(
                        bindingClass.textView2.text.toString(),
                        bindingClass.textView.text.toString(),
                        bindingClass.textView2.text.toString().split(" ")[1]
                    ) + " -"
                )
                bindingClass.textView.setText("")
            }
        }
    }

    fun multiplyFunction(view: View) {
        if (bindingClass.textView2.text == "" && bindingClass.textView.text != "") {
            bindingClass.textView2.setText("${bindingClass.textView.text} *")
            bindingClass.textView.setText("")
        } else {
            if (bindingClass.textView2.text != "" && bindingClass.textView.text == "") {
                setNewOperation(bindingClass.textView2, "*")
            } else {
                bindingClass.textView2.setText(
                    completeOperation(
                        bindingClass.textView2.text.toString(),
                        bindingClass.textView.text.toString(),
                        bindingClass.textView2.text.toString().split(" ")[1]
                    ) + " *"
                )
                bindingClass.textView.setText("")
            }
        }
    }

    fun divideFunction(view: View) {
        if (bindingClass.textView2.text == "" && bindingClass.textView.text != "") {
            bindingClass.textView2.setText("${bindingClass.textView.text} /")
            bindingClass.textView.setText("")
        } else {
            if (bindingClass.textView2.text != "" && bindingClass.textView.text == "") {
                setNewOperation(bindingClass.textView2, "/")
            } else {
                bindingClass.textView2.setText(
                    completeOperation(
                        bindingClass.textView2.text.toString(),
                        bindingClass.textView.text.toString(),
                        bindingClass.textView2.text.toString().split(" ")[1]
                    ) + " /"
                )
                bindingClass.textView.setText("")
            }
        }
    }
}