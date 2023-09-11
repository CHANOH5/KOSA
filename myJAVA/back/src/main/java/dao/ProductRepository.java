package dao;

import java.lang.module.FindException;
import java.util.List;

import dto.Product;

public interface ProductRepository {

	List<Product> selectAll(int startRow, int endRow) throws FindException;

} // end class
