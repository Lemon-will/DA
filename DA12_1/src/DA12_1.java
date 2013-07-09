import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DA12_1 {
	public static int time = 0;
	public static int n = 0;
	public static int m = 0;
	public static Vertices[] V;
	public static Edge[] E;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		PriorityQueue<Integer> Q = new PriorityQueue<Integer>(1, new Mycomp());
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		V = new Vertices[m];
		E = new Edge[n];
		for (int i = 0; i < m; i++) {
			V[i] = new Vertices(i);
		}
		for (int i = 0; i < n; i++) {
			E[i] = new Edge();
		}

	}

	public static void DFS() {
		for (int i = 0; i < V.length; i++) {
			V[i].color = Vertices.WHITE;
			V[i].pi = null;
		}
		time = 0;
		for (int i = 0; i < V.length; i++) {
			if (V[i].color == Vertices.WHITE)
				DFS_Visit(V[i]);
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
			V[i].d = 3 * n;
			V[i].pi = null;
		}
		s.d = 0;
	}
	
	private void Relax(Vertices u,Vertices v,int w){
	}
}

class Mycomp implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO 自動生成されたメソッド・スタブ
		int i = o1;
		int j = o2;

		if (i > j) {
			return 1;
		} else if (i < j) {
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
