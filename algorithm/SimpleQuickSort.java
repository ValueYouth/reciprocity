package com.romfeed.algorithmdemo;

/**
 * 类说明：简单快速排序算法
 * @author cwang6
 * @since 2017-2-7
 * @version 1.0
 */
public class SimpleQuickSort {

	/**
	 * 方法说明：测试方法，输出排序后的数组。
	 * @param args 预设参数
	 */
	public static void main(String[] args) {
		int[] numbers = {25, 25, 36, 95, 12, 68, 101};
		SimpleQuickSort simpleQuickSort = new SimpleQuickSort();
		
		simpleQuickSort.quickSort(numbers, 0, numbers.length - 1);
		for (int number : numbers){
			System.out.print(number + " ");
		}
	}
	
	/**
	 * 方法说明：当{@code left}小于{@code right}时，通过分类方法，得到基准位的
	 * 准确位置，并将其位置{@code pivot}返回。然后，再通过递归调用方法{@code quickSort}
	 * 执行上述过程。
	 * @param numbers 源数组
	 * @param left 首部索引
	 * @param right 尾部索引
	 */
	private void quickSort(int[] numbers, int left, int right) {
		if (left < right){
			int pivot = partition(numbers, left, right);
			quickSort(numbers, left, pivot - 1);
			quickSort(numbers, pivot + 1, right);
		}
	}
	
	
	/**
	 * 方法说明：将比基准位数字{@code pivotNumber}大的数字，都移动到{@code pivotNumber}
	 * 的右边，同时将比基准位数字{@code pivotNumber}小的数字，都移动到{@code pivotNumber}
	 * 的左边。最后，将基准位的数字放置到正确的位置。
	 * @param numbers 源数组
	 * @param left 首部索引，一般是基准位置。
	 * @param right 尾部索引
	 * @return 基准数字所在的位置
	 */
	private int partition(int[] numbers, int left, int right) {
		int pivotNumber = numbers[left]; // 将数组第一位作为基准
		
		while (left < right) {
			while (left < right && numbers[right] > pivotNumber) {
				right--;
			}
			numbers[left] = numbers[right]; // 移动小于基准的记录到左边
			
			while (left < right && numbers[left] < pivotNumber) {
				left++;
			}
			numbers[right] = numbers[left]; // 移动大于基准的记录到右边
		}
		
		numbers[left] = pivotNumber; // 移动基准位记录到正确位置
		return left;
	}
	
}
