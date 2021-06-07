package com.nikitakonshin.mytestshomework

import org.junit.Assert.*
import org.junit.Test

class MyClassWithMethodLessTreeTest {

    @Test
    fun myClassWithMethodLessTree_Array_Equals(){
        val list = arrayOf(1,2,3,4)
        assertArrayEquals(MyClassWithMethodLessTree.getArray(), list)
    }

    @Test
    fun factorial_ReturnEquals(){
        assertEquals(MyClassWithMethodLessTree.factorial(10), 3628800)
    }
    @Test
    fun factorial_ReturnNotEquals(){
        assertNotEquals(MyClassWithMethodLessTree.factorial(11), 3628800)
    }

    @Test
    fun myClassWithMethodLessTree_PersonNotNull(){
        assertNotNull(MyClassWithMethodLessTree.getPersons("anna"))
    }
    @Test
    fun myClassWithMethodLessTree_PersonNull(){
        assertNull(MyClassWithMethodLessTree.getPersons("annn"))
    }
}