package com.hsf_edas.listener;

import com.hsf_edas.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Alibaba Group EDAS. http://www.aliyun.com/product/edas
 */
public class StartListener implements ServletContextListener{

	@Override
	public void contextInitialized( ServletContextEvent sce ) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( sce.getServletContext() );
		final ItemService itemService = ( ItemService ) ctx.getBean( "item" );
		Thread thread = new Thread( new Runnable() {
			@Override
			public void run() {
				while ( true ) {
					try {
						System.out.println( itemService.getItemById( 1111 ) );
						System.out.println( itemService.getItemByName( "hello" ) );
						Thread.sleep( 20000l );
					} catch ( Throwable e ) {
						e.printStackTrace();
					}
				}
			}
		} );
		thread.start();
	}

	@Override
	public void contextDestroyed( ServletContextEvent sce ) {
		// TODO Auto-generated method stub
	}

}
