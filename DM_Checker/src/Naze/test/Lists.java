package Naze.test;

import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public class Lists{

	public static List<Integer> range(final int start, final int end) {

		if(start > end) throw new IllegalArgumentException();

		class RangeAbstractList extends AbstractList<Integer> implements RandomAccess {

			@Override
			public Integer get(int index) {
				//if(index >= size()) throw new IndexOutOfBoundsException();
				return start + index;
			}

			@Override
			public int size() {
				return (end - start) + 1;
			}
		}
		return new RangeAbstractList();
	}

	public static List<Integer> times(final int mult, final List<Integer> list) {

		if(list instanceof RandomAccess) {
			class TimesAbstractList extends AbstractList<Integer> implements RandomAccess {

				@Override
				public Integer get(int index) {
					if(index >= size()) throw new IndexOutOfBoundsException();
					return list.get(index) * mult;
				}

				@Override
				public int size() {
					return list.size();
				}
			}
			return new TimesAbstractList();
		}
		return new AbstractSequentialList<Integer>() {

			@Override
			public ListIterator<Integer> listIterator(int index) {
				final ListIterator<Integer> it = list.listIterator(index);
				return new ListIterator<Integer>() {

					@Override
					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public Integer next() {
						return it.next() * mult;
					}

					@Override
					public boolean hasPrevious() {
						return it.hasPrevious();
					}

					@Override
					public Integer previous() {
						return it.previous() * mult;
					}

					@Override
					public int nextIndex() {
						return it.nextIndex();
					}

					@Override
					public int previousIndex() {
						return it.previousIndex();
					}

					@Override
					public void remove() {
						it.remove();
					}

					@Override
					public void set(Integer e) {
						it.set(e);						
					}

					@Override
					public void add(Integer e) {
						it.add(e);					
					}
					
				};
			}

			@Override
			public int size() {
				return list.size();
			}
		};
	}

}
