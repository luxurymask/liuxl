package Utils;

@Deprecated
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * 目前最大索引，从0开始。数据从1开始存，0号位置作为缓存，以便hole／2取父节点。
	 */
	private int currentMaxIndex;
	private AnyType[] items;
	
	public BinaryHeap(){
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity){
		currentMaxIndex = 0;
		items = (AnyType[]) new Comparable[capacity + 1];
	}
	
	public BinaryHeap(AnyType[] items){
		currentMaxIndex = items.length;
		//TODO why?
		this.items = (AnyType[]) new Comparable[(currentMaxIndex + 2) * 11 / 10];
		int i = 1;
		for(AnyType item : items){
			this.items[i++] = item;
		}
		buildHeap();
	}
	
	public void insert(AnyType item){
		if(currentMaxIndex == items.length - 1){
			enlargeArray(items.length * 2 + 1);
		}
		
		int hole = ++currentMaxIndex;
		//TODO 插入第一个元素的时候，hole为1，item和items[0]比较，但是item[0]并没有初始化，这是个问题。
		for(;item.compareTo(items[hole/2]) < 0;hole /= 2){
			items[hole] = items[hole / 2];
		}
		items[hole] = item;
	}
	
	public boolean isEmpty(){
		return currentMaxIndex == 0;
	}
	
	public void makeEmpty(){
		//简单粗暴...
		currentMaxIndex = 0;
	}

	/**
	 * 从乱序items数组构建堆，线性时间复杂度。
	 */
	private void buildHeap(){
		for(int i = currentMaxIndex / 2;i > 0;i--){
			percolateDown(i);
		}
	}
	
	/**
	 * 删除最小值。
	 * @return 被删除的原堆最小值（堆顶）。
	 */
	public AnyType deleteMin(){
		if(isEmpty()){
			throw new RuntimeException();
		}
		
		AnyType minItem = findMin();
		items[1] = items[currentMaxIndex--];
		percolateDown(1);
		return minItem;
	}
	
	private void percolateDown(int hole){
		int child;
		AnyType tmp = items[hole];
		//hole的移动不能用hole *= 2, 因为hole *= 2取的是左孩子，而child可能是右孩子，所以要用hole = child来移动hole。
		for(;hole * 2 <= currentMaxIndex;hole = child){
			child = hole * 2;
			//取孩子中较小的一个。child != currentMaxIndex为了避免hole只有一个左孩子而陷入空指针异常。
			if(child != currentMaxIndex && items[child + 1].compareTo(items[child]) < 0){
				child++;
			}
			if(items[child].compareTo(tmp) < 0){
				items[hole] = items[child];
			}else{
				break;
			}
		}
		items[hole] = tmp;
	}
	
	public AnyType findMin(){
		if(isEmpty()){
			throw new RuntimeException();
		}
		return items[1];
	}
	
	private void enlargeArray(int newSize){
		AnyType[] old = items;
		items = (AnyType[]) new Comparable[newSize];
		for(int i = 0;i < old.length;i++){
			items[i] = old[i];
		}
	}
	
	public static void main(String[] args){
		BinaryHeap heap = new BinaryHeap();
	}
}
