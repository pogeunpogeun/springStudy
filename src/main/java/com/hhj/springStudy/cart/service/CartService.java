package com.hhj.springStudy.cart.service;

import java.util.List;

import com.hhj.springStudy.cart.model.dto.CartDTO;

public interface CartService {
	public List<CartDTO> getSelectAll();
	public int setInsert(CartDTO paramDto);
	public int setUpdate(CartDTO paramDto);
	public int setDelete(CartDTO paramDto);
	public int cartClear(int memberNo);
}
