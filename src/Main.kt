import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val books: MutableList<Book> = mutableListOf()

    println("Библиотека книг")

    while (true) {
        println()
        println("1 - Добавить книгу")
        println("2 - Удалить книгу")
        println("3 - Найти книгу по названию")
        println("4 - Показать каталог")
        println("5 - Показать средний рейтинг")
        println("0 - Выйти")
        print("Ваш выбор: ")

        when (scanner.nextLine().trim()) {
            "1" -> addBook(books, scanner)
            "2" -> removeBook(books, scanner)
            "3" -> findBooks(books, scanner)
            "4" -> printCatalog(books)
            "5" -> printAverageRating(books)
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

private fun removeBook(books: MutableList<Book>, scanner: Scanner) {
    if (books.isEmpty()) {
        println("В библиотеке пока нет книг.")
        return
    }

    val title = readNotEmptyString(
        scanner,
        "Введите полное название книги для удаления: "
    )

    val book = books.find {
        it.title.equals(title, ignoreCase = true)
    }

    book?.let {
        books.remove(it)
        println("Книга «${it.title}» удалена из библиотеки")
        println("Всего книг: ${books.size}")
    }

    if (book == null) {
        println("Книга «$title» не найдена.")
    }
}

private fun findBooks(books: MutableList<Book>, scanner: Scanner) {
    if (books.isEmpty()) {
        println("В библиотеке пока нет книг.")
        return
    }

    val query = readNotEmptyString(
        scanner,
        "Введите название книги или его часть: "
    )

    val foundBooks = books.filter { book ->
        book.title.contains(query, ignoreCase = true)
    }

    if (foundBooks.isEmpty()) {
        println("Книги по запросу «$query» не найдены.")
    } else {
        println()
        println("Найдено книг: ${foundBooks.size}")

        foundBooks.forEach { book ->
            printBook(book)
            println()
        }
    }
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

private fun printAverageRating(books: MutableList<Book>) {
    if (books.isEmpty()) {
        println("В библиотеке пока нет книг. Средний рейтинг не определён.")
        return
    }

    val averageRating = with(books) {
        var totalRating = 0.0

        forEach { book ->
            totalRating += book.rating
        }

        totalRating / size
    }

    println("Средний рейтинг книг: %.2f".format(averageRating))
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