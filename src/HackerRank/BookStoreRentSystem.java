// Input: n as book store number, 2D array to indicate (storeId, bookId, price)
// Output: a rent system which includes rent(), drop(), search() which returns the cheapest 4 store books with a certain bookId.

Class BookStoreRentSystem {
  Map<int[], int[]> map; // key element 1 as storeId and 2 as bookId, value element 1 as price and 2 for available or not, 0 is not available and 1 available
  int storeNum;

  public BookStoreRentSystem(int n, int[][] entries) {
  	storeNum = n;
  	map = new HashMap<>();
  	for (int[] entry : entries) {
  		map.put(new int[] {entry[0], entry[1]}, new int[] {entry[2], 1}); 
    }
  }

  public void rent(int storeId, int bookId) { // rent book out
	  map.get(new int[] {storeId, bookId})[1] = 0;
  }
  
  public void drop(int storeId, int bookId) { // return book back
	  map.get(new int[] {storeId, bookId})[1] = 1;
  }
  
  public List<Integer> search(int bookId) { // returns the cheapest 4 store books with bookId
	  map<Integer, List<Integer>> priceMap = new HashMap<>(); // key as price, value as list of storeId
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap to hold cheapest 4 price
    List<Integer> storeIds = new ArrayList<>();

	  for (int i = 1; i <= storeNum; i++) {
		  int[] priceAndAvailability = map.get(new int[] {i, bookId});
		  if (priceAndAvailability[1] == 1) { // book is available
			  int price = priceAndAvailability[0];
			  if (priceMap.containsKey(price)) {
				  priceMap.put(price, new ArrayList<Integer>());
        }
			  priceMap.get(price).add(i);

			  if (maxHeap.size() < 4) {
				  maxHeap.offer(price);
        } else if (maxHeap.peek() > price) {
	        maxHeap.poll(); // remove top higher price
	        maxHeap.offer(price); // insert current price
        }
      }
    }
    int size = maxHeap.size();
    int[] cheapestPrice = new int[size];
    for (int i = 0; i < size; i++) { // put cheapest price into sorted order array
  	  cheapestPrice[size - 1 - i] = maxHeap.poll();
    }
    for (int p : cheapestPrice) {
  	  List<Integer> stores = priceMap.get(price);
  	  for (int i = 0; i < stores.size() && storeIds.size() < 4; i++) {
        storeIds.add(stores.get(i));
      }
  	  if (storeIds.size() == 4) { // return after collect 4 stores with cheapest price
  		  return storeIds;
  	  }
    }
    return storeIds; // return if collect less than 4 stores with cheapest price
  }
}
