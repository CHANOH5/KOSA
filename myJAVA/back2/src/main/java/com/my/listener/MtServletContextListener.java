package com.my.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


// servletContext객체의 생성과 소멸을 관리하는 감시자
@WebListener
public class MtServletContextListener implements ServletContextListener {

    public MtServletContextListener() {
        
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
         
    }

    public void contextInitialized(ServletContextEvent sce)  { 
         System.out.println("MtServletContextListener.contextInitialized 호출됨");
    
    } // contextInitialized
	
}
