package control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.exception.FindException;
import com.my.product.dto.Product;
import com.my.product.service.ProductService;

@RestController
public class CartController {
	
	@Autowired
	private ProductService service;

    @GetMapping("/addcart")
    public void addcart(HttpServletRequest request, @RequestParam(name = "prodNo") String prodNo,
	    @RequestParam(name = "quantity") Integer quantity) {
    	
    	HttpSession session = request.getSession();

    	Map<String, Object> cart = (Map) session.getAttribute("cart");

    	if (cart == null) {
    		cart = new HashMap<>();
    		session.setAttribute("cart", cart);
    	}
    	
    	if (cart.get(prodNo) != null) {
    		
    		int oldQuantity = (int) cart.get(prodNo);
    		
    		cart.put(prodNo, quantity + oldQuantity);
    		
    		
    	} else {
    		cart.put(prodNo, quantity);
    	}
    	
    	for(Entry<String, Object> key : cart.entrySet()) {
    		System.out.println("상품번호 : " + key.getKey() + ", 상품수량 : " + key.getValue());
    	} // for
    	
    }
	
	@GetMapping("/cartlist")
	public List<Map<String, Object>> list(HttpSession session) throws FindException {
		
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		List<Map<String, Object>> list = new ArrayList<>();
		
		if(cart == null) {
			Map <String, String> map = new HashMap<>();
			map.put("msg", "장바구니 빔");
		} else {
			Product p;
			for(String prodNo : cart.keySet()) {
				int quantity = cart.get(prodNo);
				
				p = service.findByProdNo(prodNo);
				String prodName = p.getProdName();
				int prodPrice =p.getProdPrice();
				
				Map<String, Object> map = new HashMap<>();				
				map.put("product", p);
				map.put("quantity", quantity);
				
				list.add(map);
				
			} // for
			
		}// if-else
		
		return list;
		
	} // list
	
} // end class
