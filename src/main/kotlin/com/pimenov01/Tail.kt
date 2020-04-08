package com.pimenov01

import java.io.BufferedReader
import java.io.BufferedWriter
import java.util.*

class Tail (
    lines: Int,
    private val symbols: Int
) {
    private val length = if (lines == 0) symbols else lines

    fun start (writer: BufferedWriter, reader: BufferedReader) = lastLinesOrSymbols(writer, reader, symbols)

    /**
     * Function for selecting parts of information that the user needs.
     * In order not to store everything in memory, a "buffer" is used,
     * the movement of which is constant, preventing the overflow of RAM.
     * Depending on the flags selected by the user, either strings or characters are returned.
     */
    private fun lastLinesOrSymbols(writer: BufferedWriter, reader: BufferedReader, symbols: Int) {
        if (symbols == 0) {
            val remainingLines = ArrayDeque<String>()
            var currentLine = reader.readLine()

            while (currentLine != null) {
                if (remainingLines.size == length)
                    remainingLines.removeFirst()
                remainingLines.addLast(currentLine)
                currentLine = reader.readLine()
            }
            writer.write(remainingLines.joinToString("\n"))
        } else {
            val remainingChars = ArrayDeque<Char>()
            var currentChar = reader.read()

            while (currentChar != -1) {
                if (remainingChars.size == length)
                    remainingChars.removeFirst()
                remainingChars.addLast(currentChar.toChar())
                currentChar = reader.read()
            }
            writer.write(remainingChars.joinToString(""))
        }
    }

}