package Naze.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.RandomAccess;

import org.junit.Assert;
import org.junit.Test;

public class ListsTest {
	@Test(expected=IllegalArgumentException.class)
	public void range1() {
		Lists.range(4, 3);
	}

	@Test
	public void rangeSize() {
		Assert.assertEquals(3, Lists.range(0, 2).size());
		Assert.assertEquals(5, Lists.range(-2, 2).size());
	}

	@Test
	public void rangeIterator() {
		Random random = new Random();
		for(int i=0; i<1000; i++) {
			int start = random.nextInt();
			List<Integer> range = Lists.range(start, start + 99);
			Assert.assertEquals(100, range.size());

			int j = start;
			for(int value: range) {
				Assert.assertEquals(j++, value);
			}
		}
	}

	@Test
	public void rangeIterator2() {
		Random random = new Random();
		for(int i=0; i<1000; i++) {
			int start = random.nextInt();
			List<Integer> range = Lists.range(start, start + 73);
			Iterator<Integer> it = range.listIterator();
			for(int j = start; j <= start + 73; j++) {
				Assert.assertEquals(j, (int)it.next());
			}
			Assert.assertFalse(it.hasNext());
		}
	}

	@Test(expected=NoSuchElementException.class)
	public void rangeIteratorNSEE() {
		Iterator<Integer> it = Lists.range(0, 0).iterator();
		it.next();
		it.next();
	}

	@Test
	public void rangeRandomList() {
		Assert.assertTrue(Lists.range(0, 7) instanceof RandomAccess);
	}

	@Test
	public void times() {
		List<Integer> range = Lists.range(-7, 7);
		ArrayList<Integer> inverse = new ArrayList<>(range);
		Collections.reverse(inverse);
		Assert.assertEquals(inverse, Lists.times(-1, range));
	}

	@Test
	public void times2() {
		Random random = new Random();
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		for(int i=1; i<=10000; i++) {
			int value = random.nextInt();
			list.add(value);
			list2.add(value * 3);
		}

		Assert.assertEquals(list2, Lists.times(3, list));
	}

	@Test(timeout=1000)
	public void slooowTimes() {
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=1; i<=100000; i++) {
			list.add(i);
		}
		long sum = 0;
		for(int value: Lists.times(2, list)) {
			sum += value / 2;
		}
		Assert.assertEquals(100000L * (100001) / 2, sum);
	}

	@Test(timeout=1000)
	public void slooowTimes2() {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=1; i<=100000; i++) {
			list.add(i);
		}
		long sum = 0;
		List<Integer> list2 = Lists.times(3, list);
		for(int i = 0; i< list2.size(); i++) {
			sum += list2.get(i) / 3;
		}
		Assert.assertEquals(100000L * (100001) / 2, sum);
	}
}