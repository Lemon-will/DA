import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	int find(int x) {
		if (parents.get(x) == x) {
			return x;
		} else {
			parents.set(x, find(parents.get(x)));
			int tmp = parents.get(x);
			return tmp;
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

public class DA5_3 {

	/**
	 * 最小全域木問題、Greedy解法
	 */
	public static boolean end = false;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		int n = 0, m = 0;// n:端子数、m:辺数
		int min = 0; // 最終的な最小値
		int count = 0; // 調査した辺数
		// 入力
		n = scan.nextInt();
		m = scan.nextInt();
		ArrayList<Edge> A = new ArrayList<Edge>(); // 入力される辺のリスト
		ArrayList<Edge> C = new ArrayList<Edge>(); // 答えを入れるためのリスト
		A.clear();
		C.clear();
		UnionFind uf = new UnionFind(n); // インスタンス生成
		int vi[] = new int[m];
		int vj[] = new int[m];
		int w[] = new int[m];
		for (int i = 0; i < m; i++) {
			vi[i] = scan.nextInt();
			vj[i] = scan.nextInt();
			w[i] = scan.nextInt();
		}
		scan.close();
		for (int i = 0; i < m; i++) {// EdgeオブジェクトをAという動的配列に代入。配列の都合上頂点は本来のNo.から1引いている
			A.add(new Edge(vi[i] - 1, vj[i] - 1, w[i]));
		}
		long start = System.currentTimeMillis();
		Collections.sort(A); // 重みの小さい順にソートする
		// 判定
		for (int i = 0; i < A.size(); i++) {
			if (!uf.same(A.get(i).vi, A.get(i).vj)) {
				C.add(A.get(i));
				uf.unite(A.get(i).vi, A.get(i).vj);
				min += A.get(i).w;
			}
		}

		long stop = System.currentTimeMillis();
		// 出力
		System.out.println(min);
		for (int i = 0; i < C.size(); i++) {
			System.out.print((C.get(i).vi + 1) + " " + (C.get(i).vj + 1) + " "
					+ C.get(i).w + "\n");
		}
		System.out.println("Time: " + (stop - start) + "ms");
	}

}