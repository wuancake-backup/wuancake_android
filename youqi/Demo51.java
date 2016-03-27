public class Demo51 {
    public static <E> void printArray(E[] inputArray){
    	for(E element : inputArray){
    		System.out.printf("%s ",element);
    	}
    	System.out.println();
    }
    public static void main(String args[]){
    	Integer[] intArray={ 1, 2, 3, 4, 5};
    	Double[] doubleArray={ 1.1, 2.2, 3.3, 4.4};
    	Character[] charArray={ 'H', 'E', 'L', 'L', 'O'};
    	System.out.println("\nArray doubleArray contains:");
    	printArray( intArray  );
    	System.out.println("\nArray doubleArray contains:");
    	printArray( doubleArray  );
    	System.out.println("\nArray characterArray contains:");
    	printArray( charArray  );
    }
}
