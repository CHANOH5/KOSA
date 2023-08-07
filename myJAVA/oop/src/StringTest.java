import java.util.StringTokenizer;

public class StringTest {

	public static void main(String[] args) {
	
		String s = "안녕하세요";
		
		char c = s.charAt(0); // 0번 인덱스의 문자를 얻고싶다
		
		int size = s.length(); // 문자열의 길이 획득
		
		System.out.println(c);
		System.out.println(size);
		
		System.out.println("============ Palindrome =============");
		
		boolean Palindrome = true;
	
		s = "가나다라다나가";
//		System.out.println("Palindrome문자열입니다");
//		System.out.println("Palindrome문자열이 아닙니다");

		char[] arr = s.toCharArray();
		
		int max = arr.length; // 배열 크기 저장
		
		System.out.println("s배열의 크기 = " + max);
		
		// 배열의 첫 번째 요소(arr[0])와
		// 마지막 요소(arr[max-1])가 서로 같지 않은지를 비교
		
		for(int i = 0; i < max; i++) { 		// 배열 양 끝값 비교
			if(arr[i] != arr[max-1-i]) { 	// index 0부터 배열 크기의 절반 -1만큼 진행
				Palindrome = false;
				break;
			} // if
		} // for
		
		if(Palindrome) {
			System.out.println("Palindrome문자열입니다");
		} else {
			System.out.println("Palindrome문자열이 아닙니다");
		} // if-else
		
		System.out.println("================= split ================"); 
		
//		s = "100:70:65";	 // 국어 수학 영어 점수
		s = "100::65";		 // 수학은 응시 안했다로 예시
		String delim = ":"; // 구분자 선언
		
		String[] arr1 = s.split(delim); // split메서드의 반환 결과를 배열로 받음
		
		for(String str : arr1) {	// "100", "", "65"
//			System.out.println(str);
			if(str.equals("")) {
				System.out.println("미응시");
			} else {
				System.out.println(Integer.parseInt(str));
			} // if-else
		} // enhanced for
		
		System.out.println("================= StringTokenizer ================"); 
		
		StringTokenizer st = new StringTokenizer(s, delim);
		while(st.hasMoreTokens()) { 		// 분리할 토큰이 있는가?
			String str = st.nextToken();
			System.out.println(str);
		}
		
	} //main

} // end class
