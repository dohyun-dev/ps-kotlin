package data_structure.algo.sort

class InsertionSort {
    companion object {
        fun sort(arr: Array<Int>) {
            for (i in 1 until arr.size) {
                for (j in i downTo  1 step 1) {
                    if (arr[j-1] > arr[j]) {
                        val temp = arr[j]
                        arr[j] = arr[j-1]
                        arr[j-1] = temp
                    } else {
                        break
                    }
                }
            }
        }
    }
}
