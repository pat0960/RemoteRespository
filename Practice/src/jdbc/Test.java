package jdbc;

public class Test {
public static void main(String[] args){
	CustomerDAO dao=new CustomerDAO();
	CustomerBean bean=dao.select("Alex");
	System.out.println(bean);
	
	
}
	
	
}
