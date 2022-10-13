package kr2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Database db = new Database("jdbc:postgresql://localhost:5432/Address", "postgres", "tedy2018");
			
			ArrayList<UserAddress> users = db.select();
			Collections.sort(users, new Comparator<UserAddress>() {
			     public int compare(UserAddress o1, UserAddress o2) {
			    	 return o1.id - o2.id;
			     }
			});
			
			String table = "";
			
			for(UserAddress user : users) {
				table += user.toHTML();
			}
			
			request.setAttribute("table", table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
         
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
