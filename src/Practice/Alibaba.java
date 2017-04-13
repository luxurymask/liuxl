package Practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Alibaba {

	public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        int maxQps= Integer.valueOf(in.nextLine());
		final String[] rtList = in.nextLine().split(",");
		final int requestNum = Integer.valueOf(in.nextLine());
		final int threadNum = Integer.valueOf(in.nextLine());
		System.out.println(doneTime(maxQps, rtList, requestNum, threadNum));
	}
	/**
	 * 如果使用最优的最大吞吐量负载均衡算法，按照最优模型多久能够处理完所有请求，单位毫秒。
	 * @return
	 */
	static long doneTime(int maxQps,String[] rtList,int requestNum,int threadNum) {
		//TODO
		int count = rtList.length;
		List<Integer> rtListInt = new ArrayList<Integer>();
		Integer[] left = (Integer[]) rtListInt.toArray();
		for(int i = 0;i < count;i++){
			rtListInt.add(Integer.parseInt(rtList[i]));
		}
		Collections.sort(rtListInt);
		int clock = 0;
		while(requestNum > 0){
			clock++;
			for(int i : left){
				if(left[i] == 0){
					left[i] = rtListInt.get(i);
					requestNum -= maxQps;
				}else{
					left[i]--;
				}
			}
		}
		
		int max = 0;
		for(Integer i : left){
			if(left[i] > max){
				max = left[i];
			}
		}
		return clock + max;
	}
}