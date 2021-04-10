package com.hsf_edas.service;

import com.hsf_edas.pojo.Item;

/**
 * Alibaba Group EDAS. http://www.aliyun.com/product/edas
 */
public interface ItemService {

	public Item getItemById(long id);
	
	public Item getItemByName(String name);
	
}
