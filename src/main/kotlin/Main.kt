
import java.io.File
import java.io.InputStream

fun main() {

    val classLoader = Thread.currentThread().contextClassLoader
    val latexFileStream: InputStream? = classLoader.getResourceAsStream("coverletter.tex")
    val outputPath = "coverletter.tex"

    // Function to read user input with a prompt
    fun readInput(prompt: String): String {
        print(prompt)
        return readlnOrNull().orEmpty()
    }

    // Collecting user inputs
    val companyName = readInput("Enter the company name: ")
    val recipient = readInput("Enter the recipient's name: (such as Google team) ")
    val jobTitle = readInput("Enter the job title: ")
    val address1 = readInput("Enter the address line 1: ")
    val address2 = readInput("Enter the address line 2: ")
    val address3 = readInput("Enter the address line 3: ")
    val HR = readInput("Enter the HR person's name: ")

    latexFileStream?.let {
        val fileContent = latexFileStream.bufferedReader().use { it.readText() }
        val updatedContent = fileContent
            .replace("#{companyName}#", companyName)
            .replace("#{recipient}#", recipient)
            .replace("#{jobTitle}#", jobTitle)
            .replace("#{address1}#", address1)
            .replace("#{address2}#", address2)
            .replace("#{address3}#", address3)
            .replace("#{HR}#", HR)



        // Write the updated content to the file
        File(outputPath).writeText(updatedContent)
        println("Updated cover letter written to $outputPath")
    } ?: run {
        println("Could not find the LaTeX template file.")
    }
}