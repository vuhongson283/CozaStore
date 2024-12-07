

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
            .sort-button {
                font-size: 5px; /* Điều chỉnh kích thước của nút */
                padding: 2px 5px; /* Điều chỉnh padding để nút trông gọn gàng hơn */
            }
        </style>
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
                                    <form id="filterForm" action="ManageFeedbacks" method="POST">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label>Feedback Content</label>
                                                        <input class="form-control" type="text" name="content" value="${requestScope.content}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Product Name</label>
                                                            <input class="form-control" type="text" name="product_name" value="${requestScope.product_name}">
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Full name</label>
                                                            <input class="form-control" type="text" name="user_name" value="${requestScope.user_name}">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">

                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="status" class="form-select" aria-label="Default select example">
                                                                <option ${requestScope.status.equals("")?"selected":""} value="">All</option>
                                                                <option ${requestScope.status.equals("1")?"selected":""} value="1">Show</option>
                                                                <option ${requestScope.status.equals("0")?"selected":""} value="0">Hide</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Rate Star</label>
                                                            <select name="rate" class="form-select" aria-label="Default select example">
                                                                <option ${requestScope.rate.equals("")?"selected":""} value="">All</option>
                                                                <option ${requestScope.rate.equals("1")?"selected":""} value="1">&#9733</option>
                                                                <option ${requestScope.rate.equals("2")?"selected":""} value="2">&#9733&#9733</option>
                                                                <option ${requestScope.rate.equals("3")?"selected":""} value="3">&#9733&#9733&#9733</option>
                                                                <option ${requestScope.rate.equals("4")?"selected":""} value="4">&#9733&#9733&#9733&#9733</option>
                                                                <option ${requestScope.rate.equals("5")?"selected":""} value="5">&#9733&#9733&#9733&#9733&#9733</option>
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

                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>
                                                            Feedback ID
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(0, 'desc')">▲</button>

                                                            <button class="sort-button" onclick="sortTable(0, 'asc')">▼</button>
                                                        </th>
                                                        <th>
                                                            Feedback Content
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(1, 'desc')">▲</button>
                                                            <button class="sort-button" onclick="sortTable(1, 'asc')">▼</button>
                                                        </th>
                                                        <th>
                                                            Feedback Rate
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(2, 'desc')">▲</button>
                                                            <button class="sort-button" onclick="sortTable(2, 'asc')">▼</button>
                                                        </th>
                                                        <th>Feedback Image</th>
                                                        <th>
                                                            User Name
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(3, 'desc')">▲</button>
                                                            <button class="sort-button" onclick="sortTable(3, 'asc')">▼</button>
                                                        </th>
                                                        <th>
                                                            
                                                            Email
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(4, 'desc')">▲</button>
                                                            <button class="sort-button" onclick="sortTable(4, 'asc')">▼</button>
                                                        </th>
                                                        <th>
                                                            Phone
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(5, 'desc')">▲</button>
                                                            <button class="sort-button" onclick="sortTable(5, 'asc')">▼</button>
                                                        </th>
                                                        <th>
                                                            Product Name
                                                            <br>
                                                            <button class="sort-button" onclick="sortTable(6, 'desc')">▲</button>
                                                            <button class="sort-button" onclick="sortTable(6, 'asc')">▼</button>
                                                        </th>
                                                        <th>
                                                            Status

                                                        </th>
                                                    </tr>



                                                </thead>

                                                <tbody>

                                                    <c:forEach items="${requestScope.list}" var="s">
                                                        <tr class="product">
                                                            <td>${s.getFeedback_id()}</td>                                                              
                                                            <td>${s.getFeedback_des()}</td>
                                                            <td>${s.getFeedback_rate()}&#9733</td>
                                                            <td><img width="100px" src="img_product/${s.getFeedback_img()}"></td>
                                                            <td>${s.getUser_name()}</td>
                                                            <td>${s.getUser_email()}</td>
                                                            <td>${s.getUser_phone()}</td>
                                                            <td>${s.getProduct_name()}</td>
                                                            <td><c:choose>
                                                                    <c:when test="${s.getFeedback_status().equals('1')}">
                                                                        <a href="btnControllerFeedback?feedback_id=${s.getFeedback_id()}&ban=1"><button style="margin: 1px; background-color: rgb(46 255 0 / 36%)" class="btn" >Show</button></a>

                                                                    </c:when>

                                                                    <c:when test="${s.getFeedback_status().equals('0')}">
                                                                        <a href="btnControllerFeedback?feedback_id=${s.getFeedback_id()}&unban=1"><button style="margin: 1px" class="btn btn-danger">Hide</button></a>
                                                                    </c:when>
                                                                </c:choose> 
                                                            </td>

<!--                                                            <td>

                                                                <a href="getFeedbackInformation?id=${s.getFeedback_id()}"><button style="margin: 1px" class="btn btn-primary">View</button></a>
                                                            </td>-->
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
                                                            <a class="page-link" href="ManageFeedbacks?page=${cp-1}&content=${content}&rate=${rate}&product_name=${product_name}&user_name=${user_name}&status=${requestScope.status}" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="ManageFeedbacks?page=${item}&content=${content}&rate=${rate}&product_name=${product_name}&user_name=${user_name}&status=${requestScope.status}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="ManageFeedbacks?page=${cp+1}&content=${content}&rate=${rate}&product_name=${product_name}&user_name=${user_name}&status=${requestScope.status}" aria-label="Next">
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
                <script>
                    function sortTable(columnIndex, sortOrder) {
                        var table, rows, switching, i, x, y, shouldSwitch;
                        table = document.querySelector("table");
                        switching = true;
                        while (switching) {
                            switching = false;
                            rows = table.rows;
                            for (i = 1; i < (rows.length - 1); i++) {
                                shouldSwitch = false;
                                x = rows[i].getElementsByTagName("td")[columnIndex];
                                y = rows[i + 1].getElementsByTagName("td")[columnIndex];
                                if (sortOrder === 'asc') {
                                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                        shouldSwitch = true;
                                        break;
                                    }
                                } else {
                                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                        shouldSwitch = true;
                                        break;
                                    }
                                }
                            }
                            if (shouldSwitch) {
                                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                                switching = true;
                            }
                        }
                    }
                </script>


                <jsp:include page="component/admin/footer.jsp"></jsp:include>
                <!-- Footer End -->
            </div>
        </div>
    </body>


</html>



