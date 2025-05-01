package com.arm.universalneural.errors



import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.io.PrintWriter
import java.io.StringWriter

internal class ErrorDialog(private val activity: Context, throwable: Any) {

    private var message: String = ""


    init {
        when (throwable) {
            is Throwable -> {
                message = throwable.stackTraceToString()
            }
            is String -> {
                message = throwable

            }
            else -> {
                message = "Unknown error"

            }
        }
        showAlertDialog()
    }

    private fun showAlertDialog() {
        val dialog = AlertDialog.Builder(activity).create()

        val fullMessage = message

        dialog.setMessage(fullMessage)

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Copy to clipboard") { _, _ ->
            copyToClipboard(activity, message)
            dialog.dismiss()
        }

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close") { _, _ -> dialog.dismiss() }

        dialog.show()
    }

    private fun copyToClipboard(context: Context, message: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Error Message", message)
        clipboard.setPrimaryClip(clip)
    }

    private fun getStackTraceAsString(throwable: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        throwable.printStackTrace(pw)
        return sw.toString()
    }
}
