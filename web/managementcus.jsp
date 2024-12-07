

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

                                    <form id="filterForm" action="customerListController" method="post">
                                        <input type="hidden" name="page" value="${requestScope.paging.page}">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group col-sm-6">
                                                        <label>Full Name</label>
                                                        <input class="form-control" type="text" name="s_name" value="${s_name}">
                                                    </div>

                                                    <div class="form-group col-sm-4">
                                                        <label>Gender</label>
                                                        <select name="s_gender" class="form-select" aria-label="Default select example">
                                                            <option value="" ${s_gender==''?"selected":""} >All</option>
                                                            <option value="1" ${s_gender=='1'?"selected":""}>Male</option>
                                                            <option value="0" ${s_gender=='0'?"selected":""}>Female</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>Email</label>
                                                            <input class="form-control" type="text" name="s_email" value="${s_email}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>Phone</label>
                                                            <input class="form-control" type="text" name="s_phone" value="${s_phone}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="s_status" class="form-select" aria-label="Default select example">
                                                                <option value="" ${s_status==''?"selected":""} >All</option>                                                                                                                
                                                                <option value="1" ${s_status=='1'?"selected":""}>Active</option>                                                                                                                
                                                                <option value="0" ${s_status=='0'?"selected":""}>Inactive</option>                                                                                                                
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
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>Gender</td> 
                                                        <td>Email</td>
                                                        <td>Mobile</td>
                                                        <td>Status</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${requestScope.listU.size() > 0}">
                                                        <c:forEach items="${requestScope.listU}" var="u">
                                                            <c:if test="${!(u.getUser_roleId()=='1')}">
                                                                <tr>
                                                                    <td>${u.getUser_name()}</td>
                                                                    <c:if test="${u.user_gender==1}">
                                                                        <td>Male</td>
                                                                    </c:if>
                                                                    <c:if test="${u.user_gender==0}">
                                                                        <td>Female</td>
                                                                    </c:if>
                                                                    <c:if test="${u.user_gender==null}">
                                                                        <td></td>
                                                                    </c:if>

                                                                    <td>${u.getUser_email()}</td>
                                                                    <td>${u.getUser_phone()}</td>
                                                                    <td>
                                                                        <!--                                                                <div class="checkbox-wrapper-17">
                                                                                                                                            <input  type="checkbox"  id="switch-17" ${u.getUser_status()=='1'?"checked":""} value="${u.getUser_email()}" disabled  /><label for="switch-17"></label>
                                                                                                                                        </div>-->
                                                                        <c:choose>
                                                                            <c:when test="${u.user_status==1}">
                                                                                <a href="ManagementCusStatusAcc?email=${u.getUser_email()}&ban=1"><button style="margin: 1px; background-color: rgb(46 255 0 / 36%)" class="btn" >Activate</button></a>

                                                                            </c:when>

                                                                            <c:when test="${u.user_status==0}">
                                                                                <a href="ManagementCusStatusAcc?email=${u.getUser_email()}&unban=1"><button style="margin: 1px" class="btn btn-danger">De-activate</button></a>

                                                                            </c:when>
                                                                        </c:choose> 
                                                                    </td>
                                                                    <td>
                                                                        <a class="link-detail" href="EditCus?email=${u.getUser_email()}"><button style="margin: 1px" class="btn btn-primary" data-target="#modal1">Edit</button></a>
                                                                    </td>

                                                                </tr>
                                                            </c:if>

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
                                                            <a class="page-link" href="customerListController?page=${cp-1}&s_name=${s_name}&s_status=${s_status}&s_gender=${s_gender}&s_email=${s_email}&s_phone=${s_phone}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="customerListController?page=${item}&s_name=${s_name}&s_status=${s_status}&s_gender=${s_gender}&s_email=${s_email}&s_phone=${s_phone}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="customerListController?page=${cp+1}&s_name=${s_name}&s_status=${s_status}&s_gender=${s_gender}&s_email=${s_email}&s_phone=${s_phone}" aria-label="Next">
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



