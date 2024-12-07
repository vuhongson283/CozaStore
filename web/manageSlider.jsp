

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Marketer</title>
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
            <jsp:include page="component/admin/slidebarmarketing.jsp"></jsp:include>
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
                                    <form id="filterForm" action="sliderControl" method="POST">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label>Slider Title</label>
                                                        <input class="form-control" type="text" name="title" value="${requestScope.title}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label>Slider Backlink</label>
                                                        <input class="form-control" type="text" name="backlink" value="${requestScope.backlink}">
                                                    </div>
                                                </div>
                                                <div class="row">

                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="status" class="form-select" aria-label="Default select example">
                                                                <option ${requestScope.status.equals("")?"selected":""} value="">All</option>
                                                                <option ${requestScope.status.equals("1")?"selected":""} value="1">Available</option>
                                                                <option ${requestScope.slide_status.equals("0")?"selected":""} value="0">Not Available</option>
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
                                            <a class="btn btn-primary" href="addSlider.jsp">Add Slider</a>
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <td>Id</td>
                                                        <td>Image</td>
                                                        <td>Slide Title</td>
                                                        <td>Backlink</td>                                                        
                                                        <td>Status</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach items="${requestScope.listS}" var="s">
                                                        <tr class="product">
                                                            <td>${s.getSlide_id()}</td>
                                                            <td><img width="100px" src="images/${s.getSlide_image()}"></td>
                                                            <td>${s.getSlide_title()}</td>
                                                            <td>${s.getBacklink()}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${s.getSlide_status() == 1}">
                                                                        <span style="color: green;">Available</span>
                                                                    </c:when>
                                                                    <c:when test="${s.getSlide_status() == 0}">
                                                                        <span style="color: red;">Not Available</span>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <a href="deleteSlider?id=${s.getSlide_id()}"><button style="margin: 1px; background-color: orangered;" class="btn btn-primary">Delete</button></a>
                                                                <a href="getSliderInfor?id=${s.getSlide_id()}"><button style="margin: 1px" class="btn btn-primary">Edit</button></a>
                                                            </td>
                                                        </tr>    
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <c:if test="${totalUsers!=0}">
                                        <div class="col-12 pb-1">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-center mb-3">
                                                    <c:if test="${cp!=1}">
                                                        <li class="page-item ">
                                                            <a class="page-link" href="sliderControl?page=${cp-1}&status=${requestScope.status}&title=${title}&backlink=${backlink}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="sliderControl?page=${item}&status=${requestScope.status}&title=${title}&backlink=${backlink}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="sliderControl?page=${cp+1}&status=${requestScope.status}&title=${title}&backlink=${backlink}" aria-label="Next">
                                                                <span aria-hidden="true">&raquo;</span>
                                                                <span class="sr-only">Next</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div></c:if>
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



