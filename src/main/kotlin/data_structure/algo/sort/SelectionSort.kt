package data_structure.algo.sort

/**
 * 선택정렬
 * 구현
 * 1. 배열 가장 왼쪽부터 전체를 순회하며 작은거를 가져옴
 */
class SelectionSort {
    companion object {
        fun sort(arr: Array<Int>) {
            for (i in 0 until arr.size) {
                var minValueIdx = i
                for (j in i + 1 until arr.size) {
                    if (arr[minValueIdx] > arr[j]) {
                        minValueIdx = j
                    }
                }
                var temp = arr[i]
                arr[i] = arr[minValueIdx]
                arr[minValueIdx] = temp
            }
        }
    }
}