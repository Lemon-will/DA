import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DA12_1 {
	public static int time = 0;
	public static int n = 0;
	public static int m = 0;
	public static ArrayList<Vertices> V;
	public static Edge[] E;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		m = scan.nextInt();
		n = scan.nextInt();
		V = new ArrayList<Vertices>();
		E = new Edge[n];
		int[][] e_data = new int[3][n];
		long start, end;
		for (int i = 0; i < m; i++) {
			V.add(new Vertices(i));
		}
		for (int i = 0; i < n; i++) {
			E[i] = new Edge();
		}

		for (int i = 0; i < n; i++) {
			e_data[0][i] = scan.nextInt();
			e_data[1][i] = scan.nextInt();
			e_data[2][i] = scan.nextInt();
			E[i].u = V.get(e_data[0][i]);
			E[i].v = V.get(e_data[1][i]);
			E[i].w = e_data[2][i];
		}
		scan.close();
		start = System.currentTimeMillis();
		Dijkstra(V.get(0));
		end = System.currentTimeMillis();
		for (int i = 1; i < V.size(); i++) {
			System.out.println(i + " " + V.get(i).d);
		}
		System.out.println("Time:" + (end - start) + "ms");

	}

	private static ArrayList<Edge> Adj(Vertices u) {// 頂点ｕを含む辺をすべて返すメソッド
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (int i = 0; i < n; i++) {
			if (E[i].u.equals(u)) {
				list.add(E[i]);
			}
		}
		return list;
	}

	private static void Init(Vertices s) {// ダイクストラ初期化メソッド
		int tmp = 0;
		for (int i = 0; i < E.length; i++) {
			tmp += E[i].w;
		}
		for (int i = 0; i < V.size(); i++) {
			V.get(i).d = tmp + 1;
			V.get(i).pi = null;
		}
		s.d = 0;
	}

	private static void Relax(Vertices u, Vertices v, Edge E) {// 緩和メソッド
		if (v.d > u.d + E.w) {
			v.d = u.d + E.w;
			v.pi = u;
		}
	}

	private static void Dijkstra(Vertices s) {// ダイクストラ法を実行するメソッド
		PriorityQueue<Vertices> Q = new PriorityQueue<Vertices>(1, new Mycomp());
		ArrayList<Vertices> S = new ArrayList<Vertices>();
		ArrayList<Edge> A = new ArrayList<Edge>();
		Init(s);
		S.clear();
		for (int i = 0; i < V.size(); i++) {
			Q.add(V.get(i));
		}
		while (!Q.isEmpty()) {
			Vertices u = Q.poll();
			S.add(u);
			A.clear();
			A = Adj(u);
			for (int i = 0; i < A.size(); i++) {
				Relax(u, A.get(i).v, A.get(i));
			}
		}

	}
}

class Mycomp implements Comparator<Vertices> {// プライオリティキューの順番を決めるためのクラス

	@Override
	public int compare(Vertices o1, Vertices o2) {
		// TODO 自動生成されたメソッド・スタブ
		Vertices i = o1;
		Vertices j = o2;

		if (i.d > j.d) {
			return 1;
		} else if (i.d < j.d) {
			return -1;
		} else {
			return 0;
		}
	}

}

class Vertices {// 頂点クラス
	int index;// 頂点を区別するためのインデックス
	int d;// 最短路推定値
	Vertices pi;// 先行点

	public Vertices(int id) {
		// TODO 自動生成されたコンストラクター・スタブ
		index = id;
		pi = null;// 先行点はnullにしておく→つまり接続している辺がない
	}

}

class Edge {// 辺クラス
	Vertices u, v;// 辺の端点
	int w;// 重み

}
