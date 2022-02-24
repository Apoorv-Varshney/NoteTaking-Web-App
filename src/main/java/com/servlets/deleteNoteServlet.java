package com.servlets;

import java.io.IOException;

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

public class deleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteNoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int noteId = Integer.parseInt(request.getParameter("note_id").trim());

			Configuration con = new Configuration().configure().addAnnotatedClass(Note.class);
			ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
			SessionFactory sf = con.buildSessionFactory(reg);

			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();

			Note note = (Note) session.get(Note.class, noteId);
			session.delete(note);

			tx.commit();
			session.close();

			response.sendRedirect("all_notes.jsp");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
