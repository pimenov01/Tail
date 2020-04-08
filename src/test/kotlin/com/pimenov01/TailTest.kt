package com.pimenov01
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.*

class TailTest {
    /**
     * Function for convenient checking of either the last lines or the last characters.
     */
    private fun assertFileContent(name: String, expectedContent: String, lines: Int, symbols: Int) {
        val file = createTempFile("tmp.txt")
        val writer = file.bufferedWriter()
        val reader = File(name).bufferedReader()
        Tail(lines, symbols).start(writer, reader)
        writer.close()
        val content = file.readText()
        assertEquals(expectedContent, content)
        file.delete()
    }

    /**
     * Several tests to check how the program works.
     * A test to check the entered information via
     * the console could not be created due to lack of knowledge.
     * An Internet search also did not help.
     * P.S the console input was checked manually, it works.
     */
    @Test
    fun lastSymbols() {
        assertFileContent("src\\\\test\\\\Texts\\\\firstExample", File("src\\\\test\\\\Texts\\\\firstExample").readText().takeLast(10), 0, 10)
        assertFileContent("src\\\\test\\\\Texts\\\\secondExample", File("src\\\\test\\\\Texts\\\\secondExample").readText().takeLast(10),0, 10)
        assertFileContent("src\\\\test\\\\Texts\\\\secondExample", "ounter is safe.", 0, 15)
        assertFileContent("src\\\\test\\\\Texts\\\\firstExample", ".", 0, 1)
    }

    @Test
    fun lastLines() {
        assertFileContent("src\\\\test\\\\Texts\\\\firstExample", File("src\\\\test\\\\Texts\\\\firstExample").readLines().takeLast(3).joinToString("\n"), 3, 0)
        assertFileContent("src\\\\test\\\\Texts\\\\secondExample", File("src\\\\test\\\\Texts\\\\secondExample").readLines().takeLast(10).joinToString("\n"), 10, 0)
    }
}
