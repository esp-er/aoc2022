package patriker.utils
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name")
    .readLines()

fun readInputText(name: String) = File("src", "$name")
    .readText()


fun readResource(path: String): List<String> =
    object {}.javaClass.getResource(path)!!.readText().lines()


fun getResourceAsText(path: String): String? =
    object {}.javaClass.getResource(path)?.readText()


/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
