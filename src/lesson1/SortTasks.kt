@file:Suppress("UNUSED_PARAMETER")

package lesson1

import java.io.File

/**
 * Сортировка времён
 *
 * Простая
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
 * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
 *
 * Пример:
 *
 * 01:15:19 PM
 * 07:26:57 AM
 * 10:00:03 AM
 * 07:56:14 PM
 * 01:15:19 PM
 * 12:40:31 AM
 *
 * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
 * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
 *
 * 12:40:31 AM
 * 07:26:57 AM
 * 10:00:03 AM
 * 01:15:19 PM
 * 01:15:19 PM
 * 07:56:14 PM
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortTimes(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сортировка адресов
 *
 * Средняя
 *
 * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
 * где они прописаны. Пример:
 *
 * Петров Иван - Железнодорожная 3
 * Сидоров Петр - Садовая 5
 * Иванов Алексей - Железнодорожная 7
 * Сидорова Мария - Садовая 5
 * Иванов Михаил - Железнодорожная 7
 *
 * Людей в городе может быть до миллиона.
 *
 * Вывести записи в выходной файл outputName,
 * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
 * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
 *
 * Железнодорожная 3 - Петров Иван
 * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
 * Садовая 5 - Сидоров Петр, Сидорова Мария
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun sortAddresses(inputName: String, outputName: String) {
    TODO()
    /*data class Citizen(
        val lastName: String,
        val firstName: String
    )

    data class Address(
        val streetName: String,
        val houseNumber: Int
    )

    fun String.toAddress(): Pair<Citizen, Address> {
        val addressFormat = Regex("""^\S+ \S+ \d+$""")
        require(addressFormat.matches(this))
        val res = mutableListOf<String>()

        val parts = this.split(" - ")
        res += parts.first().split(" ")
        res += parts.last().split(" ")

        val citizen = Citizen(res[0], res[1])
        val address = Address(res[2], res[3].toInt())

        return citizen to address
    }

    val addresses = File(inputName).readLines().map { it.toAddress() }.sortedWith(compareBy(
        { it.second.streetName },
        { it.second.houseNumber },
        { it.first.lastName },
        { it.first.firstName }
    ))

    val outputStream = File(outputName).bufferedWriter()
    */
}

/**
 * Сортировка температур
 *
 * Средняя
 * (Модифицированная задача с сайта acmp.ru)
 *
 * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
 * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
 * Например:
 *
 * 24.7
 * -12.6
 * 121.3
 * -98.4
 * 99.5
 * -12.6
 * 11.0
 *
 * Количество строк в файле может достигать ста миллионов.
 * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
 * Повторяющиеся строки сохранить. Например:
 *
 * -98.4
 * -12.6
 * -12.6
 * 11.0
 * 24.7
 * 99.5
 * 121.3
 */
fun sortTemperatures(inputName: String, outputName: String) {
    //Асимптотика O(N)
    //Ресурсоемкость O(1)
    val maxValue = 5000 + 2730 + 1
    val numberOfValues = mutableListOf<Int>()
    for (i in 1..maxValue) numberOfValues.add(0)
    for (str in File(inputName).readLines()) {
        val temp = (str.toDouble() * 10 + 2730).toInt()
        numberOfValues[temp]++
    }
    File(outputName).bufferedWriter().use { str ->
        numberOfValues.forEachIndexed { index, value ->
            if (value != 0) {
                for (i in 1..value) {
                    str.write("${(index - 2730) / 10.0}\n")
                }
            }
        }
    }
}

/**
 * Сортировка последовательности
 *
 * Средняя
 * (Задача взята с сайта acmp.ru)
 *
 * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
 *
 * 1
 * 2
 * 3
 * 2
 * 3
 * 1
 * 2
 *
 * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
 * а если таких чисел несколько, то найти минимальное из них,
 * и после этого переместить все такие числа в конец заданной последовательности.
 * Порядок расположения остальных чисел должен остаться без изменения.
 *
 * 1
 * 3
 * 3
 * 1
 * 2
 * 2
 * 2
 */
fun sortSequence(inputName: String, outputName: String) {
    val map = mutableMapOf<Int, Int>()
    /*File(inputName).useLines { lines ->
        lines.forEach {
            val number = it.toInt()
            map[number] = map.getOrDefault(number, 0) + 1
        }
    }*/
    val numbers = File(inputName).readLines().map { it.toInt() }
    for (n in numbers) map[n] = map.getOrDefault(n, 0) + 1
    /*File(inputName).readLines().map { line ->
        val number = line.toInt()
        map[number] = map.getOrDefault(number, 0) + 1
    }*/
    /*File(inputName).bufferedReader().useLines {
        it.map { str ->
            val number = str.toInt()
            map[number] = map.getOrDefault(number, 0) + 1
        }
    }*/
    require(map.isNotEmpty())
    var maxValueOfNumbers: Pair<Int?, Int?> = null to null
    for ((number, count) in map) {
        if (maxValueOfNumbers.second == null || maxValueOfNumbers.second!! < count ||
            maxValueOfNumbers.second!! == count && maxValueOfNumbers.first!! > number
        ) maxValueOfNumbers = number to count
    }
    //require(maxValueOfNumbers.first == null)
    File(outputName).bufferedWriter().use { str ->
        map.forEach {
            if (it != maxValueOfNumbers) str.write("$it.key\n")
        }
        for (i in 1..maxValueOfNumbers.second!!)
            str.write("${maxValueOfNumbers.first!!}\n")
    }
}

/**
 * Соединить два отсортированных массива в один
 *
 * Простая
 *
 * Задан отсортированный массив first и второй массив second,
 * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
 * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
 *
 * first = [4 9 15 20 28]
 * second = [null null null null null 1 3 9 13 18 23]
 *
 * Результат: second = [1 3 4 9 9 13 15 20 23 28]
 */
fun <T : Comparable<T>> mergeArrays(first: Array<T>, second: Array<T?>) {
    TODO()
}

