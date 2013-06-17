import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
 * 活動を保存するためのクラス。要素として開始時間sと終了時間fを持ち、
 * Collections.sort()を実行するとfの比較によって昇順に並び替えることができるようにしている
 */
class Active implements Comparable<Active> {

	int s;
	int f;

	Active(int s, int f) {// コンストラクタ
		this.s = s;
		this.f = f;
	}

	@Override
	public int compareTo(Active o) {// 比較方法の指定メソッド。自分自身の数のほうが大きい時に正の数を返す
		// TODO 自動生成されたメソッド・スタブ
		return this.f - o.f;
	}
}

public class DA4_4 {

	/**
	 * 活動選択問題をDPで解く。
	 */
	public static boolean end = false;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// 入力、初期化
		Scanner scan = new Scanner(System.in);
		int n = 0;
		n = scan.nextInt();
		int s[] = new int[n];
		int f[] = new int[n];
		int c[][] = new int[n + 2][n + 2];
		int d[] = new int[n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(c[i], 0);
		}
		for (int i = 0; i < n; i++) {
			s[i] = scan.nextInt();
			f[i] = scan.nextInt();
		}
		ArrayList<Active> A = new ArrayList<Active>();// 入力した活動
		A.clear();
		for (int i = 0; i < n; i++) {
			A.add(new Active(s[i], f[i]));
		}
		Arrays.fill(d,1);

		// fの小さい順に並び替える
		Collections.sort(A);
		// 算出
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (A.get(j).f < A.get(i).s) {
					d[i] = Math.max(d[i], d[j] + 1);
				}
			}
		}
		System.out.println(d[n-1]);
	}

}
