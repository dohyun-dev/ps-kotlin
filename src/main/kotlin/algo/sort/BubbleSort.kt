package algo.sort

class BubbleSort {
    companion object {
        fun sort(arr: Array<Int>) {
            for (i in arr.indices) {
                for (j in 0  until arr.size - i - 1) {
                    if (arr[j] > arr[j + 1]) {
                        val temp = arr[j]
                        arr[j] = arr[j + 1]
                        arr[j + 1] = temp
                    }
                }
            }
        }
    }
}