package iterators;

import java.util.List;

public class ExtendedIteratorCities implements ExtendedIterator{
	
	private List<String> departList;
	private int currentIndex;
	
	public ExtendedIteratorCities(List<String> cityList) {
		this.departList = cityList;
	}
	
	@Override
	public Object previous() {
		return departList.get(currentIndex--);
	}
	
	public Object next() {
		return departList.get(currentIndex++);
	}
	
	@Override
	public boolean hasPrevious() {
		return currentIndex >= 0;
	}
	
	public boolean hasNext() {
		return currentIndex <= (departList.size()-1);
	}
	
	@Override
	public void goFirst() {
		currentIndex = 0;
	}
	
	@Override
	public void goLast() {
		currentIndex = departList.size()-1;
	}

}
