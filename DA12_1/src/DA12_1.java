import java.util.Comparator;
import java.util.PriorityQueue;

public class DA12_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PriorityQueue<Integer> que = new PriorityQueue<Integer>(1, new Mycomp());
		int[] a = { 4, 2, 5, 7, 2, 1, 3, 10 };

		for (int i = 0; i < a.length; i++) {
			que.add(a[i]);
		}

		int[] b = new int[a.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = (int) que.poll();
		}
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}

}

class Mycomp implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO 自動生成されたメソッド・スタブ
		int i = (Integer) o1;
		int j = (Integer) o2;

		if (i > j) {
			return 1;
		} else if (i < j) {
			return -1;
		} else {
			return 0;
		}
	}

}

class Edge {
	int u, v, w;

	Edge(int u_, int v_, int w_) {
		u_ = u;
		v_ = v;
		w_ = w;
	}

}