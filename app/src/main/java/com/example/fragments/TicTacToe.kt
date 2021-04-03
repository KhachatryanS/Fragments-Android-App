package com.example.tictactoe

class TicTacToe{
    val X = 'x'
    val O = 'O'
    val B = '#'
    val NO_ONE = 'N'
    private val board = arrayOf(
        B, B, B,
        B, B, B,
        B, B, B
    )
    var stepsCount = 0
    private var isXTurn = true
    fun isXTurn():Boolean{
        return isXTurn
    }

    fun getWinner(): Char?{
        if( (board[0] == X && board[1] == X && board[2] == X) ||
            (board[3] == X && board[4] == X && board[5] == X) ||
            (board[6] == X && board[7] == X && board[8] == X) ||

            (board[0] == X && board[3] == X && board[6] == X) ||
            (board[1] == X && board[4] == X && board[7] == X) ||
            (board[2] == X && board[5] == X && board[8] == X) ||

            (board[0] == X && board[5] == X && board[8] == X) ||
            (board[2] == X && board[5] == X && board[6] == X)
        )
            return X
        else if(
            (board[0] == O && board[1] == O && board[2] == O) ||
            (board[3] == O && board[4] == O && board[5] == O) ||
            (board[6] == O && board[7] == O && board[8] == O) ||

            (board[0] == O && board[3] == O && board[6] == O) ||
            (board[1] == O && board[4] == O && board[7] == O) ||
            (board[2] == O && board[5] == O && board[8] == O) ||

            (board[0] == O && board[5] == O && board[8] == O) ||
            (board[2] == O && board[5] == O && board[6] == O)
        )
            return O
        else if(stepsCount == 9)
            return NO_ONE

        return null
    }

    fun makeStep(index: Int): Boolean{
        if(board[index] != B)
            return false
        if(isXTurn)
            this.board[index] = X
        else
            this.board[index] = O
        isXTurn = !isXTurn
        stepsCount++
        return true
    }
    fun getBoard():Array<Char>{
        return board
    }
}


