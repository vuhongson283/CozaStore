

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Manager</title>
        <style>
            .status-border-black {
                border: 1px solid black;
            }

            .status-border-blue {
                border: 1px solid blue;
            }

            .status-border-green {
                border: 1px solid green;
            }

            .status-border-red {
                border: 1px solid red;
            }
        </style>

        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link rel="stylesheet" href="modal/stylecus.css" />
        <link rel="stylesheet" href="modal/styleviewcus.css" />
        <link rel="stylesheet" href="modal/style.css" />
        <jsp:include page="component/admin/head.jsp"></jsp:include>
        </head>
    <jsp:include page="component/admin/header.jsp"></jsp:include>
        <body>
            <div class="container-fluid position-relative bg-white d-flex p-0">
            <jsp:include page="component/admin/slidebarsale.jsp"></jsp:include>
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

                                    <form id="filterForm" action="ManageOrderBySale" method="post">
                                        <input type="hidden" name="page" value="${requestScope.paging.page}">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group col-sm-6">
                                                        <label>Status</label>
                                                        <select name="status" class="form-select" aria-label="Default select example">
                                                            <option value="" ${requestScope.status==""?"selected":""}   >All</option>
                                                            <c:forEach items="${listOS}" var="o">
                                                                <option value="${o.status_id}" ${requestScope.status==o.status_id?"selected":""}>${o.status_name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group col-sm-6"> 
                                                        <div class="form-group">
                                                            <label>Customer Name</label>
                                                            <input class="form-control" type="text" name="name" value="${name}">

                                                        </div>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Order ID</label>
                                                            <input class="form-control" type="text" name="oid" value="${oid}">
                                                        </div>


                                                    </div>
                                                    <div class="col-sm-6">
                                                        <label></label></br>
                                                        <div style="display: flex;">
                                                            From:&nbsp;&nbsp; <input type="date" name="dateF" value="${dateF}" style="margin-right: 10px;" > &nbsp;
                                                            To:&nbsp;&nbsp; <input type="date" name="dateT" value="${dateT}" >
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-sm-2">
                                                <div class="row justify-content-center">
                                                    <input type="hidden" name="phase" value="1">
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
                                        <div class="col-sm-12">

                                            <table class="table table-striped">
                                                <thead style="font-weight: bold">
                                                    <tr>
                                                        <td>OrderID</td>
                                                        <td>CustomerName</td> 
                                                        <td>Ordered Date</td>
                                                        <td>Total Cost</td>
                                                        <td>Payment</td>  
                                                        <td>Status</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody id ="userlist">
                                                    <c:if test="${requestScope.listO.size() > 0}">
                                                        <c:forEach items="${listO}" var="u" varStatus="counter">

                                                            <tr>
                                                                <td>${u.order_id}</td>
                                                                <td>${u.user_name}</td>
                                                                <td>${u.order_date}</td>
                                                                <td>$${u.order_total}</td>
                                                                <td>${u.payment}</td>
                                                                <c:forEach items="${listOS}" var="r">
                                                                    <c:if test="${r.status_id.equals(u.order_status)}">
                                                                        <c:if test="${r.status_id.equals('1')}">
                                                                            <td style="color: orange;font-weight: bold" class="">${r.status_name}</td>
                                                                        </c:if>
                                                                        <c:if test="${r.status_id.equals('2')}">
                                                                            <td style="color: blue;font-weight: bold" class="">${r.status_name}</td>
                                                                        </c:if>
                                                                        <c:if test="${r.status_id.equals('3')}">
                                                                            <td style="color: green;font-weight: bold" class="">${r.status_name}</td>
                                                                        </c:if>
                                                                        <c:if test="${r.status_id.equals('4')}">
                                                                            <td style="color: red;font-weight: bold" class="">${r.status_name}</td>
                                                                        </c:if>
                                                                        
                                                                    </c:if>
                                                                </c:forEach>

                                                                <td><a href="ManageOrderStatus?oid=${u.order_id}&uid=${u.user_id}"><button style="margin: 1px" class="btn btn-primary" >Edit</button></a></td>

                                                            </tr>

                                                        </c:forEach>
                                                    </c:if>

                                                </tbody>
                                            </table>
                                        </div>
                                        <c:if test="${requestScope.listO.size() == 0||requestScope.listO.size() ==null}">
                                            <img src="https://th.bing.com/th/id/OIP.9iDRgZz4MXPkEiSELaclXAHaFj?rs=1&pid=ImgDetMain" alt="alt" width="500px" height="500px"/>
                                        </c:if>
                                    </div>

                                    <c:if test="${requestScope.listO.size()!=0}">
                                        <div class="col-12 pb-1">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-center mb-3">
                                                    <c:if test="${cp!=1}">
                                                        <li class="page-item ">
                                                            <a class="page-link" href="ManageOrderBySale?page=${cp-1}&status=${requestScope.status}&name=${name}&oid=${oid}&dateF=${dateF}&dateT=${dateT}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="ManageOrderBySale?page=${item}&status=${requestScope.status}&name=${name}&oid=${oid}&dateF=${dateF}&dateT=${dateT}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="ManageOrderBySale?page=${cp+1}&status=${requestScope.status}&name=${name}&oid=${oid}&dateF=${dateF}&dateT=${dateT}" aria-label="Next">
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
        <c:if test="${s!=1}">
            <div class="modalm" id="modal1">      
            </c:if>



            <c:if test="${s!=1}">
                <div id="overlaym" ></div>   
            </c:if>
            <c:if test="${s==1}">
                <div id="overlaym" class="active" ></div>   
            </c:if>



            <div class="modalm" id="modal1" style="width: 1000px">

            </div>

            <script src="modal/script.js">
            </script>

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



