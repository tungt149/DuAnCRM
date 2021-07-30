<%@page import="cybersoft.java12.util.UrlConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="cybersoft.java12.util.UrlConst" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Detail</title>
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
                                href="<c:url value="<%=UrlConst.HOME%>" />"> Dashboard </a></li>
                        <li class="breadcrumb-item"><a href="#">Project</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Project
                            Detail
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Project Detail</h1>
            </div>
            <div class="ml-auto">
                <a href="<c:url value="<%=UrlConst.TASK_ADD%>" />?projectID=${project.id}" class="btn btn-light">
                    <i class="material-icons icon-16pt text-muted mr-1">add</i> Add New Task
                </a>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumb -->

<!-- START BODY -->
<div class="container">
    <div class="row">
        <div class="col-12">
            <h2>Dựa án: ${project.name}</h2>
            <p>${project.description}</p>
        </div>
        <div class="col-12">
            <div class="card card-form">
                <h4 class="text-center">Danh sách thành viên trong dự án</h4>
                <div class="row no-gutters">
                    <table class="table mb-0 thead-border-top-0">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Join date</th>
                            <th>Remove</th>
                        </tr>
                        </thead>
                        <tbody class="list" id="staff02">
                        <c:choose>
                            <c:when test="${members == null}">
                                <tr class="row">
                                    <td class="col-12 text-center">There is no data.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="member" items="${members}">
                                    <tr>
                                        <td>${member.name}</td>
                                        <td>${member.roleDescription}</td>
                                        <td>${member.joinDate}</td>
                                        <td>
                                            <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_REMOVE%>" />?projectID=${member.projectID}&userID=${member.userID}"
                                               class="text-muted"><i class="material-icons">delete</i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-12">
            <div class="card card-form">
                <h4 class="text-center">Danh sách công việc</h4>
                <div class="row no-gutters">
                    <table class="table mb-0 thead-border-top-0">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Assignee</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody class="list" id="tasklist">
                        <c:choose>
                            <c:when test="${tasks == null}">
                                <tr class="row">
                                    <td class="col-12 text-center">There is no data.</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="task" items="${tasks}">
                                    <tr>
                                        <td>${task.name }</td>
                                        <td>${task.description }</td>
                                        <td>${task.startDate }</td>
                                        <td>${task.endDate }</td>
                                        <td>${task.user.name }</td>
                                        <td><span class="badge badge-primary">${task.status.name}</span></td>
                                        <td>
                                            <a href="<c:url value="<%=UrlConst.TASK_UPDATE%>" />?taskID=${task.id}&projectID=${project.id}" class="text-muted">
                                                <i class="fa fa-user-edit"></i>
                                            </a>
                                           <a href="<c:url value="<%=UrlConst.TASK_DELETE%>" />?projectID=${project.id}&taskID=${task.id}"
                                               class="text-muted"><i class="material-icons">delete</i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END BODY -->
</body>
</html>