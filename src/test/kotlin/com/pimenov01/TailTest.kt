package com.pimenov01
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.*

class TailTest {
    /**
     * Function for convenient checking of either the last lines or the last characters.
     */
    private fun assertFileContent(name: String, expectedContent: String, lines: Int, size: Int) {
        val selection = if (lines == 0) Selection.SYMBOLS else Selection.LINES
        val file = createTempFile("tmp.txt")
        val writer = file.bufferedWriter()
        val reader = File(name).bufferedReader()
        Tail(selection, size).start(writer, reader)
        writer.close()
        val content = file.readText()
        assertEquals(expectedContent, content)
        file.delete()
    }

    /**
     * Several tests to check how the program works.
     */
    @Test
    fun lastSymbols() {
        assertFileContent("src/test/Texts/firstExample", File("src/test/Texts/firstExample").readText().takeLast(10), 0,  10)
        assertFileContent("src/test/Texts/secondExample", File("src/test/Texts/secondExample").readText().takeLast(10),0,  10)
        assertFileContent("src/test/Texts/secondExample", "ounter is safe.", 0,  15)
        assertFileContent("src/test/Texts/firstExample", ".", 0,  1)
    }

    @Test
    fun lastLines() {
        assertFileContent("src/test/Texts/firstExample", File("src/test/Texts/firstExample").readLines().takeLast(3).joinToString("\n"), 3,  3)
        assertFileContent("src/test/Texts/secondExample", File("src/test/Texts/secondExample").readLines().takeLast(10).joinToString("\n"), 10,  10)
    }
}
