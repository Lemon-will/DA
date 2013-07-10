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
		n = scan.nextInt();
		m = scan.nextInt();
		V = new ArrayList<Vertices>();
		E = new Edge[n];
		for (int i = 0; i < m; i++) {
			V.add(new Vertices(i));
		}
		for (int i = 0; i < n; i++) {
			E[i] = new Edge();
		}

	}

	public static void DFS() {
		for (int i = 0; i < V.size(); i++) {
			V.get(i).color = Vertices.WHITE;
			V.get(i).pi = null;
		}
		time = 0;
		for (int i = 0; i < V.size(); i++) {
			if (V.get(i).color == Vertices.WHITE)
				DFS_Visit(V.get(i));
		}
	}

	public static void DFS_Visit(Vertices u) {
		ArrayList<Vertices> A = new ArrayList<Vertices>();
		time++;
		u.d = time;
		u.color = Vertices.GRAY;
		A = Adj(u);
		for (int i = 0; i < A.size(); i++) {
			if (A.get(i).color == Vertices.WHITE) {
				A.get(i).pi = u;
				DFS_Visit(A.get(i));
			}
		}
		u.color = Vertices.BLACK;
		time++;
		u.f = time;
	}

	private static ArrayList<Vertices> Adj(Vertices u) {
		ArrayList<Vertices> list = new ArrayList<Vertices>();
		for (int i = 0; i < n; i++) {
			if (E[i].u.equals(u)) {
				list.add(E[i].v);
			}
		}
		return list;
	}

	private static void Init(Vertices s) {
		for (int i = 0; i < n; i++) {
			V.get(i).d = 3 * n;
			V.get(i).pi = null;
		}
		s.d = 0;
	}

	private static void Relax(Vertices u, Vertices v, int w) {
		if (v.d > u.d + w) {
			v.d = u.d + w;
			v.pi = u;
		}
	}

	private static void Dijkstra(int w, Vertices s) {
		PriorityQueue<Vertices> Q = new PriorityQueue<Vertices>(1, new Mycomp());
		ArrayList<Vertices> S = new ArrayList<Vertices>();
		Init(s);
		S.clear();
		for (int i = 0; i < V.size(); i++) {
			Q.add(V.get(i));
		}
		while (Q != null) {
			Vertices u = Q.poll();
			S.add(u);
			for (int i = 0; i < E.length; i++) {
				if(u.equals(E[i].u)){
					Relax(u,E[i].v,w);//wについて再考する必要あり
				}
			}
		}

	}
}

class Mycomp implements Comparator<Vertices> {

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

class Vertices {
	public final static int WHITE = 0;
	public final static int GRAY = 1;
	public final static int BLACK = 2;
	int index;
	int d, f;
	int color;
	Vertices pi;

	public Vertices(int id) {
		// TODO 自動生成されたコンストラクター・スタブ
		index = id;
		color = WHITE;
		pi = null;
	}

}

class Edge {
	Vertices u, v;
	int w;

}
