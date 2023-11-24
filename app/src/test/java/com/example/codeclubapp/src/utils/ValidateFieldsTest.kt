package com.example.codeclubapp.src.utils

import org.junit.Assert
import org.junit.Test


class ValidateFieldsTest {

    val field = listOf<String>("")
    @Test
    fun isBlankField() {
        val validateFields = ValidateFields()
        val blankField = validateFields.isBlankField(field)
        Assert.assertTrue(blankField)
    }

    @Test
    fun isNotBlankField() {
        val validateFields = ValidateFields()
        val blankField = validateFields.isBlankField(field)
        Assert.assertFalse(blankField)
    }

    @Test
    fun isNotEmptyList() {
        val validateFields = ValidateFields()
        val blankField = validateFields.isBlankField(field)
        Assert.assertFalse(blankField)
    }
}