package org.tuwaiq.carwash

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.tuwaiq.carwash.util.HelperFunctions

class EmailValidatorUnitTest {



    @Test
    fun correct_Email(): Unit {
        assertEquals(HelperFunctions.isValidEmail("mhs@gmail.com"),true)
    }

    @Test
    fun correct_Email_With_Two_Ends(): Unit {
        assertEquals(HelperFunctions.isValidEmail("mhs@gmail.edu.sa"),true)
    }

//    @Test
//    fun incorrect_Email_With_Space(): Unit {
//        assertEquals(HelperFunctions.isValidEmail("mh  s@gm ail.com"),false)
//    }
}