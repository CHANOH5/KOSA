package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] {MyApplicationContext.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] {MyMVCContext.class};
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"};
	}

} // end class
