package iterators;

import businessLogic.*;
import factories.*;

public class Main {

	public static void main(String[] args) {
		BLFacade	blFacade =	new LocalBLFactory().createBLFacade();
		ExtendedIterator<String> i = blFacade.getDepartingCitiesIterator();
		
		String c;
		System.out.println("_____________________");
		System.out.println("FROM	LAST	TO	FIRST");
		i.goLast();
		while (i.hasPrevious())	{
			c =	i.previous();
			System.out.println(c);
		}
		System.out.println();
		System.out.println("_____________________");
		System.out.println("FROM	FIRST	TO	LAST");
		i.goFirst();
		while (i.hasNext())	{
			c =	i.next();
			System.out.println(c);
		}
	}

}
