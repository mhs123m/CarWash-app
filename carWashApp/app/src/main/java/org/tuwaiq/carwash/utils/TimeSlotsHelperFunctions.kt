package org.tuwaiq.carwash.utils

class TimeSlotsHelperFunctions {

    companion object {

        fun convertIndexToTime(index: Int): String {
            return when (index) {
                0 -> "09:00 - 09:30"
                1 -> "09:30 - 10:00"
                2 -> "10:00 - 10:30"
                3 -> "10:30 - 11:00"
                4 -> "11:00 - 11:30"
                5 -> "11:30 - 12:00"
                6 -> "12:00 - 12:30"
                7 -> "12:30 - 13:00"
                8 -> "13:00 - 13:30"
                9 -> "13:30 - 14:00"
                10 -> "14:00 - 14:30"
                11 -> "14:30 - 15:00"
                12 -> "15:00 - 15:30"
                13 -> "15:30 - 16:00"
                14 -> "16:00 - 16:30"
                15 -> "16:30 - 17:00"
                16 -> "17:00 - 17:30"
                17 -> "17:30 - 18:00"
                18 -> "18:00 - 18:30"
                19 -> "18:30 - 19:00"
                else -> "closed"
            }

        }

        fun getStartTime(index: Int): String{
            val t = convertIndexToTime(index)
            val indexOfDash = t.indexOf('-')
            return t.substring(0,indexOfDash-1)
        }
    }
}