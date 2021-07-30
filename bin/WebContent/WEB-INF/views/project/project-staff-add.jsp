<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.util.UrlConst" %>
<head>
    <meta charset="UTF-8">
    <title>Add New User</title>
</head>
<body>
<!-- Breadcrumb -->
<div class="container page__heading-container">
    <div class="page__heading">
        <div class="d-flex align-items-center">
            <div>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item"><a
                                href="<c:url value="<%=UrlConst.HOME%>" />">Home</a></li>
                        <li class="breadcrumb-item"><a
                                href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD%>" />">Project</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Add
                            Member
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Add member to the project</h1>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumb -->
<div class="container page__container">
    <div class="card card-form">
        <div class="row no-gutters">
            <div class="col-lg-4 card-body">
                <p>
                    <strong class="headings-color">Rules</strong>
                </p>
                <p class="text-muted">There is no rule!</p>
            </div>
            <div class="col-lg-8 card-form__body card-body">
                <form action="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD%>" />"
                      method="post">
                    <div class="form-group">
                        <label for="projectID">ID:</label>
                        <input readonly value=${project.id} type="text" class="form-control" name="projectID" id="projectID">
                    </div>
                    <div class="form-group">
                        <label for="projectName">PROJECT:</label>
                        <input readonly value="${project.name}" type="text" class="form-control" name="projectName" id="projectName">
                    </div>
                    <div class="form-group">
                        <label for="name">MEMBER:</label>
                        <input class="form-control" list="users" name="memberID" id="name">
                        <datalist id="users">
                            <c:forEach var="user" items="${users}">
                                <option value=${user.id}>${user.name}</option>
                            </c:forEach>
                        </datalist>
                    </div>
                    <div class="form-group">
                        <label for="description">Role Description:</label>
                        <textarea class="form-control" id="description" name="description"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="joinDate">JOIN DATE:</label>
                        <input class="form-control" type="date" id="joinDate" name="joinDate">
                    </div>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-primary w-25 justify-content-center"
                                type="submit" class="btn btn-primary">Submit
                        </button>
                        <a class="btn btn-primary w-25 justify-content-center" href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD%>" />">Back to project dashboard</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

