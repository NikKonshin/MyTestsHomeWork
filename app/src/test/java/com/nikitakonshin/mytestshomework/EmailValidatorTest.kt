package com.nikitakonshin.mytestshomework

import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@email.co.uk"))
    }

    @Test
    fun emailValidator_CorrectEmailSubDomainNumber_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("name@123456.uk"))
    }

    @Test
    fun emailValidator_CorrectEmailNameNumber_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("123456@123456.uk"))
    }

    @Test
    fun emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email"))
    }

    @Test
    fun emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email..com"))
    }

    @Test
    fun emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("@email.com"))
    }

    @Test
    fun emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(""))
    }

    @Test
    fun emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(null))
    }

    @Test
    fun emailValidator_Number_Domain_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("email@list.123"))
    }

    @Test
    fun emailValidator_NotDots_And_NotAt_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("emailemailemailru"))
    }

    @Test
    fun emailValidator_NotAt_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("emailemailemail.ru"))
    }

    @Test
    fun emailValidator_AllNumbers_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("111222333"))
    }

    @Test
    fun emailValidator_AllSymbols_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("..,*&?!@[].@@)"))
    }

    @Test
    fun emailValidator_RusLetters_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("емаил@емаил.ру"))
    }
    @Test
    fun emailValidator_OneRusLettersStart_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("еame@email.com"))
    }

    @Test
    fun emailValidator_OneRusLettersCenter_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@eтmail.com"))
    }

    @Test
    fun emailValidator_OneRusLettersEnd_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@eтmail.comт"))
    }

    @Test
    fun emailValidator_ExtraCharEnd_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email.com+"))
    }
    @Test
    fun emailValidator_InvalidCharEnd_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email.com,"))
    }
    @Test
    fun emailValidator_InvalidCharStart_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("?name@email.com"))
    }

    @Test
    fun emailValidator_InvalidCharCenter_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("name@email,.com"))
    }

    @Test
    fun emailValidator_CharStart_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail("+-name@email.com"))
    }

    @Test
    fun emailValidator_MaxCharStart_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(
            "hadaddkdadadadhfakdshglkadshfkadjshfljhfalkjhfkjhvil" +
                    "vildufyskhfkhcvkhvhidfidfyoidfvhxkhzvhvdxkhzvhvdha" +
                    "dadadadadadadadadadadadadadadadadfdgdfgfhgfhdhgfgd" +
                    "hdfghshdfghdgghdghdghghadadadadadadadadadadadadada" +
                    "dadadadfdgdfgfhgfhdhgfgdhdfghshdfghdgghdghdghgfhdg" +
                    "hdghname@email.com"))
    }

    @Test
    fun emailValidator_MaxCharCenter_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(
            "name@emailemailemailemailemailemailemailemailemailemailemailemailemailemailemail.com"))
    }

    @Test
    fun emailValidator_MaxCharEnd_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(
            "name@email.comcomcomcomcomcomcomcomcom"))
    }

    @Test
    fun emailValidator_ReverseEmail_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail("com.email@name"))
    }
}
