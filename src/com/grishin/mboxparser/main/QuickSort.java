package com.grishin.mboxparser.main;

import java.util.ArrayList;

public class QuickSort  {
	  private ArrayList<Integer> numbers;
	  private ArrayList<String> addrs;
	  private int number;

	  public void sort(ArrayList<Integer> values, ArrayList<String> addresses) {
	    // check for empty or null array
	    if (values ==null || values.size()==0){
	      return;
	    }
	    this.numbers = values;
	    this.addrs = addresses;
	    number = values.size();
	    quicksort(0, number - 1);
	  }

	  private void quicksort(int low, int high) {
	    int i = low, j = high;
	    int pivot = numbers.get(low + (high-low)/2);
	    
	    while (i <= j) {
	      while (numbers.get(i) < pivot) {
	        i++;
	      }
	      while (numbers.get(j) > pivot) {
	        j--;
	      }
	      
	      if (i <= j) {
	        exchange(i, j);
	        i++;
	        j--;
	      }
	    }
	     
	    if (low < j)
	      quicksort(low, j);
	    if (i < high)
	      quicksort(i, high);
	  }

	  private void exchange(int i, int j) {
	    int temp = numbers.get(i);
	    String stemp = addrs.get(i);
	    int jtemp = numbers.get(j);
	    String jstemp = addrs.get(j);
    	numbers.set(i, jtemp);
    	addrs.set(i,jstemp);
	    numbers.set(j, temp);
	    addrs.set(j,  stemp);
	  }
	} 
