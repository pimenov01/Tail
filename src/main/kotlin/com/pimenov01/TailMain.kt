package com.pimenov01
import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineException
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.*

/**
 * The TailMain class, where the launch() function
 * is used to launch the console application and flags are declared.
 */
class TailMain {

    @Option(name = "-c", metaVar = "extractSymbols", required = false, usage = "extract some last symbols")
    private var c = -1

    @Option(name = "-n", metaVar = "extractLines", required = false, usage = "extract some last lines")
    private var n = -1

    @Option(name = "-o", metaVar = "outName", required = false, usage = "output file name")
    private var outName = ""

    @Argument(required = false, metaVar = "inNames", usage = "names of the entered files separated by spaces")
    private var inNames = mutableListOf<String>()

    /**
     * The launch function, where I catch exceptions and errors
     * that the user may have made and output a result
     * based on the flags selected by the user.
     */
    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        try {
            parser.parseArgument(*args)
        } catch (e: CmdLineException) {
            println(e.message)
            println("enter the command in this format:")
            println("java -jar tail.jar [-c num|-n num] [-o ofile] inNames")
            parser.printUsage(System.out)
            return
        }
        val tail =  when {
            (c == -1 && n == -1) -> Tail(Selection.LINES, 10)
            (c != -1 && n != -1) -> {
                println("You can't enter the -n and -c flags at the same time. Choose one of them.")
                parser.printUsage(System.out)
                return
            }
            n > 0 -> Tail(Selection.LINES, n)
            c > 0 -> Tail(Selection.SYMBOLS, c)
            else -> {
                println("You entered something incorrectly, make the correct request.")
                println("java -jar tail.jar [-c num|-n num] [-o ofile] inNames")
                parser.printUsage(System.out)
                return
            }
        }
        try {
            val writer = if (outName == "") {
                BufferedWriter(OutputStreamWriter(System.out))
            } else {
                BufferedWriter(OutputStreamWriter(FileOutputStream(outName)))
            }
            for (name in inNames) {
                if (inNames.size > 1) {
                    writer.write("$name ")
                    writer.newLine()
                }
                tail.start(writer, BufferedReader(InputStreamReader(FileInputStream(name))))
                writer.newLine()
            }
            writer.close()
        } catch (e: IOException) {
            println(e.message)
            return
        }
    }
}

/**
 * Entry point to the program.
 */
fun main(args:Array<String>) {
    TailMain().launch(args)
}