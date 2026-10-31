import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val books: MutableList<Book> = mutableListOf()

    println("Библиотека книг")

    while (true) {
        println()
        println("1 - Добавить книгу")
        println("4 - Показать каталог")
        println("0 - Выйти")
        print("Ваш выбор: ")

        when (scanner.nextLine().trim()) {
            "1" -> addBook(books, scanner)
            "4" -> printCatalog(books)
            "0" -> {
                println("Работа программы завершена")
                return
            }
            else -> println("Выберите один из пунктов меню.")
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

private fun printCatalog(books: MutableList<Book>) {
    books.run {
        if (isEmpty()) {
            println("В библиотеке пока нет книг.")
        } else {
            println()
            println("Каталог книг")

            forEach { book ->
                printBook(book)
                println()
            }

            println("Всего книг: $size")
        }
    }
}

private fun printBook(book: Book) {
    println("Название: ${book.title}")
    println("Автор: ${book.author}")
    println("Рейтинг: ${book.rating}")
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