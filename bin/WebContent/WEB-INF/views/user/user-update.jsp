<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cybersoft.java12.util.UrlConst"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
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
								href="<c:url value="<%=UrlConst.USER_DASHBOARD%>" />">User</a></li>
							<li class="breadcrumb-item active" aria-current="page">Update
								New User</li>
						</ol>
					</nav>
					<h1 class="m-0">Update User</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
			<div class="row no-gutters">
				<div class="col-lg-12 card-form__body card-body">
					<form action="<c:url value="<%=UrlConst.USER_UPDATE%>" />"
						method="post">
						<div class="form-group">
							<label for="id">Id:</label> <input readonly value="${user.id}" type="text"
								class="form-control" name="id" id="id">
						</div>
						<div class="form-group">
							<label for="email">Email:</label> <input value="${user.email}" type="email"
								class="form-control" name="email" id="email">
						</div>
						<div class="form-group">
							<label for="name">Name:</label> <input value="${user.name}"  type="text"
								class="form-control" name="name" id="name">
						</div>
						<div class="form-group">
							<label for="phone">Phone:</label> <input value="${user.phone} " type="text"
								class="form-control" name="phone" id="phone">
						</div>
						<div class="form-group">
							<label for="address">Address:</label>
							<textarea type="text" class="form-control" name="address"
								id="address" aria-label="With textarea">${user.address}</textarea>
						</div>
						<div class="form-group">
                        <label for="name">Role:</label>
                        <input class="form-control" list="roles" name="roleId" id="roleId">
                        <datalist id="roles">
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.id}">${role.name}</option>
                            </c:forEach>
                        </datalist>
                    </div>
						<button class="btn btn-primary w-25 justify-content-center"
							type="submit" class="btn btn-primary">SUBMIT</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>