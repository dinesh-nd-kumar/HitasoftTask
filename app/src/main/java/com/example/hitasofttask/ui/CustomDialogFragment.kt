package com.example.hitasofttask.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.hitasofttask.R
import com.example.hitasofttask.model.User

class CustomDialogFragment(val u:User?,val btnText : String, val onClicked :(t1:String,t2:String) -> Unit ) : DialogFragment() {
    lateinit var title: EditText
    lateinit var body: EditText
    lateinit var btnCancel: Button
    lateinit var btnAdd: Button

    interface DialogListener {
        fun onAddClicked(firstInput: String, secondInput: String)
        fun onCancelClicked()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.custom_dialog, null)


        title = view.findViewById<EditText>(R.id.etFirstInput)

        body = view.findViewById<EditText>(R.id.etSecondInput)

        btnCancel = view.findViewById<Button>(R.id.btnCancel)

        btnAdd = view.findViewById<Button>(R.id.btnAdd)
        u.let {
            title.setText(u?.name)
            body.setText(u?.role)

        }
        btnAdd.text = btnText

        val dialog = AlertDialog.Builder(requireContext())
            .setView(view)
            .create()

        btnCancel.setOnClickListener {

            dialog.dismiss()
        }

        btnAdd.setOnClickListener {
            val firstValue = title.text.toString().trim()
            val secondValue = body.text.toString().trim()
            onClicked(firstValue, secondValue)
            dialog.dismiss()
        }

        return dialog
    }

}
