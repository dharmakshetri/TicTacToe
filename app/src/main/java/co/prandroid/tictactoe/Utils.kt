package co.prandroid.tictactoe

import java.util.*

/**
 * Created by dharmakshetri on 6/29/17.
 */
class Utils {
    companion object {
        var playerOne = ArrayList<Int>()
        var playerTwo = ArrayList<Int>()


        fun gameStrategy3(): Int {
            // ********* ROW *************
            // row1
            if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3)) {
                return 1
            }
            // row1
            if (playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3)) {
                return 2
            }

            // row2
            if (playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6)) {
                return 1
            }
            // row1
            if (playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6)) {
                return 2
            }

            // row1
            if (playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9)) {
                return 1
            }
            // row3
            if (playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9)) {
                return 2
            }


            // ********* COLUMN *************


            // col 1
            if (playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7)) {
                return 1
            }
            // col 1
            if (playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7)) {
                return 2
            }

            // col 2
            if (playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8)) {
                return 1
            }
            // col 2
            if (playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8)) {
                return 2
            }

            // col 3
            if (playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9)) {
                return 1
            }
            // col 3
            if (playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9)) {
                return 2
            }


            //***** DIAGONAL **********

            // diagonal 1
            if (playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9)) {
                return 1
            }
            // diagonal 1
            if (playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9)) {
                return 2
            }

            // diagonal 2
            if (playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7)) {
                return 1
            }
            // diagonal 1
            if (playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7)) {
                return 2
            }
            return -1

        }


        fun gameStrategy4(): Int {
            // ********* ROW *************
            // row1
            if (playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3) && playerOne.contains(1)) {
                return 1
            }
            // row1
            if (playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3) && playerTwo.contains(4)) {
                return 2
            }

            // row2
            if (playerOne.contains(5) && playerOne.contains(6) && playerOne.contains(7) && playerOne.contains(8)) {
                return 1
            }
            // row2
            if (playerTwo.contains(5) && playerTwo.contains(6) && playerTwo.contains(7) && playerTwo.contains(8)) {
                return 2
            }

            // row3
            if (playerOne.contains(9) && playerOne.contains(10) && playerOne.contains(11) && playerOne.contains(12)) {
                return 1
            }
            // row3
            if (playerTwo.contains(9) && playerTwo.contains(10) && playerTwo.contains(11) && playerTwo.contains(12)) {
                return 2
            }

            // row4
            if (playerOne.contains(13) && playerOne.contains(14) && playerOne.contains(15) && playerOne.contains(16)) {
                return 1
            }
            // row4
            if (playerTwo.contains(13) && playerTwo.contains(14) && playerTwo.contains(15) && playerTwo.contains(16)) {
                return 2
            }


            // ********* COLUMN *************


            // col1
            if (playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9) && playerOne.contains(13)) {
                return 1
            }
            // col1
            if (playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9) && playerTwo.contains(13)) {
                return 2
            }

            // col2
            if (playerOne.contains(2) && playerOne.contains(6) && playerOne.contains(10) && playerOne.contains(14)) {
                return 1
            }
            // col2
            if (playerTwo.contains(2) && playerTwo.contains(6) && playerTwo.contains(10) && playerTwo.contains(14)) {
                return 2
            }

            // col3
            if (playerOne.contains(3) && playerOne.contains(7) && playerOne.contains(11) && playerOne.contains(15)) {
                return 1
            }
            // col3
            if (playerTwo.contains(3) && playerTwo.contains(7) && playerTwo.contains(11) && playerTwo.contains(15)) {
                return 2
            }

            // col4
            if (playerOne.contains(4) && playerOne.contains(8) && playerOne.contains(12) && playerOne.contains(16)) {
                return 1
            }
            // col4
            if (playerTwo.contains(4) && playerTwo.contains(8) && playerTwo.contains(12) && playerTwo.contains(16)) {
                return 2
            }

            //***** DIAGONAL **********

            // dig1
            if (playerOne.contains(1) && playerOne.contains(6) && playerOne.contains(11) && playerOne.contains(16)) {
                return 1
            }
            // dig1
            if (playerTwo.contains(1) && playerTwo.contains(6) && playerTwo.contains(11) && playerTwo.contains(16)) {
                return 2
            }

            // dig2
            if (playerOne.contains(4) && playerOne.contains(7) && playerOne.contains(10) && playerOne.contains(13)) {
                return 1
            }
            // dig2
            if (playerTwo.contains(4) && playerTwo.contains(7) && playerTwo.contains(10) && playerTwo.contains(13)) {
                return 2
            }

            return -1


        }

    }

}