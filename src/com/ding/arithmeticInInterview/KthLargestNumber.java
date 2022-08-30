package com.ding.arithmeticInInterview;

public class KthLargestNumber {
    /**
     *
     * @param array 待调整的堆
     * @param k 第几大
     */
    public static int findKthLargestNumber(int[] array, int k){
        buildHeap(array, k);
        for (int i = k; i < array.length; i++){
            if (array[i] > array[0]){
                array[0] = array[i];
                downAdJust(array, 0, k);
            }
        }
        return array[0];
    }

    /**
     *
     * @param array 待调整的堆
     * @param length 堆的有效大小
     */
    private static void buildHeap(int[] array, int length) {
        for (int i = (length - 2) / 2; i >= 0; i--){
            downAdJust(array, i, length);
        }
    }

    /**
     *
     * @param array 带调整的堆
     * @param index 要下沉的节点
     * @param length 堆的有效大小
     */
    private static void downAdJust(int[] array, int index, int length) {
        int temp = array[index];
        int childIndex = (2 * index) + 1;
        while (childIndex < length){
            if (((childIndex + 1) < length) && (array[childIndex + 1] < array[childIndex])){
                childIndex++;
            }
            if (temp <= array[childIndex]){
                break;
            }
            array[index] = array[childIndex];
            index = childIndex;
            childIndex = (2 * childIndex) + 1;
        }
        array[index] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[] {7, 5, 15, 3, 17, 2, 20, 24, 1, 9, 12, 8};
        System.out.println(findKthLargestNumber(array, 5));
    }
}
