package com.hsf_edas.service.impl;


import com.hsf_edas.pojo.Item;
import com.hsf_edas.service.ItemService;

/**
 * Alibaba Group EDAS. http://www.aliyun.com/product/edas
 */
public class ItemServiceImpl implements ItemService {

	@Override
	public Item getItemById(long id ) {
		Item car = new Item();
		car.setItemId( id );
		car.setItemName( "Mercedes Benz" );
		return car;
	}
	@Override
	public Item getItemByName( String name ) {
		Item car = new Item();
		car.setItemId( 110L );
		car.setItemName(name );
		return car;
	}
}
