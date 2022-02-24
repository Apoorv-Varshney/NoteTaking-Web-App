package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.entities.Note;

public class saveNoteServlet extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public saveNoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// getting title,content
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// parsing date
			SimpleDateFormat sdf = new SimpleDateFormat();  
			Calendar clndr = Calendar.getInstance();
			String currDate = sdf.format(clndr.getTime()); 
			
			// Note class object
			Note note = new Note(title, content, currDate);
			
			// hibernate save()
			Configuration con=new Configuration().configure().addAnnotatedClass(Note.class);
	        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
	        SessionFactory sf=con.buildSessionFactory(reg);
	       
	        Session session=sf.openSession();
	        Transaction tx=session.beginTransaction();
	        session.save(note);
	        tx.commit();
	        session.close();
	        
	        response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();
	        pw.println("<h1 style='text-align:center;'>Note is added Successfully</h1>");
	        pw.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View All Notes</a></h1>");
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
