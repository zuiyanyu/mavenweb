package com.spring_stu.service.serviceImpl;

import com.spring_stu.dao.BookDao;
import com.spring_stu.service.BookService;

public class BookServiceImpl implements BookService {
	
	// 方式1：之前，接口=实现类
//	private BookDao bookDao = new BookDaoImpl();
	// 方式2：接口 + setter
	private BookDao bookDao;
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Override
	public void addBook(){
		this.bookDao.save();
	}

	public BookServiceImpl() {
		System.out.println("被new 了");
	}
	
}
