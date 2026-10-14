package com.kaamai.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kaamai.app.data.VisitReport

class KaamAIViewModel : ViewModel() {
    private val _reports = mutableStateListOf<VisitReport>()
    val reports: List<VisitReport> get() = _reports

    var showForm by mutableStateOf(false)
        private set

    var customer by mutableStateOf("")
        private set

    var notes by mutableStateOf("")
        private set

    fun updateCustomer(value: String) {
        customer = value
    }

    fun updateNotes(value: String) {
        notes = value
    }

    fun toggleForm() {
        showForm = !showForm
    }

    fun addReport() {
        if (customer.isNotBlank() && notes.isNotBlank()) {
            _reports.add(
                0,
                VisitReport(
                    customer = customer.trim(),
                    notes = notes.trim()
                )
            )
            customer = ""
            notes = ""
            showForm = false
        }
    }

    fun addReport(customerParam: String, notesParam: String) {
        if (customerParam.isNotBlank() && notesParam.isNotBlank()) {
            _reports.add(
                0,
                VisitReport(
                    customer = customerParam,
                    notes = notesParam
                )
            )
        }
    }
}
