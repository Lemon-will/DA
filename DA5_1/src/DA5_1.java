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

public class DA5_1 {

	/**
	 * 最小全域木問題、虱潰し解法
	 */
	public static boolean end = false;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		int n = 0, m = 0;// n:端子数、m:辺数
		int tmp = 0; // 重さ測定用変数
		int min = 0; // 最終的な最小値
		// 入力
		n = scan.nextInt();
		m = scan.nextInt();
		boolean count[] = new boolean[m];// 2進数カウンタ
		boolean flag_all = true;// 調査中の木が全域木かどうか
		boolean flag_tree = true;// 調査中の木が木になっているかどうか
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
		for (int i = 0; i < m; i++) {// EdgeオブジェクトをAという動的配列に代入。配列の都合上頂点は本来のNo.から1引いている
			A.add(new Edge(vi[i] - 1, vj[i] - 1, w[i]));
			min += A.get(i).w;
		}
		min++;// 最小値の値は最初は最大値+1を代入しておく
		long start = System.currentTimeMillis();
		// 判定
		while (end == false) {
			UnionFind uf = new UnionFind(n); // ループ毎にインスタンス生成
			// 初期化
			flag_all = true;
			flag_tree = true;
			C.clear();
			tmp = 0;
			for (int i = 0; i < m; i++) {// Cという動的配列にカウンタがtrueのAを加える
				if (count[i] == true)
					C.add(A.get(i));
			}

			// ここからループになっていないかを判断
			for (int i = 0; i < C.size(); i++) {
				if (uf.same(C.get(i).vi, C.get(i).vj)) {// UFが同じグループに属している場合ループができているので木ではない
					flag_tree = false;
					break;
				} else {// ループがなければ頂点同士をuniteし、重みを加算する
					tmp += C.get(i).w;
					uf.unite(C.get(i).vi, C.get(i).vj);
				}
			}
			// ループがない場合は全域木かどうかを調査
			if (flag_tree == true) {
				for (int i = 1; i < n; i++) {// すべての頂点が同じグループに属していない場合は辺でつながっていない頂点があるので全域木ではない
					if (!uf.same(i, i - 1)) {
						flag_all = false;
						break;
					}
				}
			}
			// ループがなく、全域木である場合のみに最小値の更新
			if ((flag_tree == true) && (flag_all == true)) {
				min = Math.min(min, tmp);
			}
			func(count, 0, m);// カウンタ加算
		}
		long stop = System.currentTimeMillis();
		// 出力
		System.out.println(min);
		System.out.println("Time: " + (stop - start) + "ms");
	}

	private static boolean[] func(boolean A[], int i, int n) {// 2進数インクリメントメソッド
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
