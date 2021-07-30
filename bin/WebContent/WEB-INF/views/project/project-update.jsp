<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="cybersoft.java12.util.UrlConst" %>
<head>
    <meta charset="UTF-8">
    <title>Update Project</title>
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
                        <li class="breadcrumb-item active" aria-current="page">Update
                            Project
                        </li>
                    </ol>
                </nav>
                <h1 class="m-0">Update Project</h1>
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
                <form action="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />"
                      method="post">
                    <div class="form-group">
                        <label for="id">Id:</label>
                        <input value="${ project.id }" readonly type="text" class="form-control" name="id" id="id">
                    </div>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input value="${ project.name }" r type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description">${ project.description }</textarea>
                    </div>
                    <div class="form-group">
                        <label for="startdate">START DATE:</label>
                        <input class="form-control" value="${ project.startDate }" type="date" id="startdate" name="startdate">
                    </div>
                    <div class="form-group">
                        <label for="enddate">END DATE:</label>
                        <input class="form-control" value="${ project.endDate }"  type="date" id="enddate" name="enddate">
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
