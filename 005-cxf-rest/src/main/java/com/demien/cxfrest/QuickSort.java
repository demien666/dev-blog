package com.demien.cxfrest;

import java.util.Arrays;

public class QuickSort {
	public static void main(String args[]) {
		int[] a=new int[]{5,7,0,6,3,5,8,1,3,2,5,7,4,6,9,4,6,3,7,5,3,7,8,2,4,6,9,1};
		//int[] a=new int[]{2,3,1,3,1,2};
		new QuickSort().sort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));

	}

	public void sort(int[] a, int l, int k) {
		if (l == k) {
			return;
		}
		if (l == k - 1) {
			if (a[l]>a[k])	swap(a, l, k);
			return;
		}

		int x = a[l+ (k - l) / 2];
		int i = l;
		int j = k;

		while (i < j) {
			while (a[i] < x && i<j)
				i++;
			while (a[j] > x && j>i)
				j--;

			if (i < j) {
				swap(a, i, j);
				if (i+1==j) break;
				if (j>i) {
					i++;
				    j--;
				}
			}

		}
		
		sort(a, l, i);
		sort(a, j, k);

	}

	public void swap(int[] array, int a, int b) {
		int v = array[a];
		array[a] = array[b];
		array[b] = v;
	}

}
