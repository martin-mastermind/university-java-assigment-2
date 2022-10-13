package kr2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ApiController() {
        super();
    }
    
    private Database getDB() throws SQLException {
    	return new Database("jdbc:postgresql://localhost:5432/Address", "postgres", "tedy2018");
    }
    
    private UserAddress parseJSON(boolean needId, HttpServletRequest request) throws Exception {
    	String data = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		JSONObject json = (JSONObject) JSONValue.parseWithException(data);
		
		int id = needId ? Integer.parseInt((String) json.get("id")) : 0;
		
		String phone = (String) json.get("phone");
		if(phone.matches("[a-zA-Z]+")) {
			throw new Exception("Ошибка при обработке строки телефона.");
		}
		
		return new UserAddress(id, (String) json.get("second_name"), (String) json.get("first_name"), (String) json.get("address"), phone);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserAddress user = parseJSON(false, request);
			
			Database db = getDB();
			
			db.post(user);
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserAddress user = parseJSON(true, request);
			
			Database db = getDB();
			
			db.update(user);
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String data = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			JSONObject json = (JSONObject) JSONValue.parseWithException(data);
			
			Database db = getDB();

			db.delete(Integer.parseInt((String) json.get("id")));
			
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
