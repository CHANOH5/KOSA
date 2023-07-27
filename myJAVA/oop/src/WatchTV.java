// 제공자 소스
class TV{
	boolean power;
	int channel;
	int volume;
	
	/**
	 * 전원을 켠다
	 */
	void powerOn() {
		power = true;
	}
	
	/**
	 * 전원 상태를 반환한다.
	 * @return 전원이 켜진상태면 true를 반환 꺼진 상태면 false를 반환
	 */
	public boolean isPower() {
		return power;
	}
	
	/**
	 * 채널을 설정한다.
	 * @param ch 설정할 채널값
	 */
	void setChannel(int ch) {
		channel = ch;
	}
	
	/**
	 * 현재채널값을 반환한다
	 * @return 현재 채널 값
	 */
	public int getChannel() {
		return this.channel;
	}
	
	/**
	 * 채널값을 1증가한다.
	 */
	void channelUp() {
		channel++;
	}
	
	/**
	 * 음량을 1 증가한다
	 * 단 최소음량은 0이다.
	 */
	void volumUp() {
		this.volume++;
	}
	
	/**
	 * 음량을 1 감소한다
	 */
	void volumeDown() {
		
		if(volume == 0) {
			return;
		}
		this.volume--;

	}
	
	/**
	 * 현재 음량값 반환한다
	 * @return 음량
	 */
	int getVolume() {
		return volume;
	}

	
} // TV

// 사용자 소스
public class WatchTV {

	public static void main(String[] args) {
		
		TV tv;			// 참조형 지역변수 선언
		tv = new TV(); 	// 인스턴스화
		System.out.println(tv.power);  
		System.out.println(tv.channel);
		System.out.println(tv.volume);
		
		tv.powerOn();
		boolean flag = tv.isPower();
		
		if(flag == true) {
			System.out.println("전원이 켜졌습니다.");
			tv.setChannel(11);
			int ch = tv.getChannel();
			System.out.println("현재 채널은" + ch + "입니다.");
			
			tv.channelUp();
			System.out.println("현재 채널은" + tv.getChannel() + "입니다.");
			
			//음량을 20증가
			for(int i = 0; i < 20; i++) {
				tv.volumUp();
				System.out.println("현재 볼륨은" + tv.getVolume());
			}
			
			//음량을 50감소
			for(int i = 0; i < 50; i--) {
				tv.volumeDown();
			}

			System.out.println("현재 볼륨은" + tv.getVolume());
	
		} else { 
			System.out.println("전원이 꺼졌습니다.");
		}
		
	} // main

} // end class



