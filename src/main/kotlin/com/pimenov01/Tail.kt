package com.pimenov01

import java.io.BufferedReader
import java.io.BufferedWriter
import java.util.*

class Tail (
    private val selection: Selection,
    private val length: Int
) {
    fun start (writer: BufferedWriter, reader: BufferedReader) = if (selection == Selection.LINES) lastLines(writer, reader) else lastSymbols(writer, reader)

    /**
     * Function for selecting parts of information that the user needs.
     * In order not to store everything in memory, a "buffer" is used,
     * the movement of which is constant, preventing the overflow of RAM.
     * Depending on the flags selected by the user, either strings or characters are returned.
     */
    private fun lastLines(writer: BufferedWriter, reader: BufferedReader) {
        val remainingLines = ArrayDeque<String>()
        var currentLine = reader.readLine()
        while (currentLine != null) {
            if (remainingLines.size == length)
                remainingLines.removeFirst()
            remainingLines.addLast(currentLine)
            currentLine = reader.readLine()
        }
        writer.write(remainingLines.joinToString("\n"))
    }

    private fun lastSymbols(writer: BufferedWriter, reader: BufferedReader) {
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