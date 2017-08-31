package algorithms.priorityqueue;

import java.math.BigDecimal;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int BIG_TOP = 1;
	private static final int SMALL_TOP = 0;

	private boolean isFixedLength;
	private int heapType;

	private int currentMaxIndex;
	private AnyType[] items;

	public BinaryHeap() {
		this(DEFAULT_CAPACITY, SMALL_TOP, false);
	}

	public BinaryHeap(int heapType) {
		this(DEFAULT_CAPACITY, heapType, false);
	}

	public BinaryHeap(int capacity, int heapType, boolean isFixedLength) {
		items = (AnyType[]) new Comparable[capacity + 1];
		currentMaxIndex = 0;
		this.heapType = heapType;
		this.isFixedLength = isFixedLength;
	}

	public BinaryHeap(AnyType[] items) {
		this(items, SMALL_TOP, false);
	}

	public BinaryHeap(AnyType[] items, int heapType, boolean isFixedLength) {
		currentMaxIndex = items.length;
		this.items = (AnyType[]) new Comparable[currentMaxIndex + 1];
		this.heapType = heapType;
		this.isFixedLength = isFixedLength;

		for (int i = 0; i < currentMaxIndex; i++) {
			this.items[i + 1] = items[i];
		}

		buildHeap();
	}

	public void buildHeap() {
		for (int i = currentMaxIndex / 2; i >= 0; i--) {
			percolateDown(i);
		}
	}

	public void percolateDown(int hole) {
		AnyType item = items[hole];
		while (hole <= currentMaxIndex / 2) {
			int child = hole * 2;

			if (heapType == SMALL_TOP) {
				if (hole * 2 < currentMaxIndex && items[child + 1].compareTo(items[child]) < 0) {
					child++;
				}

				if (items[child].compareTo(item) < 0) {
					items[hole] = items[child];
					hole = child;
				}else{
					break;
				}
			} else {
				if (hole * 2 < currentMaxIndex && items[child + 1].compareTo(items[child]) > 0) {
					child++;
				}

				if (items[child].compareTo(item) > 0) {
					items[hole] = items[child];
					hole = child;
				}else{
					break;
				}
			}
		}
		items[hole] = item;
	}

	public AnyType poll() {
		if (this.isEmpty())
			throw new RuntimeException();

		AnyType top = items[1];
		// hole处必须保证是一个剩余堆中的有效值，如果没有这条语句hole中是一个已经弹出的过期值。
		items[1] = items[currentMaxIndex--];
		percolateDown(1);
		return top;
	}

	public boolean isEmpty() {
		return currentMaxIndex == 0;
	}

	public void insert(AnyType item) {
		if (isFixedLength == true && currentMaxIndex == items.length - 1) {
			if(heapType == SMALL_TOP && item.compareTo(peek()) > 0 || heapType == BIG_TOP && item.compareTo(peek()) < 0){
				items[1] = item;
				percolateDown(1);
			}
			return;
		}
		if (currentMaxIndex == items.length - 1) {
			enlargeArray();
		}

		items[++currentMaxIndex] = item;
		percolateUp(currentMaxIndex);
	}

	public AnyType peek() {
		return (currentMaxIndex == 0) ? null : items[1];
	}

	public void percolateUp(int hole) {
		AnyType item = items[hole];
		if (hole == 1)
			return;
		if (heapType == SMALL_TOP) {
			while (hole > 1 && item.compareTo(items[hole / 2]) < 0) {
				items[hole] = items[hole / 2];
				hole = hole / 2;
			}
			items[hole] = item;
		} else {
			while (hole > 1 && item.compareTo(items[hole / 2]) > 0) {
				items[hole] = items[hole / 2];
				hole = hole / 2;
			}
			items[hole] = item;
		}
	}

	public void enlargeArray() {
		int newSize = items.length * 2 + 1;
		AnyType[] old = items;
		items = (AnyType[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++) {
			items[i] = old[i];
		}
	}

	public static void main(String[] args) {
		int[] array = { 1, 3, 7, 8, 2, 4, 6, 5, 9, 0 };
		BinaryHeap<Integer> heap = new BinaryHeap<>(10, BinaryHeap.BIG_TOP, true);
		for (int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
		System.out.println(heap.peek());
	}
}
