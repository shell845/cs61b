import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author YC 02/23/2020
 * Practice sorting
 */

public class Sort {
    /** Bubble sort */
    public void bubbleSort(int[] input) {
        // ascending
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - 1 - i; j++) {
                if (input[j] > input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
        for (int i:input) {
            System.out.print(i + " ");
        }

        System.out.println();

        // descending
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - i - 1; j++) {
                if (input[j] < input[j + 1]) {
                    int temp = input[j];
                    input[j] = input[j + 1];
                    input[j + 1] = temp;
                }
            }
        }
        for (int i:input) {
            System.out.print(i + " ");
        }
    }

    /** Selection sort */
    public void selectionSort(int[] input) {
        // ascending
        for (int i = 0; i < input.length; i++) {
            int min = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] < input[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = input[i];
                input[i] = input[min];
                input[min] = temp;
            }
        }
        for (int i:input) {
            System.out.print(i + " ");
        }
        System.out.println();

        // descending
        for (int i = 0; i < input.length; i++) {
            int max = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j] > input[max]) {
                    max = j;
                }
            }
            if (max != i) {
                int temp = input[i];
                input[i] = input[max];
                input[max] = temp;
            }
        }
        for (int i:input) {
            System.out.print(i + " ");
        }
    }

    /** Heap sort */
    public void heapSort(int[] input) {
        // ascending
        PriorityQueue<Integer> pq = new PriorityQueue<>(input.length);
        for (int i:input) {
            pq.add(i);
        }
        for (int i = 0; i < input.length; i++) {
            input[i] = pq.poll();
        }

        for (int i:input) {
            System.out.print(i + " ");
        }

        System.out.println();
        // descending
        Comparator<Integer> cmp = (a, b) -> (b - a);
        pq = new PriorityQueue<>(input.length, cmp);
        for (int i:input) {
            pq.add(i);
        }
        for (int i = 0; i < input.length; i++) {
            input[i] = pq.poll();
        }

        for (int i:input) {
            System.out.print(i + " ");
        }
    }

    /** In place heap sort */
    public void inPlaceHeapSort(int[] input) {
        // ascending
        for (int i = input.length - 1; i > 0; i--) {
            heapifyMax(input, i);
            int temp = input[0];
            input[0] = input[i];
            input[i] = temp;
        }

        System.out.println("ascending");
        for (int i:input) {
            System.out.print(i + " ");
        }
        System.out.println();

        // descending
        for (int i = input.length - 1; i > 0; i--) {
            heapifyMin(input, i);
            int temp = input[0];
            input[0] = input[i];
            input[i] = temp;
        }

        System.out.println("descending");
        for (int i:input) {
            System.out.print(i + " ");
        }

    }

    // Heapify to min heap
    private void heapifyMin(int[] input, int last) {
        for (int i = last; i > 0; i--) {
            if (input[parent(i)] > input[i]) {
                int temp = input[i];
                input[i] = input[parent(i)];
                input[parent(i)] = temp;
            }
        }
    }

    // Heapify to max heap
    private void heapifyMax(int[] input, int last) {
        for (int i = last; i > 0; i--) {
            if (input[parent(i)] < input[i]) {
                int temp = input[i];
                input[i] = input[parent(i)];
                input[parent(i)] = temp;
            }
        }
    }


    private int leftChild(int i) {
        return (i + 1) * 2 - 1;
    }

    private int rightChild(int i) {
        return (i + 1) * 2;
    }

    private int parent(int i) {
        return (int) (i - 1) / 2;
    }

    /** Merge sort */
    public void mergeSort(int[] input) {
        // ascending
        int size = input.length;

        mSort(input, 0, size - 1);

        for (int i:input) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private void mSort(int[] in, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mSort(in, l, mid);
            mSort(in, mid + 1, r);
            mMerge(in, l, mid, r);
        }
    }

    private void mMerge(int[] in, int l, int m, int r) {
        int sizeL = m - l + 1;
        int sizeR = r - m;
        int[] left = new int[sizeL];
        int[] right = new int[sizeR];
        System.arraycopy(in, l, left, 0, sizeL);
        System.arraycopy(in, m + 1, right, 0, sizeR);

        int il = 0;
        int ir = 0;
        int i = l;
        while (il < sizeL && ir < sizeR) {
            if (left[il] < right[ir]) {
                in[i] = left[il];
                il++;
                i++;
            } else {
                in[i] = right[ir];
                ir++;
                i++;
            }
        }
        while (il < sizeL) {
            in[i] = left[il];
            il++;
            i++;
        }
        while (ir < sizeR) {
            in[i] = right[ir];
            ir++;
            i++;
        }
    }

    /** Insertion sort */
    public void insSort(int[] input) {
        // ascending
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
            int j = i;
            while (j > 0) {
                if (output[j] < output[j - 1]) {
                    int temp = output[j];
                    output[j] = output[j - 1];
                    output[j - 1] = temp;
                    j--;
                } else {
                    break;
                }
            }
        }
        for (int i:output) {
            System.out.print(i + " ");
        }
        System.out.println();

        // descending
        int[] output2 = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output2[i] = input[i];
            int j = i;
            while (j > 0) {
                if (output2[j] > output2[j - 1]) {
                    int temp = output2[j];
                    output2[j] = output2[j - 1];
                    output2[j - 1] = temp;
                    j--;
                } else {
                    break;
                }
            }
        }
        for (int i:output2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /** In place insertion sort */
    public void inPlcInsSort(int[] input) {
        // ascending
        for (int i = 0; i < input.length; i++) {
            int j = i;
            while (j > 0) {
                if (input[j] < input[j - 1]) {
                    int temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                    j--;
                } else {
                    break;
                }
            }
        }
        for (int i:input) {
            System.out.print(i + " ");
        }
        System.out.println();

        // descending
        for (int i = 0; i < input.length; i++) {
            int j = i;
            while (j > 0) {
                if (input[j] > input[j - 1]) {
                    int temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                    j--;
                } else {
                    break;
                }
            }
        }
        for (int i:input) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /** Quick sort */
    public void qSort(int[] input) {
        // ascending, take last element as pivot
        partition(input, 0, input.length - 1);
        for (int i:input) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private void partition(int[] input, int left, int right) {
        int j = left;
        for (int i = left; i < right; i++) {
            if (input[i] < input[right]) {
                swap(input, i, j);
                j++;
            }
        }

        swap(input, right, j);

        if (left < j - 1) {
            partition(input, left, j - 1);
        }
        if (j + 1 < right) {
            partition(input, j + 1, right);
        }
    }

    private void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

    public static void main(String[] args) {
        int[] sort = {41, 19, 32, 17, 15, 26, 2, 17, 17};
        Sort test = new Sort();
        test.qSort(sort);
    }
}
