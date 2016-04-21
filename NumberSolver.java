package kevOOP;

import java.util.ArrayList;
import java.util.Arrays;


//Solution class, stores text of calculations
 class Solution{
	
	int result;
	ArrayList<String> solutionLines;
	
	public Solution() {
		solutionLines=new ArrayList<String> ();
		result=0;
	}
	
	public Solution(String line,int res){
		result=res;
		solutionLines=new ArrayList<String> ();
		solutionLines.add(line);
	}

	
	public Solution(Solution parent,String line, int res){
		solutionLines=new ArrayList<String> ();
		solutionLines.addAll(parent.solutionLines);
		solutionLines.add(line);
		result=res;
	}
	
	
	
	


	public String getSolution(){
		String sol="Possible Solution: \n";
		for(String s:solutionLines)
			sol+=s+"\n";
		return sol;
	}


	
}

public class NumberSolver {
	int[] numbers;
	int target;
	ArrayList<Solution> solutionList;
	Solution finalSolution;
	Solution aiSolution1,aiSolution2,aiSolution3;
	int aiSkill;
	int bestResult;
	boolean solutionFound;
	
	enum operation{
		ADD,SUBTRACT,MULTIPLY,DIVIDE;
	}
	
	
	
	public NumberSolver(int[] roundNumbers,int targetNumber){
		numbers=sortArray(roundNumbers);
		target=targetNumber;
		bestResult=0;
		solutionList=new  ArrayList<Solution>();
		finalSolution=new Solution();
		aiSolution1=new Solution();
		aiSolution2=new Solution();
		
		ArrayList<int[]>numList=new ArrayList<int[]>();
		numList.add(numbers);
		findSolutions(numList,solutionList);
		for(String s:finalSolution.solutionLines){
			System.out.println(s);
		}
		
		
		
	}
	
	public String getSolution(){
		return finalSolution.getSolution();
	}
	
//recursive function to search for solutions
	//take in arraylists of parent number arrays and Solution objects
private void findSolutions(ArrayList<int[]> numList,
						   ArrayList<Solution>parentSolutions){
	
		//new arraylist which will be fed back in at end of method
		ArrayList<int[]>updatedNums=new ArrayList<int[]>();
		ArrayList<Solution> childSolutions=new ArrayList<Solution>();
		
		//loop through arraylist of parent number arrays
		for(int index=0; index<numList.size(); index++){
			//set current array
			int[] nums=numList.get(index);
			
			//loop through four opeations + - * /
			for(operation op:operation.values()){
				
				//loop through all pairs of number un array
				for(int[]  pair: getPairs(nums)){

					//get result of current operation/pair
					int result=operationResult(pair,op);
					
					
					//abandon if invalid operation
					if(result!=0){
						
						//add updated array to child arraylist
						updatedNums.add(updateArray(nums,pair,op));
						
						//set text of current line
						String line=getSolutionLine(pair, result,op);
						
						//if solution has a parent, use it to create child with new line added
						if(parentSolutions.size()>0){
							childSolutions.add(new Solution(parentSolutions.get(index) ,line, result));
						//otherwise start new solution
						}else{
							childSolutions.add(new Solution(line,result));
						}
								                              
						int x=childSolutions.size()-1;
						if(bestAnswer(result)&&x>=0){//avoids error on first pass
							
							//if current answer is best, set it as final solution for now
							finalSolution=childSolutions.get(x);
						}
						
							//solution is found, stop searching
						if(result==target){
							solutionFound=true;
							return;
						}

					}
				}
			}
		}

		
			if(updatedNums.get(0).length==3){
				aiSolution1=finalSolution;
			}
			if(updatedNums.get(0).length==2){
				aiSolution2=finalSolution;
			}
		
		//if there are still 2 or more numbers in array,feed back to start of method
		if(updatedNums.get(0).length>1){
			findSolutions(updatedNums,childSolutions);
		}
		

	}
	
	//Sort array in descending order(first sort ascending, then reverse)
	private int[] sortArray(int []_nums){
		int[]nums=_nums;
		Arrays.sort(nums);
		for(int i = 0; i < nums.length / 2; i++)
		{
		    int temp = nums[i];
		    nums[i] = nums[nums.length - i - 1];
		    nums[nums.length - i - 1] = temp;
		}
		return nums;
	}
	//Return 2d array of all combinations of 2 numbers from array
	public int[][] getPairs(int[] _nums){
		int[] nums=_nums;
		int size=nums.length*(nums.length-1)/2;
		int[][]pairs =new int[size][2];

		int count=0;
		for(int i=0; i<nums.length; i++){
			for(int j=1; j<nums.length; j++){
				if(j>i){
					pairs[count][0]=nums[i];
					pairs[count][1]=nums[j];
					
					count++;
				}
			}
		}

		return pairs;

	}
	
	//Remove 2 numbers from array and add result of operation on them, return updated array
	private int[] updateArray(int[]nums,int[]pair,operation operation){
		int [] updated = new int[nums.length-1];
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		for(int num:nums){
			
			list.add(num);
		}
		int result=operationResult(pair,operation);
		list.add(result);
		list.remove(Integer.valueOf(pair[0]));
		list.remove(Integer.valueOf(pair[1]));
		int i=0;
		for(int num:list){
			updated[i++]=num;
		}
		updated=sortArray(updated);
		
		return updated;
	}


		
	
	//Check if answer is closer to target than current best
	private boolean bestAnswer(int ans){
		boolean best=false;
		if(Math.abs(target-ans)<=Math.abs(target-bestResult)){
			best=true;
			bestResult=ans;
			
		}
		
		return best;
	}

	
	
	//Take a pair of numbers, perform required operation and return the result
	private int operationResult(int[] pair,operation op){
		int result=0;
		int a= pair[0];
		int b=pair[1];
		switch(op){
			case ADD:
				result=a+b;
				
				return result;
			case SUBTRACT:
				if(a>b){
					result=a-b;
					
					return result;
				}else{
					result=0;
					return result;
				}
					
			case MULTIPLY:
				if(a!=1 &&b!=1){
					result=a*b;
					
					return result;
				}else{
					result=0;
					return result;
				}
			case DIVIDE:
				if(a%b==0&& b!=1){
					result=a/b;
					
					return result;
				}else{
					result=0;
					return result;
				}
		}
		
		return 0;
		
	}
	
	private String getSolutionLine(int[]pair, int result, operation op){
		int a=pair[0];
		int b=pair[1];
		
		switch(op){
		case ADD:
			
		return (a+" + "+b+" = "+result);
		case SUBTRACT:
		
		return (a+" - "+b+" = "+result);
		
		case MULTIPLY:
		return (a+" x "+b+" = "+result);

		case DIVIDE:
		
		return (a+" / "+b+" = "+result);
		
		}
		
		return "";
	}
	
}