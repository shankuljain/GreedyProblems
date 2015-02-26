
import java.io.*;
import java.util.*;

class MinimizedWeightedSum{
	public static void main(String [] args) throws IOException{
		loadData();
		quickSort(weight,0,nJobs-1);
		
		createDifferenceList();
		Arrays.sort(difference);
		
		createRatioList();
		Arrays.sort(ratio);
		
		computeDifferenceAlgo();
		computeRatioAlgo();
	}
	
	private static void loadData() throws IOException{
		Scanner s = new Scanner(new FileReader("jobs.txt"));
		nJobs = s.nextInt();
		weight = new int[nJobs];
		length = new int[nJobs];
		
		
		//System.out.println(nJobs);
		for(int i =0;i<nJobs;i++){
			s.nextLine();
			int w = s.nextInt();
			int l = s.nextInt();
			weight[i] = w;
			length[i] = l;
			//System.out.println(weight[i]+ "  "+ length[i]);
		
		}
	}
	
	
	private static void quickSort(int[] array,int l, int r){
		if(l<r){
			int i = partitionArray(array,l,r); // returns the correct position of chosen pivot
			quickSort(array,l,i-1);
			quickSort(array,i+1,r);
		}
	}
	
	private static int partitionArray(int[] array, int l, int r){
		
		/* random selection of pivot */
		//int pivotIndex = rgen.nextInt(l,r);
		
		/**
		 * below commented out portion is even much more cooler
		 * it takes three random values and choses the median as pivot.
		 * better running time for larger inputs.
		 */
		
		/*
		int x = rgen.nextInt(l,r);
		int y = rgen.nextInt(l,r);
		int z = rgen.nextInt(l,r);
		int first = array[x];
		int last = array[z];
		int middle = array[y]; // be aware to add l
		
		if(first<last && first< middle){
			if(middle<last){
				pivotIndex = y;
			}
			else{
				pivotIndex = z;
			}
		}else if(first>last && first > middle){
			if(middle>last){
				pivotIndex = y;
			}
			else{
				pivotIndex = z;
			}
		}
		else{
			pivotIndex = x;
		}*/
		
		
		/* swapping pivot with first element of the array */
		//swap(array,l,pivotIndex);
		
		int i = l+1;
		for(int j=l+1;j<=r;j++){
			if(array[j] <= array[l]){
				swap(length,j,i);
				swap(array,j,i++);
			}
		}
		swap(array,l,--i);
		swap(length,l,i);
		return i;
	}
	
	private static void swap(int[] array, int j, int i){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
	
	private static void createDifferenceList(){
		difference = new int[nJobs];
		for(int i=0;i<nJobs;i++){
			//System.out.println(weight[i] +"  "+  length[i]);
			difference[i] = weight[i]-length[i];
			differenceList.add(weight[i]-length[i]);
		}
		
		//for(int i =0 ;i<nJobs;i++){
		//	System.out.println(difference[i]);
		//}
	}
	
	private static void createRatioList(){
		ratio = new double[nJobs];
		for(int i=0;i<nJobs;i++){
			//System.out.println(weight[i] +"  "+  length[i]);
			ratio[i] = ((double) weight[i])/length[i];
			ratioList.add(((double) weight[i])/length[i]);
		}
	}
	
	private static void computeDifferenceAlgo(){
		long weightedSum = 0;
		int totalTime = 0;
		for(int i = nJobs-1;i>=0;i--){
			int index = differenceList.lastIndexOf(difference[i]);
			totalTime += length[index];
			weightedSum += weight[index]*totalTime;
			differenceList.set(index, null);  //don't forget that difference can't be 0
		}
		System.out.println(weightedSum);
		System.out.println(totalTime);
	}
	
	private static void computeRatioAlgo(){
		long weightedSum = 0;
		int totalTime = 0;
		for(int i = nJobs-1;i>=0;i--){
			int index = ratioList.lastIndexOf(ratio[i]);
			totalTime += length[index];
			weightedSum += weight[index]*totalTime;
			ratioList.set(index,null);
		}
		System.out.println(weightedSum);
		System.out.println(totalTime);
	}
	
	
	private static ArrayList<Double> ratioList = new ArrayList<Double>();
	private static double[] ratio;
	private static ArrayList<Integer> differenceList = new ArrayList<Integer>();
	private static int[] difference;
	private static int nJobs;
	private static int[] weight;
	private static int[] length;
}