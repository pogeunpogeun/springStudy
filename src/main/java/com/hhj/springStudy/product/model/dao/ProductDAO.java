package com.hhj.springStudy.product.model.dao;

import java.util.List;

import com.hhj.springStudy.product.model.dto.ProductDTO;

public interface ProductDAO {
	public List<ProductDTO> getSelectAll();
	public ProductDTO getSelectOne(ProductDTO paramDto);
	public int setInsert(ProductDTO paramDto);
	public int setUpdate(ProductDTO paramDto);
	public int setDelete(ProductDTO paramDto);
}
