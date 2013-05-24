import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge> {
	int vi;
	int vj;
	int w;

	Edge(int vi, int vj, int w) {
		this.vi = vi;
		this.vj = vj;
		this.w = w;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO 自動生成されたメソッド・スタブ
		return this.w - o.w;
	}

}

class UnionFind {
	private ArrayList<Integer> parents = new ArrayList<Integer>();
	private ArrayList<Integer> rank = new ArrayList<Integer>();

	UnionFind(int n) {
		for (int i = 0; i < n; i++) {
			parents.add(i);
			rank.add(0);
		}
	}

	int find(int x) {
		if (parents.get(x) == x) {
			return x;
		} else {
			return parents.set(x, find(parents.get(x)));
		}
	}

	void unite(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return;
		if (rank.get(x) < rank.get(y)) {
			parents.set(x, y);
		} else {
			parents.set(y, x);
			if (rank.get(x) == rank.get(y))
				rank.set(x, rank.get(x + 1));
		}
	}

	boolean same(int x, int y) {
		return (find(x) == find(y));
	}

}

public class DA5_1 {

	/**
	 * @param args
	 */
	public static boolean end = false;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		int n = 0, m = 0;// n:端子数、m:辺数
		n = scan.nextInt();
		m = scan.nextInt();
		boolean count[] = new boolean[m];
		boolean is_all[] = new boolean[n];
		boolean flag_all = true;
		boolean flag_tree = true;
		Arrays.fill(count, false);
		Arrays.fill(is_all, false);
		ArrayList<Edge> A = new ArrayList<Edge>(); // 入力される辺のリスト
		ArrayList<Edge> C = new ArrayList<Edge>(); // 調査を実行する辺のリスト
		UnionFind uf = new UnionFind(n);
		int vi[] = new int[m];
		int vj[] = new int[m];
		int w[] = new int[m];
		for (int i = 0; i < m; i++) {
			vi[i] = scan.nextInt();
			vj[i] = scan.nextInt();
			w[i] = scan.nextInt();
		}
		scan.close();
		for (int i = 0; i < m; i++) {
			A.add(new Edge(vi[i], vj[i], w[i]));
		}

		// 判定
		while (end == false) {
			Arrays.fill(is_all, false);
			C.clear();
			flag_all = true;
			for (int i = 0; i < m; i++) {
				if (count[i] == true)
					C.add(A.get(i));
			}
			for (int i = 0; i < C.size(); i++) {
				is_all[C.get(i).vi] = true;
				is_all[C.get(i).vj] = true;

			}
			for (int i = 0; i < n; i++) {
				if (is_all[i] == false) {
					flag_all = false;
					break;
				}
			}
			// ここまで全域木(正確には木かはわからないが)かどうかを判断
			// ここからループになっていないかを判断
			if (flag_all == true) {
				for (int i = 0; i < C.size(); i++) {
					if (uf.same(C.get(i).vi, C.get(i).vj)) {
						flag_tree = false;
						break;
					}else{
						uf.unite(C.get(i).vi, C.get(i).vj);
					}
				}
			}
			func(count, 0, m);
		}
	}

	private static boolean[] func(boolean A[], int i, int n) {
		if (i >= n) {
			end = true;
			return A;
		}

		if (A[i] == false) {
			A[i] = true;
			for (int j = i - 1; j >= 0; j--) {
				A[j] = false;
			}
			return A;
		} else {
			return func(A, i + 1, n);
		}
	}

}
