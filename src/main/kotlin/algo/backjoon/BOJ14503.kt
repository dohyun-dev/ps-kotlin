package main.kotlin.algo.backjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

enum class Direction(val dr: Int, val dc: Int) {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    fun turnLeft(): Direction =
        entries[(ordinal + 3) % 4]

    fun backward(): Direction =
        entries[(ordinal + 2) % 4]
}

data class Position(val r: Int, val c: Int) {
    fun move(dir: Direction): Position =
        Position(r + dir.dr, c + dir.dc)
}

class Board(val map: Array<IntArray>) {

    fun isOut(position: Position): Boolean =
        position.r !in map.indices || position.c !in map[0].indices

    fun isWall(position: Position): Boolean =
        map[position.r][position.c] == 1

    fun isDirty(position: Position): Boolean =
        map[position.r][position.c] == 0

    fun hasAdjacentDirty(position: Position): Boolean =
        Direction.entries.any { dir ->
            val next = position.move(dir)
            !isOut(next) && isDirty(next)
        }
}

class Robot(
    var position: Position,
    var direction: Direction
) {
    fun clean(board: Board) {
        board.map[position.r][position.c] = 2
    }

    fun turnLeft() {
        direction = direction.turnLeft()
    }

    fun moveForward() {
        position = position.move(direction)
    }

    fun moveBackward() {
        position = position.move(direction.backward())
    }

    fun canMoveForward(board: Board): Boolean {
        val next = position.move(direction)
        return !board.isOut(next) &&
                !board.isWall(next) &&
                board.isDirty(next)
    }

    fun canMoveBackward(board: Board): Boolean {
        val back = position.move(direction.backward())
        return board.isOut(back) || board.isWall(back)
    }
}

class Solution(
    private val board: Board,
    private val robot: Robot
) {
    fun solve(): Int {
        var cleaned = 0

        while (true) {
            if (board.isDirty(robot.position)) {
                robot.clean(board)
                cleaned++
                continue
            }

            if (!board.hasAdjacentDirty(robot.position)) {
                if (robot.canMoveBackward(board)) break
                robot.moveBackward()
                continue
            }

            robot.turnLeft()
            if (robot.canMoveForward(board)) {
                robot.moveForward()
            }
        }
        return cleaned
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val d = st.nextToken().toInt()

    val map = Array(n) { IntArray(m) }

    for (i in 0 until n) {
        st = StringTokenizer(br.readLine())
        for (j in 0 until m) {
            map[i][j] = st.nextToken().toInt()
        }
    }

    val solution = Solution(
        board = Board(map),
        robot = Robot(Position(r, c), Direction.entries[d])
    )

    println(solution.solve())
}
