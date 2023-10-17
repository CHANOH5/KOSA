package com.my.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MySessionListener implements HttpSessionAttributeListener {

	private int loginedCnt;
	
    public MySessionListener() {
        System.out.println("MySessionListener객체 생성됨");
        // 객체가 생성되면 생성자가 호출이 된다
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	// session에 추가되면 호출 될 것 예상
    	System.out.println("MySessionListener.attributeAdded() 메서드 호출됨 ");
    	
    	if(se.getName().equals("loginedId")) {
    		loginedCnt++;
    		System.out.println("로그인된 사용자 수: " + loginedCnt);
    	} // if
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	// session에서 제거되면 호출 될 것 예상
    	System.out.println("MySessionListener.attributeRemoved() 메서드 호출됨 ");
    	
    	if(se.getName().equals("loginedId")) {
    		loginedCnt--;
    		System.out.println("로그아웃 되었습니다 : 로그인된 사용자 수: " + loginedCnt);
    	} // if
    	
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         
    }
	
}
