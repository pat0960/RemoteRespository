package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;

public class ProductServlet extends HttpServlet {
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	private ProductService ps = new ProductService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		String temp1 = request.getParameter("id");
		String name = request.getParameter("name");
		String temp2 = request.getParameter("price");
		String temp3 = request.getParameter("make");
		String temp4 = request.getParameter("expire");
		String prodaction = request.getParameter("prodaction");
		// if(name==null||name.trim().length()==0){
		// errorMsgMap.put("name","姓名必須輸入");
		// }
//        System.out.println(request.getMethod());
		
		int id = 0;
		if (temp1 != null && temp1.trim().length() != 0) {
			try {
				id = Integer.parseInt(temp1.trim());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsgMap.put("id", "Id必須輸入數字");
			}

		}
		double price = 0;
		if (temp2 != null && temp2.trim().length() != 0) {
			try {
				price = Double.parseDouble(temp2.trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgMap.put("price", "price必須輸入");
			}

		}
		java.util.Date make = null;
		if (temp3 != null && temp3.trim().length() != 0) {
			try {
				make = sFormat.parse(temp3.trim());
			} catch (ParseException e) {
				e.printStackTrace();
				errorMsgMap.put("make", "請輸入時間 格式為 YYYY-MM-DD");
			}

		}
		int expire = 0;
		if (temp4 != null && temp4.trim().length() != 0) {
			try {
				expire = Integer.parseInt(temp4.trim());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsgMap.put("expire", "expire必須為數字");
			}

		}

		System.out.println("hhhhhhh");
		if ("Insert".equals(prodaction) || "Update".equals(prodaction)
				|| "Delete".equals(prodaction)) {
			if (temp1 == null || temp1.trim().length() == 0) {
				errorMsgMap.put("id", "id必須輸入" + prodaction);
			}
		}
		if (errorMsgMap != null && !errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/product.jsp");
			rd.forward(request, response);
			return;
		}
		ProductBean bean = new ProductBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPrice(price);
		bean.setMake(make);
		bean.setExpire(expire);

		if ("Select".equals(prodaction)) {
			List<ProductBean> result = ps.select(bean);
			request.setAttribute("select", result);
			request.getRequestDispatcher("/pages/display.jsp").forward(request,
					response);

		}
		if ("Delete".equals(prodaction)) {
			boolean result = ps.delete(bean);
			request.setAttribute("select", result);
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);
		}
		if ("Insert".equals(prodaction)) {
			ProductBean result = ps.insert(bean);
			request.setAttribute("insert", result);
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);
		}
		if ("Update".equals(prodaction)) {
			ProductBean result = ps.update(bean);
			result.setName(name);
			result.setPrice(price);
			result.setMake(make);
			result.setExpire(expire);
			request.setAttribute("update", result);
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);

		}
//		System.out.println("id="+temp1+"name="+name);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request,response);
		//super.doPost(request, response);
	}

}
