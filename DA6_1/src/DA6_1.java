import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DA6_1 {

	/**
	 *	3-PARTITION問題、虱潰し解法。
	 */
	public static boolean end = false;
	public static boolean flag = false;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//入力
		Scanner scan = new Scanner(System.in);
		int n = 0;
		int sum1 = 0, sum2 = 0, sum3 = 0;
		boolean ans = false;
		n = scan.nextInt();
		int[] S = new int[n];
		for (int i = 0; i < n; i++) {
			S[i] = scan.nextInt();
		}
		ArrayList<Integer> S_1 = new ArrayList<Integer>();
		ArrayList<Integer> S_2 = new ArrayList<Integer>();
		ArrayList<Integer> S_3 = new ArrayList<Integer>();
		int[] count = new int[n]; //3bitカウンタ
		Arrays.fill(count, 0);

		long start = System.currentTimeMillis();
		//判断
		while (end == false) {
			//初期化
			S_1.clear();
			S_2.clear();
			S_3.clear();
			sum1 = 0;
			sum2 = 0;
			sum3 = 0;
			//S1,S2,S3に代入
			for (int i = 0; i < n; i++) {
				switch (count[i]) {
				case 0:
					S_1.add(S[i]);
					break;
				case 1:
					S_2.add(S[i]);
					break;
				case 2:
					S_3.add(S[i]);
					break;
				default:
					System.out.println("カウンタエラー");
					break;
				}
			}
			//加算
			for (int i = 0; i < S_1.size(); i++) {
				sum1 += S_1.get(i);
			}
			for (int i = 0; i < S_2.size(); i++) {
				sum2 += S_2.get(i);
			}
			for (int i = 0; i < S_3.size(); i++) {
				sum3 += S_3.get(i);
			}

			if (sum1 == sum2 && sum2 == sum3) {
				ans = true;
				break;
			}
			count = inc(count);
		}
		long end = System.currentTimeMillis();
		System.out.println(ans);
		System.out.println("Time: " + (end - start) + "ms");
	}

	private static int[] inc(int[] A) {
		flag = false;
		A[0] = add3(A[0]);
		for (int i = 1; i < A.length; i++) {
			if (flag == true) {
				flag = false;
				A[i] = add3(A[i]);
				if (i == A.length - 1) {
					//overflow
					end = true;
				}
			} else
				break;
		}
		return A;
	}

	private static int add3(int a) {
		if (a == 0 || a == 1) {
			a++;
			flag = false;
		} else {
			a = 0;
			flag = true;
		}
		return a;
	}
}
