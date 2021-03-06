import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Search Utilities using recursion
 */
public class SearchUtils <E extends Comparable<E>> {

    private List<E> tmpSort = null;
    private int tmpSortSize = 0;

    public boolean binarySearch(List<Integer> l, int query) {
        if (l.size() == 0) {
            return false;
        } else {
            return binarySearch(l, query, 0, l.size() - 1);
        }
    }

    private boolean binarySearch(List<Integer> list, int query, int low, int high) {
        if (low > high) { return false; }
        int half = (low + high) / 2;
        if (list.get(half) == query) { return true; }
        else if (list.get(half) < query) {
            return binarySearch(list, query, half + 1, high);
        } else {
            return binarySearch(list, query, low, half - 1);
        }
    }

    public List<E> quickSort (List<E> toSort) {
        if (toSort.size() <= 1) {
            return toSort;
        }

        // make a temporary copy of the list to sort
        tmpSort = new ArrayList<>();
        tmpSort.addAll(toSort);
        tmpSortSize = tmpSort.size();

        quickSort(0, tmpSortSize - 1);

        // make a new list to return
        List<E> result = new ArrayList<>();
        result.addAll(tmpSort);

        // clear the temp copy
        tmpSort = null;

        return result;
    }

    private void quickSort (int low, int high) {
        int i = low, j = high;
        E pivot = tmpSort.get(low + (high - low) / 2);

        while (i <= j) {
            while (tmpSort.get(i).compareTo(pivot) < 0) {
                i++;
            }
            while (tmpSort.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                Collections.swap(tmpSort, i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(low, j);
        }
        if (i < high) {
            quickSort(i, high);
        }
    }

    public List<E> mergeSort (List<E> toSort) {
        if (toSort.size() <= 1) {
            return toSort;
        }

        int half = toSort.size() / 2;

        List<E> left = mergeSort(toSort.subList(0, half));
        List<E> right = mergeSort(toSort.subList(half, toSort.size()));

        return merge(mergeSort(left), mergeSort(right));
    }

    private List<E> merge (List<E> a, List<E> b) {
        List<E> result = new ArrayList<>();

        int aSize = a.size(), bSize = b.size(), i = 0, j = 0;

        // compare the lists and add the smaller item to the result
        while (i < aSize && j < bSize) {
            if (a.get(i).compareTo(b.get(j)) < 0) {
                result.add(a.get(i));
                i++;
            } else {
                result.add(b.get(j));
                j++;
            }
        }

        // list a has been completely checked, add remainder of list b to result
        if (i == aSize) {
            for (int k = j; k < bSize; k++) {
                result.add(b.get(k));
            }
        } else if (j == bSize) {
            // list b has been checked so add any remaining items in list a to result
            for (int l = i; l < aSize; l++) {
                result.add(a.get(l));
            }
        }

        return result;
    }


}
