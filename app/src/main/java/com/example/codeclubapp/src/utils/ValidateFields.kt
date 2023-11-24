package com.example.codeclubapp.src.utils

class ValidateFields : IValidator {
    override fun isBlankField(fields: List<String>): Boolean {
        if (fields.isEmpty()) return true
        fields.forEach { currentValue ->
            if (currentValue.isBlank()) return true
        }
        return false
    }
}