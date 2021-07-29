<%@page import="cybersoft.java12.util.UrlConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="cybersoft.java12.util.UrlConst" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Dashboard</title>
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
                            Dashboard
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Project Dashboard</h1>
            </div>
            <div class="ml-auto">
                <a href="<c:url value="<%=UrlConst.PROJECT_ADD %>" />" class="btn btn-light"><i
                        class="material-icons icon-16pt text-muted mr-1">add</i>
                    Add New Project
                </a>
            </div>
        </div>
    </div>
</div>
<!-- End Breadcrumb -->

<!-- START BODY -->
<div class="container">
    <div class="card card-form">
        <div class="row no-gutters">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Add Member</th>
                    <th>Owner</th>
                    <th></th>
                </tr>
                </thead>
                <tbody class="list" id="staff02">
                <c:choose>
                    <c:when test="${projects == null}">
                        <tr class="row">
                            <td class="col-12 text-center">There is no data.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="project" items="${projects}">
                            <tr>
                                <td><a href="<c:url value="<%=UrlConst.PROJECT_DETAIL%>" />?id=${project.id}">
                                    ${project.name}
                                </a></td>
                                <td>${project.description }</td>
                                <td>${project.startDate }</td>
                                <td>${project.endDate }</td>
                                <td class="text-center">
                                    <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD%>" />?id=${project.id}"
                                       class="text-muted">
                                        <i class="fa fa-user-plus"></i>
                                    </a>
                                </td>
                                <td>${project.owner.name}</td>
                                <td>
                                    <a href="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />?id=${project.id}"
                                       class="text-muted">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    <a href="<c:url value="<%=UrlConst.PROJECT_DELETE%>" />?id=${project.id}"
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
<!-- END BODY -->
</body>
</html>