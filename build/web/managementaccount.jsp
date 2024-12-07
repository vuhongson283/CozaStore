

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
        <link rel="stylesheet" href="modal/style.css" />
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

                                    <form id="filterForm" action="ManageAccount" method="post">
                                        <input type="hidden" name="page" value="${requestScope.paging.page}">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group col-sm-4">
                                                        <label>Gender</label>
                                                        <select name="gender" class="form-select" aria-label="Default select example">
                                                            <option value="" ${gender == ''?"selected":""} >All</option>
                                                            <option value="1" ${gender == '1'?"selected":""}>Male</option>
                                                            <option value="0" ${gender == '0'?"selected":""}>Female</option>
                                                        </select>
                                                    </div>

                                                    <div class="form-group col-sm-4">
                                                        <label>Role</label>
                                                        <select name="role" class="form-select" aria-label="Default select example">
                                                            <option value="" ${role == ''?"selected":""}>All</option>
                                                            <c:forEach items="${listR}" var="r">
                                                                <c:if test="${r.getRole_id()!='1'}">
                                                                    <option value="${r.getRole_id()}" ${role == r.getRole_id()?"selected":""}>${r.getRole_name()}</option>  
                                                                </c:if>

                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group col-sm-4">
                                                        <label>Status</label>
                                                        <select name="status" class="form-select" aria-label="Default select example">
                                                            <option value="" ${status == ''?"selected":""} >All</option>
                                                            <option value="1" ${status == '1'?"selected":""}>Active</option>
                                                            <option value="0"${status == '0'?"selected":""}>Inactive</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>User Name</label>
                                                            <input class="form-control" type="text" name="name" value="${name}">

                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>Email</label>
                                                            <input class="form-control" type="text" name="email" value="${email}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4">
                                                        <div class="form-group">
                                                            <label>Mobile</label>
                                                            <input class="form-control" type="text" name="phone" value="${phone}">
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
                                            <c:if test="${s!=1}">
                                                <button class="btnm btn-primary" data-target="#modal1">Add Account</button>   
                                            </c:if>
                                            <c:if test="${s==1}">
                                                <button class="btnm btn-primary active" data-target="#modal1">Add Account</button>   
                                            </c:if>
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <td>Name</td>
                                                        <td>Gender</td> 
                                                        <td>Email</td>
                                                        <td>Mobile</td>
                                                        <td>Role</td>  
                                                        <td>Status</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody id ="userlist">
                                                    <c:if test="${requestScope.listU.size() > 0}">
                                                        <c:forEach items="${listU}" var="u" varStatus="counter">

                                                            <tr>
                                                                <td>${u.getUser_name()}</td>
                                                                <td>${u.getUser_gender().equals("1")?"Male":"Female"}</td>
                                                                <td>${u.getUser_email()}</td>
                                                                <td>${u.getUser_phone()}</td>

                                                                <c:forEach items="${listR}" var="r">
                                                                    <c:if test="${r.getRole_id().equals(u.getUser_roleId())}">
                                                                        <td>${r.getRole_name()}</td>
                                                                    </c:if>
                                                                </c:forEach>
                                                                <td>
                                                                    <c:choose>
                                                                        <c:when test="${u.user_status==1}">
                                                                            <a href="ManagementCusStatusAcc?email=${u.getUser_email()}&ban=1"><button style="margin: 1px" class="btn btn-success" >Active</button></a>

                                                                        </c:when>

                                                                        <c:when test="${u.user_status==0}">
                                                                            <a href="ManagementCusStatusAcc?email=${u.getUser_email()}&unban=1"><button style="margin: 1px" class="btn btn-dark">Inactive</button></a>

                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td style="display: flex;">
                                                                    <a class="link-detail" href="EditCusByAdmin?email=${u.getUser_email()}"><button style="margin: 1px" class="btn btn-primary" data-target="#modal2">Edit</button></a>
<!--                                                                    <a href="DeleteUserByAdmin?email=${u.getUser_email()}"><button  style="margin: 1px" class="btn btn-danger" >Delete</button></a>-->
                                                                </td>
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
                                                            <a class="page-link" href="ManageAccount?page=${cp-1}&gender=${gender}&role=${role}&status=${requestScope.status}&name=${name}&email=${email}&phone=${phone}"" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="ManageAccount?page=${item}&gender=${gender}&role=${role}&status=${requestScope.status}&name=${name}&email=${email}&phone=${phone}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="ManageAccount?page=${cp+1}&gender=${gender}&role=${role}&status=${requestScope.status}&name=${name}&email=${email}&phone=${phone}"" aria-label="Next">
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
        <c:if test="${s!=1}">
            <div class="modalm" id="modal1">      
            </c:if>
            <c:if test="${s==1}">
                <div class="modalm active" id="modal1">      
                </c:if>
                <div class="headerm">
                    <div class="title">Add Account</div>
                    <button class="btnm close-modalm">&times;</button>
                </div>
                <div class="bodym">
                    <form class="form-group" method="post" action="ManageAccount" >
                        <div class="form-group">
                            <label class="form-label font-weight-bold" for="formControlLg">UserName</label>
                            <input required name="u_name" value="${newus.user_name}"  type="text" id="formControlLg" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label class="form-label font-weight-bold" for="formControlLg">Email</label> <span style="color: red">${u_alert1}</span>
                            <input id="numberInput" type="email" class="form-control" value="${newus.user_email}" name="u_email" oninput="formatNumber(this)">
                        </div>

                        <div class="form-group">
                            <label class="form-label font-weight-bold" for="formControlLg">PassWord</label>
                            <input required name="u_pass" value="${newus.user_pass}" type="text" minlength="6" maxlength="20"  id="formControlLg" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label class="form-label font-weight-bold" for="formControlLg">Address</label>
                            <input required name="u_address" value="${newus.user_address}" type="text" id="formControlLg" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label class="form-label font-weight-bold" for="formControlLg">Phone</label> <span style="color: red">${u_cPhone}</span>
                            <input required name="u_phone" type="text" value="${newus.user_phone}" id="formControlLg" class="form-control" />

                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label>Gender</label>
                                    <select name="u_gender" class="form-select" aria-label="Default select example">
                                        <option value="1" ${newus.user_gender=='1'?"selected":""} >Male</option>
                                        <option value="0" ${newus.user_gender=='0'?"selected":""}>Female</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label>Role</label>
                                    <select name="u_role" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listR}" var="r">
                                            <c:if test="${!(r.getRole_id()=='1')}">
                                                <option value="${r.getRole_id()}" ${newus.user_roleId==r.getRole_id()?"selected":""}>${r.getRole_name()}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <input type="hidden" name="phase" value="2">
                        <br>
                        <button type="submit" class="btn btn-primary">Add account</button>
                    </form>
                </div>
            </div>

            <c:if test="${s!=1}">
                <div id="overlaym" ></div>   
            </c:if>
            <c:if test="${s==1}">
                <div id="overlaym" class="active" ></div>   
            </c:if>



            <div class="modalm" id="modal2" style="width: 700px">


            </div>
            <script src="modal/script.js">
            </script>
            <script type="text/javascript">
                $(document).ready(function () {
                    $(".link-detail").on("click", function (e) {
                        e.preventDefault();
                        $("#modal2").load($(this).attr("href"));
                    });
                });
            </script>
    </body>


</html>



