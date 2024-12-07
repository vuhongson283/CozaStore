

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
        <link rel="stylesheet" href="modal/stylecus.css" />
        <link rel="stylesheet" href="modal/styleviewcus.css" />
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

                                    <form id="filterForm" action="postListMkt" method="post">
                                        <input type="hidden" name="page" value="${requestScope.paging.page}">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group col-sm-6">
                                                        <label>Content</label>
                                                        <input class="form-control" type="text" name="pl_content" value="${pl_content}">
                                                    </div>

                                                    <div class="form-group col-sm-4">
                                                        <label>Author</label>
                                                        <input class="form-control" type="text" name="pl_author" value="${pl_author}">
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Title</label>
                                                            <input class="form-control" type="text" name="pl_title" value="${pl_title}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="pl_status" class="form-select" aria-label="Default select example">
                                                                <option value="" ${pl_status==''?"selected":""} >All</option>                                                                                                                
                                                                <option value="1" ${pl_status=='1'?"selected":""}>Available</option>                                                                                                                
                                                                <option value="0" ${pl_status=='0'?"selected":""}>Disable</option>                                                                                                                
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
                                                <div class="row justify-content-center text-center">
                                                    <!--                                        <div class="form-group mt-4">
                                                                                                <button type="button" class="btn btn-secondary" onclick="resetForm()">Reset</button>
                                                                                            </div>-->
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
                                        <h4>${mess}</h4>
                                        <div class="col-sm-12">
                                            <a class="btn btn-primary" href="AddPost?mod=1">New Post</a>

                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <td>Content</td>
                                                        <td>Title</td> 
                                                        <td>Author</td>
                                                        <td>Status</td>
                                                        <td>Action</td>
                                                        <td>Updated Date</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${requestScope.listB.size() > 0}">
                                                        <c:forEach items="${requestScope.listB}" var="b">

                                                            <tr>
                                                                <td>${b.getContent()}</td>
                                                                <td>${b.getTitle()}</td>
                                                                <td>${b.getAuthorName()} </td>
                                                                <td>                                                                  
                                                                    <c:choose>
                                                                        <c:when test="${b.getStatus().equals('1')}">
                                                                            <a href="buttonController?blog_id=${b.getBlogId()}&ban=1"><button style="margin: 1px; background-color: rgb(46 255 0 / 36%)" class="btn" >Available</button></a>

                                                                        </c:when>

                                                                        <c:when test="${b.getStatus().equals('0')}">
                                                                            <a href="buttonController?blog_id=${b.getBlogId()}&unban=1"><button style="margin: 1px" class="btn btn-danger">Disable</button></a>
                                                                        </c:when>
                                                                    </c:choose> 
                                                                </td>
                                                                <td>
                                                                    <a href="AddPost?blog_id=${b.getBlogId()}&mod=2"><button style="margin: 1px" class="btn btn-primary" >Edit</button></a>
                                                                    <a href="postListMkt?blog_id=${b.getBlogId()}&mod=1"><button style="margin: 1px; background-color: orangered" class="btn btn-primary" >Remove</button></a>
                                                                </td>
                                                                <td>${b.getUpdatedDate()}</td>
                                                            </tr>


                                                        </c:forEach>
                                                    </c:if>
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
                                                            <a class="page-link" href="postListMkt?page=${cp-1}&pl_content=${pl_content}&pl_author=${pl_author}&pl_title=${pl_title}&pl_status=${pl_status}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="postListMkt?page=${item}&pl_content=${pl_content}&pl_author=${pl_author}&pl_title=${pl_title}&pl_status=${pl_status}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="postListMkt?page=${cp+1}&pl_content=${pl_content}&pl_author=${pl_author}&pl_title=${pl_title}&pl_status=${pl_status}" aria-label="Next">
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
        <div class="modalm" id="modal1" style="width: 700px">


        </div>

        <div id="overlaym"></div>

        <script src="modal/script.js"></script> 
        <script type="text/javascript">
            $(document).ready(function () {
                $(".link-detail").on("click", function (e) {
                    e.preventDefault();
                    $("#modal1").load($(this).attr("href"));
                });
            });
        </script>
    </body>


</html>



