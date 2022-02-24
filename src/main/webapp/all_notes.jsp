<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Query"%>
<%@page
	import="org.hibernate.boot.registry.StandardServiceRegistryBuilder"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="org.hibernate.service.ServiceRegistry"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.entities.Note"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Note Taker : All Notes</title>
<%@include file="all_css.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="navbar.jsp"%>

		<br>
		<h1>All Notes :</h1>

		<div class="row">
			<div class="col-12">

				<%
				Configuration con = new Configuration().configure().addAnnotatedClass(Note.class);
				ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
				SessionFactory sf = con.buildSessionFactory(reg);
				Session ses = sf.openSession();

				Query q = ses.createQuery("from Note");
				List<Note> list = q.list();
				for (Note note : list) {
				%>

				<div class="card mt-3">
					<img class="card-img-top m-4" style="max-width: 100px;"
						src="img/notes.png" alt="Card image cap">
					<div class="card-body px-4">
						<h5 class="card-title"><%=note.getTitle()%></h5>
						<p class="card-text"><%=note.getContent()%></p>
						<p>
						<h5>
							Note Added On :
							<%=note.getAddedDate()%></h5>
						</p>
						<a href="edit_notes.jsp?note_id=<%=note.getId()%>"
							class="btn btn-primary">Update</a> <a
							href="deleteNoteServlet?note_id=<%=note.getId()%>"
							class="btn btn-danger">Delete</a>

					</div>
				</div>

				<%
				}

				ses.close();
				%>

			</div>
		</div>
	</div>
</body>
</html>