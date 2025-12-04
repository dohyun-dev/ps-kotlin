package data_structure.algo.sort

import java.util.Arrays

/**
 * 병합정렬
 * 구현
 *      - 왼쪽 오른쪽 파티션을 나눈다
 *      - 왼쪽 오른쪽에서 값을뽑아 새로운 배열 만든다
 *      - 두배열 병합
 *      - 재귀
 */
class MergeSort {
    companion object {
        fun sort(arr: Array<Int>): Array<Int> {
            return mergeSort(arr, 0, arr.size - 1)
        }

        fun mergeSort(arr: Array<Int>, left: Int, right: Int): Array<Int> {
            if (left == right) {
                return arrayOf(arr[left])
            }

            val mid = (left + right) / 2
            val leftArr = mergeSort(arr, left, mid)
            val rightArr = mergeSort(arr, mid + 1, right)

          return merge(leftArr, rightArr)
        }

        fun merge(leftArr: Array<Int>, rightArr: Array<Int>): Array<Int> {
            val newArr = Array(leftArr.size + rightArr.size) { 0 }
            var leftPtr = 0
            var rightPtr = 0

            for (i in 0 until newArr.size) {
                if (leftPtr >= leftArr.size) {
                    newArr[i] = rightArr[rightPtr++]
                    continue
                }

                if (rightPtr >= rightArr.size) {
                    newArr[i] = leftArr[leftPtr++]
                    continue
                }

                if (leftArr[leftPtr] <= rightArr[rightPtr]) {
                    newArr[i] = leftArr[leftPtr++]
                } else {
                    newArr[i] = rightArr[rightPtr++]
                }
            }

            return newArr
        }
    }
}
