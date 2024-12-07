

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Manager</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <style>
            .action-column-button {
                display: flex;
                justify-content: center;
                align-items: center;
            }

        </style>
        <jsp:include page="component/admin/head.jsp"></jsp:include>
        </head>
    <jsp:include page="component/admin/header.jsp"></jsp:include>
        <body>
            <div class="container-fluid position-relative bg-white d-flex p-0">
            <jsp:include page="component/admin/slidebar.jsp"></jsp:include>
                <!-- Content Start -->
                <div class="content">
                    <div class="container">
                    <jsp:include page="component/admin/navbar.jsp"></jsp:include>
                        <!-- Sale & Revenue Start -->
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row">
                                        <c:if test="${requestScope.ERROR != null}">
                                            <p class="text-danger">${requestScope.ERROR}</p>
                                        </c:if>
                                    </div>
                                    <form id="filterForm" action="settingmanagement" method="POST">
                                        <input type="hidden" name="page" value="${1}">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label>Setting Value</label>
                                                        <input class="form-control" type="text" name="value" value="${requestScope.value}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label for="type">Type</label>
                                                            <select name="type" class="form-select" aria-label="Default select example" id="type">
                                                                <option value="" >All</option>
                                                                <c:forEach items="${requestScope.settingCategorys}" var="sc">
                                                                    <option ${requestScope.type == sc.getSettingCatogoryName()?"selected":""} value="${sc.getSettingCatogoryName()}">${sc.getSettingCatogoryName()}</option>  
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="status" class="form-select" aria-label="Default select example">
                                                                <option ${requestScope.status == ''?"selected":""} value="">All</option>
                                                                <option ${requestScope.status == '1'?"selected":""} value="1">Available</option>
                                                                <option ${requestScope.status == '0'?"selected":""} value="0">Not Available</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-sm-2">
                                                <div class="row justify-content-center">
                                                    <div class="form-group mt-4 text-center">
                                                        <button type="submit" class="btn btn-primary">Search</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <a class="btn btn-primary" href="addnewsetting">Add New Setting</a>
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <td>Id</td>
                                                        <td>Value</td> 
                                                        <td>Order</td>
                                                        <td>Type</td>
                                                        <td>Status</td>  
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${requestScope.settingList.size() > 0}">
                                                        <c:forEach items="${requestScope.settingListInPage}" var="s" >
                                                            <tr>
                                                                <td>${s.getSettingId()}</td>
                                                                <td>${s.getSettingValue()}</td>
                                                                <td>${s.getSettingOrder()}</td>
                                                                <td>${s.getSettingCategory()}</td>
                                                                <c:choose>
                                                                    <c:when test="${s.isStatus()}">
                                                                        <td style="color: green;">Available</td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="color: gray">Not Available</td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <td style="display: flex;">
                                                                    <a class="link-detail" href="editsetting?id=${s.getSettingId()}"><button style="margin: 1px" class="btn btn-primary" data-target="#modal1">Edit</button></a>
                                                                </td>
                                                            </tr>

                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <c:if test="${settingList.size()!=0}">
                                        <div class="col-12 pb-1">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-center mb-3">
                                                    <c:if test="${cp!=1}">
                                                        <li class="page-item ">
                                                            <a class="page-link" href="settingmanagement?page=${cp-1}&value=${requestScope.value}&status=${requestScope.status}&type=${requestScope.type}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="settingmanagement?page=${item}&value=${requestScope.value}&status=${requestScope.status}&type=${requestScope.type}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="settingmanagement?page=${cp+1}&value=${requestScope.value}&status=${requestScope.status}&type=${requestScope.type}" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                                <span class="sr-only">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Sale & Revenue End -->
                </div>
                <!-- Footer Start -->
                <jsp:include page="component/admin/footer.jsp"></jsp:include>
                <!-- Footer End -->
            </div>
        </div>
    </body>


</html>



