<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>


<tags:adminPageTemplate titulo="Area administrativa">



	
	<div class="container ">
		<h1 class="mt-4">Area administrativa</h1>
		<c:if test="${not empty status }">${status }</c:if>

	</div>


	<!-- 	TODO desenvolver area administrativa  -->


</tags:adminPageTemplate>