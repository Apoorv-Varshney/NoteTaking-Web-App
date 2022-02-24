package com.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entities.Note;

public class updateNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateNoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// getting title,content
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			// parsing date
			SimpleDateFormat sdf = new SimpleDateFormat();
			Calendar clndr = Calendar.getInstance();
			String currDate = sdf.format(clndr.getTime());

			int noteId = Integer.parseInt(request.getParameter("noteId").trim());

			Configuration con = new Configuration().configure().addAnnotatedClass(Note.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
			SessionFactory sf = con.buildSessionFactory(reg);

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();

			Note note = session.get(Note.class, noteId);

			note.setTitle(title);
			note.setContent(content);
			note.setAddedDate(currDate);

			tx.commit();
			session.close();

			response.sendRedirect("all_notes.jsp");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
