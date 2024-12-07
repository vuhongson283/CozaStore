

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
            .product-row {
                position: relative;
            }
            .size-info {
                display: none;
                position: absolute;
                bottom: -295px; /* Adjust this value to position the dropdown */
                left: 1100px;
                background-color: red; /* Set the desired background color (not transparent) */
                padding: 10px;
                border: 1px solid #ccc;
                z-index: 1;
            }

            .product-row:hover .size-info {
                display: block;
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
                                    <form id="filterForm" action="manageproduct" method="POST">
                                        <div class="row">
                                            <div class="col-sm-10">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <label>Product Name</label>
                                                        <input class="form-control" type="text" name="search" value="${requestScope.search}">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Category</label>
                                                            <select name="category" class="form-select" aria-label="Default select example">
                                                                <option value="">All</option>
                                                                <c:forEach items="${requestScope.categories}" var="c">
                                                                    <option ${requestScope.category.equals(c.getCategory_name())?"selected":""} value="${c.getCategory_name()}">${c.getCategory_name()}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <div class="form-group">
                                                            <label>Status</label>
                                                            <select name="status" class="form-select" aria-label="Default select example">
                                                                <option ${requestScope.status.equals("")?"selected":""} value="">All</option>
                                                                <option ${requestScope.status.equals("1")?"selected":""} value="1">Available</option>
                                                                <option ${requestScope.status.equals("0")?"selected":""} value="0">Not Available</option>
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
                                            <a class="btn btn-primary" href="addproduct">Add Product</a>
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <td>Id</td>
                                                        <td>Image</td>
                                                        <td>Product Name</td>
                                                        <td>Category</td> 
                                                        <td>Price</td>
                                                        <td>Sale Price</td>
                                                        <td>Supplier</td>  
                                                        <td>Status</td>
                                                        <td>Action</td>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${requestScope.products.size() > 0}">
                                                        <c:forEach items="${requestScope.products}" var="p">
                                                            <tr class="product-row">
                                                                <td >${p.getProduct_id()}</td>
                                                                <td ><img width="100px" src="img_product/${p.getImg()}"></td>
                                                                <td >${p.getProduct_name()}</td>

                                                                <td >
                                                                    <c:forEach items="${requestScope.categories}" var="c">
                                                                        <c:if test="${p.category_id == c.category_id}">
                                                                            ${c.category_name}
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </td>
                                                                <td >${p.getProduct_price()}</td>
                                                                <td >${p.getProduct_saleprice()}</td>                                                                
                                                                <td >
                                                                    <c:forEach items="${requestScope.suppliers}" var="s">
                                                                        <c:if test="${p.supplier_id == s.supplier_id}">
                                                                            ${s.supplier_name}
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </td>
                                                                <c:choose>
                                                                    <c:when test="${p.getStatus()}">
                                                                        <td style="color: green;" >Available</td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td style="color: gray" >Not Available</td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <td >
                                                                    <a href="productDetail?id=${p.getProduct_id()}"><button style="margin: 1px" class="btn btn-primary">Edit</button></a>
                                                                </td>
                                                                <td> </td>
                                                                <td> </td>
                                                                <td class="size-info" style="background-color: white;">
                                                                    <c:forEach items="${p.getProductSizes()}" var="ps" varStatus="loop" >
                                                                        size ${loop.index + 36}: ${ps.getQuantity()}<br>
                                                                    </c:forEach>  
                                                                </td>
                                                            </tr>
                                                            </td>
                                                            </tr>    

                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>

                                            </table>
                                        </div>
                                    </div>
                                    <c:if test="${totalProductsInPage!=0}">
                                        <div class="col-12 pb-1">
                                            <nav aria-label="Page navigation">
                                                <ul class="pagination justify-content-center mb-3">
                                                    <c:if test="${cp!=1}">
                                                        <li class="page-item ">
                                                            <a class="page-link" href="manageproduct?page=${cp-1}&search=${search}&status=${requestScope.status}&category=${category}"" aria-label="Previous">
                                                                <span aria-hidden="true">&laquo;</span>
                                                                <span class="sr-only">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="1" end="${endPage}" var="item">
                                                        <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                            <a class="page-link" href="manageproduct?page=${item}&search=${search}&status=${requestScope.status}&category=${category}">${item}</a>                               
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${cp!=endPage}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="manageproduct?page=${cp+1}&search=${search}&status=${requestScope.status}&category=${category}"" aria-label="Next">
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



