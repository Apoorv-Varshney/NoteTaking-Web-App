<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Note Taker : Add Notes</title>
<%@include file="all_css.jsp" %>
</head>
<body>
 <div class="container-fluid">
    <%@include file="navbar.jsp" %>
    <div class="container-fluid">
    <br>
    <h1>Please fill your Note details</h1></div>
    <br/>
    <!-- form  -->
    <form action="saveNoteServlet" method="post">
    
  <div class="form-group">
    <label for="title">Notes Title</label>
    <input name="title"
    required type="text" 
    class="form-control" 
    id="title" 
    placeholder="Enter Title Here">
  </div>
  
  <div class="form-group">
    <label for="content">Note Content</label>
    <textarea name="content"
     required class="form-control"
     id="content" 
     style="height: 300px;"
     placeholder="Enter Your Content Here"></textarea>
  </div>
  
  <div class="container text-center">
  <button type="submit" class="btn btn-primary">Add Note</button>
  </div>
  
</form>

    </div>
</body>
</html>