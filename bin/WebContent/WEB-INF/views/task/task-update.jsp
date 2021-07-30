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
                                href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD%>" />">Task</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Update
                           	Task
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Update Task</h1>
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
                <form action="<c:url value="<%=UrlConst.TASK_UPDATE%>" />"
                      method="post">
                      <div class="form-group">
                        <label for="name">Id:</label>
                        <input value="${task.id }" type="text" class="form-control" name="id" id="id">
                    </div>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input value="${task.name }" type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description">${task.description }</textarea>
                    </div>
                    <div class="form-group">
                        <label for="startdate">START DATE:</label>
                        <input  value="${task.startDate}" class="form-control" type="date" id="startdate" name="startDate">
                    </div>
                    <div class="form-group">
                        <label for="enddate">END DATE:</label>
                        <input  value="${task.endDate }" class="form-control" type="date" id="enddate" name="endDate">
                    </div>
                    <div class="form-group">
                        <label for="name">ASSIGNEE:</label>
                        <input value="${task.user.id }" class="form-control" list="assignees" name="assigneeID" id="assignee">
                        <datalist id="assignees">
                            <c:forEach var="assignee" items="${assignees}">
                                <option value="${assignee.id}">${assignee.name}</option>
                            </c:forEach>
                        </datalist>
                    </div>
                    <div class="form-group">
                        <label for="status">Status</label>
                        <select id="status" name="statusID" data-toggle="select" class="form-control">
                            <option ${task.status.id == 1 ? 'selected' : '' } value="1">TODO</option>
                            <option ${task.status.id == 2 ? 'selected' : '' } value="2">IN PROGRESS</option>
                            <option ${task.status.id == 3 ? 'selected' : '' } value="3">DONE</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="project">Project ID:</label>
                        <input readonly value="${projectID}" class="form-control" type="text" id="project" name="projectID">
                    </div>
                    <button class="btn btn-primary w-25 justify-content-center"
                            type="submit" class="btn btn-primary">Submit
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
