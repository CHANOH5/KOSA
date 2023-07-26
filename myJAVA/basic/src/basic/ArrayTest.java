package basic;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTest {

	public static void main(String[] args) {
		
//		int[] arr;
//		arr = new int[4];
		
//		int[] arr = new int[4];
		
//		arr[0] = 10; // 국어 점수
//		arr[1] = 20; // 수학 점수
//		arr[2] = 33; // 영어 점수
//		arr[3] = 40; // 과학 점수
		
		int[] arr = {10, 20, 33, 40};
		
		System.out.println(arr.length); // 3
		
		int totalScore = 0;
		
		// for
		for(int i = 0; i < arr.length; i++) {
			totalScore += arr[i];
		}
		
		// enhanced for
		for( int score : arr ) {
			totalScore += score;
		}
		
//		float avg = (float)totalScore / arr.length;
		
//		System.out.println("totalScore = " + totalScore + ", " + "avg = " + avg);

//		int[][] arrTwo = {
//							{1, 2},
//							{3, 4},
//							{5,6}
//						 };
//		
//		System.out.println(arrTwo.length);
//		System.out.println(arrTwo[0].length);
		
		int[][] arrTwo = new int[3][2];
		int num = 1;
		
//		for(int col = 0; col < 2; col++, num++) {
//			arr2[0][col] = num;
//		}
//	
		
		int rowLength = arrTwo.length;
		
		for(int row = 0; row < rowLength; row++) {
			int colLength = arrTwo[row].length;
			for(int col = 0; col < 2; col++, num++) {
				arrTwo[row][col] = num;
				
				System.out.print(arrTwo[row][col] + ",");
			}
			System.out.println();
		}
		
		//행별 열 수 다르게 하기
		int[][] arrTwo2 = new int[3][];
		arrTwo2[0] = new int[1];
		arrTwo2[1] = new int[2];
		arrTwo2[2] = new int[3];
		
		System.out.println(arrTwo2.length); //3
		System.out.println(arrTwo2[0].length); //1
		System.out.println(arrTwo2[1].length); //2
		System.out.println(arrTwo2[2].length); //3

		num =1;
//		for( int row = 0; row < rowLength; row++) {
//			int colLength = arrTwo[row].length;
//			for(int col = 0; col < colLength; col++, num++) {
//				arr2[row][col] = num;
//			}
//		}
		
//		for( int row = 0; row < rowLength; row++) {
//			int colLength = arrTwo[row].length;
//			for(int col = 0; col < colLength; col++) {
//				System.out.print(arr2[row][col] + ",");
//			}
//			System.out.println();
//		}
		
		num = 1;
		rowLength = arrTwo2.length;
		for(int row=0; row< rowLength ; row++) {
			int colLength = arrTwo2[row].length;
			for(int col=0; col<colLength; col++, num++) {
				arrTwo2[row][col] = num;
			}
		}
		
		for(int row=0; row< rowLength ; row++) {
			int colLength = arrTwo2[row].length;
			for(int col=0; col<colLength; col++) {
				System.out.print(arrTwo2[row][col] + ",");
			}
			System.out.println();
		}
		
		int[] arr1 = {1, 10, 6, 3, 2, 4, 5, 5, 2, 1};
		int[] arr2 = new int[10]; // [0]는 1의 출현횟수 누적공간
								  // [1]는 2의 출현횟수 누적공간
		
		System.out.println("숫자의 출현횟수를 출력하시오.");
//		arr2[arr1[0]-1]++;
//		arr2[arr1[1]-1]++;
//		arr2[arr1[2]-1]++;
//		arr2[arr1[3]-1]++;
		
		// enhanced for
//		for(int value : arr1) {
//			arr2[ value-1]++;
//		}
		
		for(int i = 0; i<arr1.length; i++) {
			arr2[ arr1[i]-1 ]++;     // 누적횟수 획득
		}
		for(int i = 0; i<arr2.length; i++) {
			System.out.println((i+1)+"의 출현횟수 = " + arr2[i] +"회");
		}
		
//		for(int i = 0; i < arr2.length; i++) {
//			int same = arr[i];
//			for(int j = 0; j < arr1.length; j++) {
//				
//				arr2[]++;
//			}
//				
//		}
		
		
		int[] arr3 = {-1, -2, -3};
		System.out.println("최대값을 계산하시오.");

		int max = arr3[0];
		
		for(int i = 0; i < arr3.length; i++) {
			if( arr3[i] > max ) {
				max = arr3[i];
			}
		}
		System.out.println("최대값은 " + max + "입니다.");
		
		// 배열 정렬
		
		int[] arr4 = {5, 4, 7, 1 ,2};
		
		Arrays.sort(arr4);
		
		System.out.println(Arrays.toString(arr4));
		
//		int[] lotto = new int[6];
//		
//		for(int i=0; i<lotto.length; i++) {
//			lotto[i] = (int)( Math.random() * 45 + 1) ;
//			for(int j = 0; j < i; j++) {
//				if(lotto[i] == lotto[j] ) {
//					i--;	// 중복이 있을 경우 다시 반복
//					break; // 조건이 만족하면 반복문을 빠져나옴
////					continue; // 반복문을 완전히 빠져나오지 않고 증감식으로 가게됨
//				}
//			}
//		} 
//		for(int value : lotto) {
//			System.out.print(value+",");
//		}
		
		
		int[][] arrTwo3; //선언
		arrTwo3 = new int[5][];
		
//		arrTwo3[0] = new int[1];
//		arrTwo3[1] = new int[2];
//		arrTwo3[2] = new int[3];
//		arrTwo3[3] = new int[4];
//		arrTwo3[4] = new int[5];
		
		rowLength = arrTwo3.length;
		
		for(int i = 0; i < rowLength; i++) {
			arrTwo3[i] = new int[i+1];
		}
		
////		arrTwo3[0][0] = 1;
//		arrTwo3[0][ arrTwo3[0].length-1 ] = 1;
//		
//		arrTwo3[1][0] = 1;
//		arrTwo3[1][ arrTwo3[1].length-1 ] = 1;
//		
//		arrTwo3[2][0] = 1;
//		arrTwo3[2][ arrTwo3[2].length-1 ] = 1;
//		
//		arrTwo3[3][0] = 1;
//		arrTwo3[3][ arrTwo3[3].length-1 ] = 1;
//		
//		arrTwo3[4][0] = 1;
////		arrTwo3[4][4] = 1;
//		arrTwo3[4][ arrTwo3[4].length-1 ] = 1;
		
		for(int i = 0; i < rowLength; i++ ) {
			arrTwo3[i][0] = 1;
			int colLength = arrTwo3[i].length; // 열
			for(int j=1; j<colLength-1; j++) {
				arrTwo3[i][j] = arrTwo3[i][j] = arrTwo3[i-1][j-1] + arrTwo3[i-1][j];
			}
			arrTwo3[i][colLength-1] = 1;
		}
		
		// enhacned for
		for(int[]valueArr : arrTwo3) {
			for(int value : valueArr) {
				System.out.print(value + ",");
			}
			System.out.println();
		}
		
		
		
		// step.1 과목 선언
		String[] subject = {"국어", "수학", "영어"};
		int subjectLength = subject.length;
		
		int[][] arrTwo4 = new int[10][subjectLength]; // 최대 10명의 학생 점수(국어, 수학, 영어)
//		arrTwo4[0][0] = 10; // 1번 학생의 국어점수는 10
//		arrTwo4[0][1] = 5; // 1번 학생의 수학점수는 5
//		arrTwo4[0][2] = 7; // 1번 학생의 영어점수는 7
		
		Scanner sc = new Scanner(System.in);
		int no = 0;
		
		while( no < 10 ) {
			// step 2. 학생수(no)가 11인 경우에는 반복을 빠져나오기
			
			System.out.println("점수입력을 진행하시겠습니까 [y/n]");
			String yn = sc.next();
			if(yn.equals("n")) {
				break;
			} else if(yn.equals("y")) {
				
//				System.out.print((no+1) + "학생의 국어점수");
//				int k = sc.nextInt();
//				arrTwo4[no][0] = k;
//				
//				System.out.print((no+1) + "학생의 수학점수");
//				int m = sc.nextInt();
//				arrTwo4[no][1] = m;
//				
//				System.out.print((no+1) + "학생의 영어점수");
//				int e = sc.nextInt();
//				arrTwo4[no][2] = e;
				
				// 줄인 코드
				for(int i = 0; i < subjectLength; i++) {
					System.out.println((no+1) + "번 학생의" + subject[i] + "점수:");
					arrTwo4[no][i] = sc.nextInt();
				}
					
				no++;
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
			
		}
		
		
		// 학생들의 점수 출력하기
		// 1번 학생점수: 국어-, 수학-, 영어-
		// 2번 학생점수: 국어-, 수학-, 영어-
//		int[] k;
//		int[] m;
//		int[] e;
//		
//		int avg = 0;
//		int total = 0;
//		
//		for(int i = 0; i < arrTwo4.length; i++) {
//			
//			int colLength = arrTwo4[i].length;
//			
//			for(int j = 0; j < colLength; i++) {
//				
//				arrTwo4[no][j] = arrTwo4[i][j];
//				
//				System.out.println(no+"번 학생의 점수" + "국어" + arrTwo4[i][j] + "수학" + );
//				
//				System.out.print(Arrays.deepToString(arrTwo4));
//				
//			}
//			
//		}
		
		// 강사님

		int[] totalScoreSubject = new int[3];
		System.out.println("학생들의 점수 출력하기");
		for(int i = 0; i < no; i++) {
			System.out.println((i+1) + "번 학생점수:");
			totalScore = 0 ; // 학생별 총점
			for(int j = 0; j < subjectLength; j++) {
				totalScore += arrTwo4[i][j];
			}
			
//			System.out.println("국어 - " + k + ", 수학-" + m + ", 영어-" + e
			for(int j = 0; j < subjectLength; j++) {
				System.out.print(subject[j] + "-");
				System.out.print(arrTwo4[i][j]);
			}
			System.out.println(", 총점-" + totalScore
								+", 평균- " +(float)totalScore/3);
			
			// 과목별 총점 누적하기
			for(int j = 0; j < subjectLength; j++) {
				totalScoreSubject[j] += arrTwo4[i][j];
			}
							
		}
		
		for(int j = 0; j <subjectLength; j++) {
			System.out.println(subject[j] + "과목 평균 : " + (float)totalScoreSubject[j]/no );
		}
		
	} // main

} //  end class
