package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {
	private CustomerDAO customerDao = new CustomerDAOJdbc();
//	public static void main(String[] args) {
//		CustomerService service = new CustomerService();
//		CustomerBean bean = service.login("Alex", "AAA");
//		System.out.println(bean);
//		service.changePassword("Ellen", "E", "EEE");
//	}
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if(bean!=null) {
			byte[] pass = bean.getPassword();	//��Ʈw��X���K�X
			byte[] temp = password.getBytes();	//�ϥΪ̿�J���K�X
			if(Arrays.equals(pass, temp)) {
				return bean;
			}
		}
		return null;
	}
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			if(newPassword!=null && newPassword.length()!=0) {
				return customerDao.update(newPassword.getBytes(),
						bean.getEmail(), bean.getBirth(), username);
			}
		}
		return false;
	}
}
