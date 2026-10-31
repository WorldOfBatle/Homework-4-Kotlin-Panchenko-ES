fun main() {
    println("Библиотека книг")
    println()

    val books: MutableList<Book> = mutableListOf()

    val book = Book().apply {
        title = "Мастер и Маргарита"
        author = "Михаил Булгаков"
        rating = 4.8
    }

    books.add(book)

    println("Книга добавлена в библиотеку")
    println("Название: ${book.title}")
    println("Автор: ${book.author}")
    println("Рейтинг: ${book.rating}")
    println("Всего книг: ${books.size}")
}