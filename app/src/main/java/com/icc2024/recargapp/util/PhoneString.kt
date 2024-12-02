package com.icc2024.recargapp.util

fun formatPhoneNumber(value: String?): String {
    return value?.filter { it.isDigit() } // Remove any non-digit characters
        ?.take(10) // Ensure only the first 10 digits are considered
        ?.chunked(3) // Split into chunks of 3 digits
        ?.let { chunks ->
            if (chunks.size > 2) "${chunks[0]}-${chunks[1]}-${chunks.drop(2).joinToString("")}"
            else chunks.joinToString("-")
        } ?: "Invalid number" // Return "Invalid number" if input is null or invalid
}