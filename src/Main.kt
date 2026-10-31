import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val books: MutableList<Book> = mutableListOf()

    println("Библиотека книг")

    while (true) {
        println()
        println("1 - Добавить книгу")
        println("0 - Выйти")
        print("Ваш выбор: ")

        when (scanner.nextLine().trim()) {
            "1" -> addBook(books, scanner)
            "0" -> {
                println("Работа программы завершена")
                return
            }
            else -> println("Выберите пункт меню: 1 или 0.")
        }
    }
}

private fun addBook(books: MutableList<Book>, scanner: Scanner) {
    val book = Book().apply {
        title = readNotEmptyString(scanner, "Введите название книги: ")
        author = readNotEmptyString(scanner, "Введите автора: ")
        rating = readRating(scanner)
    }

    books.add(book)

    println("Книга «${book.title}» добавлена в библиотеку")
    println("Всего книг: ${books.size}")
}

private fun readNotEmptyString(scanner: Scanner, prompt: String): String {
    while (true) {
        print(prompt)
        val text = scanner.nextLine().trim()

        if (text.isNotEmpty()) {
            return text
        }

        println("Значение не должно быть пустым.")
    }
}

private fun readRating(scanner: Scanner): Double {
    while (true) {
        print("Введите рейтинг книги от 0 до 5: ")
        val rating = scanner.nextLine()
            .trim()
            .replace(',', '.')
            .toDoubleOrNull()

        if (rating != null && rating in 0.0..5.0) {
            return rating
        }

        println("Нужно ввести число от 0 до 5.")
    }
}