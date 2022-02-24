<%@page
	import="org.hibernate.boot.registry.StandardServiceRegistryBuilder"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.service.ServiceRegistry"%>
<%@page import="org.hibernate.cfg.Configuration"%>
<%@page import="com.entities.Note"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Note Taker : Update Page</title>
<%@include file="all_css.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="navbar.jsp"%>
		<h1>Edit your note details</h1>
		<br>
		<%
		int noteId = Integer.parseInt(request.getParameter("note_id").trim());

		Configuration con = new Configuration().configure().addAnnotatedClass(Note.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);

		Session ses = sf.openSession();
		Note note = (Note) ses.get(Note.class, noteId);
		%>

		<!-- form  -->
		<form action="updateNoteServlet" method="post">

			<input value="<%=note.getId()%>" name="noteId" type="hidden">

			<div class="form-group">
				<label for="title">Notes Title</label> <input name="title" required
					type="text" class="form-control" id="title"
					placeholder="Enter Title Here" value="<%=note.getTitle()%>">
			</div>

			<div class="form-group">
				<label for="content">Note Content</label>
				<textarea name="content" required class="form-control" id="content"
					style="height: 300px;" placeholder="Enter Your Content Here"><%=note.getContent()%></textarea>
			</div>

			<div class="container text-center">
				<button type="submit" class="btn btn-success">Save Changes</button>
			</div>

		</form>

	</div>
</body>
</html>