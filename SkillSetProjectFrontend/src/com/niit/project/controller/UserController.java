package com.niit.project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.niit.project.model.Emp;
import com.niit.project.model.User;
import com.niit.project.repository.UserRepository;

public class UserController extends HttpServlet {

	UserRepository userRepository = new UserRepository();

	private static final long serialVersionUID = 1L;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userOperations = request.getServletPath();
		// System.out.println(userOperations);

		switch (userOperations) {
		case "/insert":
			try {
				createUser(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case "/edit":
			try {
				userEditPage(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/activateuser":
			activateUser(request, response);
			break;
		case "/login":
			login(request, response);
			break;
		case "/validateuser":
			validate(request, response);
			break;
		case "/profile":
			try {
				checkProfile(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "/findprofiles":
			try {
				findProfile(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			try {
				getUserList(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void activateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("user_id"));
		User user = new User();
		userRepository.approvingUser(id, user);
		response.sendRedirect("activateuser");
		
	}

	private void findProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String fname = request.getParameter("fname");
		System.out.println("Fname is: " + fname);
		List<User> userList = userRepository.getUserByUserNameForManager(fname);
		for(User u:userList) {
			System.out.println("query output"+u.getFname());
		}
		request.setAttribute("userList", userList);
		RequestDispatcher dispatcher =
		request.getRequestDispatcher("/WEB-INF/views/findprofiles.jsp");
		//response.sendRedirect("findprofiles");
		dispatcher.forward(request, response);
	}

	private void checkProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// userRepository.getUser();
		System.out.println("---");
		HttpSession session = request.getSession();
		String n = (String) session.getAttribute("username");
		User user = userRepository.getUserByUserName(n);
		System.out.println("id is: " + user.getUser_id());
		String usertype = user.getUser_type();
		System.out.println("User type is: " + usertype);
		System.out.println(n);
		request.setAttribute("user", user);
		if (userRepository.getUserByUserName(n) != null && usertype.equalsIgnoreCase("HR")) {
			System.out.println("HR method got executed");
			//userRepository.getUserByUserNameForManager();
			List<User> userList = userRepository.userListForApproval();
			request.setAttribute("userList", userList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/profilestoapprove.jsp");
			dispatcher.forward(request, response);
		} else if (usertype.equalsIgnoreCase(usertype)) {
			System.out.println("Manager method got executed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/findprofiles.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("Common method got executed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/profile.jsp");
			dispatcher.forward(request, response);
			String message = "Error";
			request.setAttribute("message", message);
		}
	}

	private void validate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		System.out.println("Stored from html" + username);
		HttpSession session = request.getSession();
		session.setAttribute("username", username);

		String pw = request.getParameter("password");
		User user = userRepository.getUserByUserName(username);
		int status = user.getStatus();
		System.out.println("Status is:" + status);
		if (userRepository.getUserByUserName(username) != null) {
			response.sendRedirect("profile");
		} else {
			/*
			 * System.out.println("Not able to register"); RequestDispatcher dispatcher =
			 * request.getRequestDispatcher("/WEB-INF/views/error.jsp");
			 * dispatcher.forward(request, response);
			 */
			/*
			 * PrintWriter out = response.getWriter();
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('User or password incorrect');");
			 * out.println("</script>");
			 */
			String message = "Invalid Username & Password";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);

	}

	private void getUserList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// userRepository.getUser();
		System.out.println("---");
		List<User> userList = userRepository.listAllUsers();
		request.setAttribute("userList", userList);
		
		/*List<User> userList2 = userRepository.getUserByUserNameForManager();
		request.setAttribute("userList2", userList2);*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userList.jsp");
		dispatcher.forward(request, response);
		String message = "Error";
		request.setAttribute("message", message);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int age = Integer.parseInt(request.getParameter("age"));

		User user = new User(id, fname, lname, age);
		userRepository.editUser(id, user);
		;
		response.sendRedirect("list");

	}

	private void userEditPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = request.getParameter("user_id");
		System.out.println(id);
		// int user_id = Integer.parseInt(request.getParameter("user_id"));
		int user_id = Integer.parseInt(id);
		User user = userRepository.getUserById(user_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userList.jsp");
		request.setAttribute("user", user);
		dispatcher.forward(request, response);

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));

		// User user = new User();
		userRepository.deleteUser(user_id);
		response.sendRedirect("list");

	}

	private void createUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		User user = new User();
		user.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		user.setAge(Integer.parseInt(request.getParameter("age")));
		Date d = new Date();
		user.setDob(new java.sql.Date(d.getTime()));
		user.setEmail_id(request.getParameter("email_id"));
		user.setPhone_number(request.getParameter("phone_number"));
		user.setTotal_yrs_of_experience(1);
		user.setTotal_nm_of_stdnts_handled(1);

		user.setPassword(request.getParameter("password"));
		user.setManager_id(122);
		user.setUser_type(request.getParameter("user_type"));
		String usertype = request.getParameter("user_type");
		System.out.println(usertype);
		System.out.println(request.getParameter("phone_number"));
		String ph = request.getParameter("phone_number");
		int l = ph.length();
		System.out.println("length" + l);
		if (ph.length() == 10) {
			userRepository.addUser(user);
			System.out.println("here");
			response.sendRedirect("list");
		} else {

			String m = "Check the phone number";
			System.out.println("message" + m);
			request.setAttribute("m", m);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userList.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
