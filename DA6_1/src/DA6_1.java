
public class DA6_1 {

	/**
	 *
	 */
	public static boolean end = false;
	public static boolean flag = false;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private static int[] inc(int[] A, int n) {
		flag = false;
		A[0] = add3(A[0]);
		for (int i = 1; i < n; i++) {
			if (flag == true) {
				flag = false;
				A[i] = add3(A[i]);
				if (i == n - 1) {
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
