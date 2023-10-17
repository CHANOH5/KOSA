package com.my.di.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.di.dto.A;
import com.my.di.dto.B;

public class ContainerStart {
	
	public static void main(String[] args) {
		
		// 스프링 엔진 = 스프링 컨테이너 = 스프링 어플리케이션 컨텍스트
		// 스프링 컨테이너를 구동하기! 
		String configFileName = "myApplicationContext.xml";
		
		ApplicationContext ctx; // applicationContext타입의 변수를 선언
		ctx = new ClassPathXmlApplicationContext(configFileName);
		
		// 스프링 컨테이너에 있는 스프링 객체를 찾는다
		// 첫번째 인자로 bean태그에 등록된 id값
		// 두번째 인자로 bean태그에 등록된 class에 해당하는 값
		A a1 = ctx.getBean("a", com.my.di.dto.A.class);
		System.out.println(a1);
		
		A a2 = ctx.getBean("a", com.my.di.dto.A.class);
		System.out.println(a1);
		
		B b1 = ctx.getBean("b", com.my.di.dto.B.class);
		
		// a1과 a2가 같은 객체를 참조하는지 확인
		System.out.println("싱글톤 여부 : " + (a1 == a2));
		
		System.out.println("msg : " + a1.getMsg());
		
		System.out.println("B클래스의 no값 : " + b1.getNo());
		
	} // main

} // end class
