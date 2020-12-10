@file:Suppress("UNUSED_PARAMETER")

package lesson7

import kotlin.math.max


/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    //память O(N*M)
    //время O(N*M)
    val n = first.length
    val m = second.length
    val dpTable = MutableList(n + 1) { MutableList(m + 1) { 0 } }

    for (i in 1..n) {
        for (j in 1..m) {
            if (first[i - 1] == second[j - 1]) {
                dpTable[i][j] = dpTable[i - 1][j - 1] + 1
            } else {
                dpTable[i][j] = max(dpTable[i - 1][j], dpTable[i][j - 1])
            }
        }
    }
    var res = ""
    var i = n
    var j = m
    while (i > 0 && j > 0) {
        when {
            first[i - 1] == second[j - 1] -> {
                res += first[i - 1]
                i--
                j--
            }
            dpTable[i - 1][j] == dpTable[i][j] -> i--
            else -> j--
        }
    }
    return res.reversed()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    //память O(N*logN)
    //время O(N)
    if (list.isEmpty()) return list
    val d = MutableList(list.size + 1) { 0 }
    val pos = MutableList(list.size + 1) { 0 }
    val prev = MutableList(list.size) { 0 }
    var length = 0
    pos[0] = -1;
    d[0] = Int.MAX_VALUE

    for (i in list.size - 1 downTo 0) {
        var j = binarySearch(d, list[i])
        if (d[j] < list[i] && d[j - 1] > list[i]) {
            d[j] = list[i]
            pos[j] = i
            prev[i] = pos[j - 1]
            length = max(length, j)
        }
    }

    if (length == 1) return listOf(list[0])

    val out = mutableListOf<Int>()
    var p = pos[length]

    while (p != -1) {
        out.add(list[p])
        p = prev[p]
    }

    return out
}

fun binarySearch(a: MutableList<Int>, x: Int): Int {
    var l = 0
    var r = a.size - 1
    while (r - l > 1) {
        val m = (l + r) / 2
        if (x < a[m]) l = m
        else r = m
    }
    return r
}


/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5

