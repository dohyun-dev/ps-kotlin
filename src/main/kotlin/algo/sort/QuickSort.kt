package algo.sort

import java.util.Arrays

/**
 * 퀵솔트
 * 구현
 *     - 파티션 생성
 *         - 중간인덱스값 기준
 *         - 작은거는 왼쪽
 *         - 큰거는 오른쪽으로
 *         - 피봇 인덱스 반환
 *     - 퀵솔트 파티션 별로 재귀 실행
 */
class QuickSort {
    companion object {
        fun sort(arr: Array<Int>) {
            quickSort(arr, 0, arr.size - 1)
        }

        private fun quickSort(arr: Array<Int>, left: Int, right: Int) {
            if (left >= right) {
                return
            }

            val pivot = partition(arr, left, right)

            quickSort(arr, left, pivot - 1)
            quickSort(arr, left, pivot)
        }

        private fun partition(arr: Array<Int>, left: Int, right: Int): Int{
            val mid = (left + right) / 2
            var leftPtr = left
            var rightPtr = right

            while (true) {
                while  (arr[leftPtr] < arr[mid]) {
                    leftPtr++
                }

                while  (arr[rightPtr] > arr[mid]) {
                    rightPtr--
                }

                if (leftPtr >= rightPtr) {
                    break
                }

                arr[leftPtr] = arr[rightPtr].also { arr[rightPtr] = arr[leftPtr] }
                leftPtr++
                rightPtr--
            }

            return rightPtr
        }
    }
}