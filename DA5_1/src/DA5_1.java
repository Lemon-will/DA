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
		parents.clear();
		rank.clear();
		for (int i = 0; i < n; i++) {
			parents.add(i);
			rank.add(0);
		}
	}

	void Init(int n) {
		parents.clear();
		rank.clear();
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
				rank.set(x, rank.get(x) + 1);
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
		int tmp = 0; // 重さ測定用変数
		int min = 0; // 最終的な最小値
		n = scan.nextInt();
		m = scan.nextInt();
		boolean count[] = new boolean[m];
		boolean flag_all = true;
		boolean flag_tree = true;
		Arrays.fill(count, false);
		ArrayList<Edge> A = new ArrayList<Edge>(); // 入力される辺のリスト
		ArrayList<Edge> C = new ArrayList<Edge>(); // 調査を実行する辺のリスト
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
			A.add(new Edge(vi[i] - 1, vj[i] - 1, w[i]));
			min += A.get(i).w;
		}
		min++;

		// 判定
		while (end == false) {
			UnionFind uf = new UnionFind(n);
			uf.Init(n);
			flag_all = true;
			flag_tree = true;
			C.clear();
			tmp = 0;
			for (int i = 0; i < m; i++) {
				if (count[i] == true)
					C.add(A.get(i));
			}
			// ここからループになっていないかを判断
			for (int i = 0; i < C.size(); i++) {
				if (uf.same(C.get(i).vi, C.get(i).vj)) {
					flag_tree = false;
					break;
				} else {
					tmp += C.get(i).w;
					uf.unite(C.get(i).vi, C.get(i).vj);
				}
			}
			// ループがない場合は全域木かどうかを調査
			if (flag_tree == true) {
				for (int i = 1; i < n; i++) {
					if (!uf.same(i, i - 1)) {
						flag_all = false;
						break;
					}
				}
			}
			// ループがなく、全域木である場合のみに最小値の更新
			if ((flag_tree == true) && (flag_all == true)) {
				min = Math.min(min, tmp);
				System.out.println(min);
			}
			func(count, 0, m);
		}
		System.out.println(min);
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
