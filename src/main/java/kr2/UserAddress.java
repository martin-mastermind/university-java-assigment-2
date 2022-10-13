package kr2;

public class UserAddress {
	public int id;
	public String second_name, first_name, address, phone;
	
	public UserAddress(int id, String second_name, String first_name, String address, String phone) {
		this.id = id;
		this.second_name = second_name;
		this.first_name = first_name;
		this.address = address;
		this.phone = phone;
	}
	
	private String getEscapedSQL(String str) {
		return "'" + str + "'";
	}
	
	public String prepareInsert() {
		return getEscapedSQL(second_name) + ", " + getEscapedSQL(first_name) + ", " + getEscapedSQL(address) + ", " + getEscapedSQL(phone);
	}
	
	public String prepareUpdate() {
		return "second_name = " + getEscapedSQL(second_name) + ","
				+ " first_name = " + getEscapedSQL(first_name) + ","
				+ " address = " + getEscapedSQL(address) + ","
				+ " phone = " + getEscapedSQL(phone) + " WHERE id = " + id;
	}
	
	public String toHTML() {
		return "<tr data-id=" + id + ">\r\n"
				+ "<td class='item_id'>" + id + "</td>\r\n"
				+ "<td class='item_sname'>" + second_name + "</td>\r\n"
				+ "<td class='item_fname'>" + first_name + "</td>\r\n"
				+ "<td class='item_address'>" + address + "</td>\r\n"
				+ "<td class='item_phone'>" + phone + "</td>\r\n"
				+ "<td><button class='edit'>Изменить</button></td>\r\n"
				+ "<td><button class='remove'>Удалить</button></td>\r\n"
				+ "</tr>";
	}
}
