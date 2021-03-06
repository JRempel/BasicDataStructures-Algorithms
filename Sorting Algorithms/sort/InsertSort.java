package sort;

import java.util.Comparator;
import java.util.List;

public class InsertSort {
	public static <T extends Comparable<T>> void InsertionSort(T[] input) {
		T temp;
		// Iterate n-1 times, consider a single element to be sorted
		for (int i = 1; i < input.length; i++) {
			temp = input[i];
			// compare with items (0,1,...i-1) and shift
			int j = i;
			while (j > 0 && input[j - 1].compareTo(temp) > 0) {
				input[j] = input[j - 1];
				j--;
			}
			// insert item
			input[j] = temp;
		}
	}

	public static <T extends Comparable<T>> void InsertionSort(List<T> input) {
		T temp;
		// Iterate n-1 times, consider a single element to be sorted
		for (int i = 1; i < input.size(); i++) {
			temp = input.get(i);
			// compare with items (0,1,...i-1) and shift
			int j = i;
			while (j > 0 && input.get(j - 1).compareTo(temp) > 0) {
				input.set(j, input.get(j - 1));
				j--;
			}
			// insert item
			input.set(j, temp);
		}
	}

	public static <T> void InsertionSort(List<T> input, Comparator<? super T> c) {
		T temp;
		// Iterate n-1 times, consider a single element to be sorted
		for (int i = 1; i < input.size(); i++) {
			temp = input.get(i);
			// compare with items (0,1,...i-1) and shift
			int j = i;
			while (j > 0 && c.compare(input.get(j - 1), temp) > 0) {
				input.set(j, input.get(j - 1));
				j--;
			}
			// insert item
			input.set(j, temp);
		}
	}
}
