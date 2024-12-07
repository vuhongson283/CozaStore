<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="component-interfacecus/header.jsp"></jsp:include>
            <style>
                .dot {
                    height: 6px;
                    width: 6px;
                    background-color: black;
                    border-radius: 50%;
                    display: inline-block;
                    margin-right: 5px;
                }


                label {
                    margin-left: 3%;
                    display: block;
                }
            </style>
        </head>
        <body class="animsition">

            <!-- Header -->
        <jsp:include page="component-interfacecus/taskbar.jsp"></jsp:include>

        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>
            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>

            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/xuan2.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    Order Information
                </h2>
            </section>

            <!-- Product -->
            <div class="bg0 m-t-23 p-b-140">
                <div class="content">

                    <div class="container" >
                        <div style="display: flex">
                            <div style="margin-left: 5%">
                                <label style="font-size: 20px;margin-left: 1%;font-weight: bold;">Order Detail</label>
                                <label><span class="dot"></span> Order ID:<span style="font-style: italic;font-weight: bold;"> ${order.order_id} </span></label>
                            <label style="white-space: nowrap"><span class="dot"></span> Recipient's name:<span style="font-style: italic;font-weight: bold;"> ${u.user_name}</span></label>
                            <label style="white-space: nowrap"><span class="dot"></span> Recipient's email:<span style="font-style: italic;font-weight: bold;"> ${u.user_email}</span></label>
                            <label style="white-space: nowrap"><span class="dot"></span> Phone:<span style="font-style: italic;font-weight: bold;"> ${order.phone}</span></label>
                            <label style="white-space: nowrap"><span class="dot"></span> Address:<span style="font-style: italic;font-weight: bold;"> ${order.address}</span></label>
                            <label style="white-space: nowrap"><span class="dot"></span> Order Date:<span style="font-style: italic;font-weight: bold;"> ${order.order_date}</span></label>
                        </div>

                        <div style="margin-left: 40%">
                            <label style="font-size: 20px;margin-left: 1%;font-weight: bold;">Order Summary</label>

                            <label style="white-space: nowrap"><span class="dot"></span> Payment:  <span style="font-style: italic;color: red;"> ${order.payment}</span> </label>
                            <label style="white-space: nowrap"><span class="dot"></span> Total:   <span style="font-style: italic;color: red;"> $${order.order_total}</span> </label>

                            <label style="white-space: nowrap"><span class="dot"></span> Status: 
                                <c:forEach items="${OS}" var="os">


                                    <c:if test="${os.status_id==order.order_status}">
                                        <span style="font-style: italic;color: red;"> ${os.status_name}</span>
                                    </c:if>


                                </c:forEach>   
                            </label><br/>
                        </div>
                    </div>


                    <label style="font-size: 20px;margin-left: 1%;font-weight: bold;">List of product</label> &nbsp;<span style="color: red;font-weight: bold">${mess}</span> <br/>
                    <div class="row">
                        <div class="col-sm-12">

                            <table class="table table-striped" border="1" style="margin-left: 1%">
                                <thead style="font-weight: bold">
                                    <tr>

                                        <td>Product Name</td> 
                                        <td>Size</td>
                                        <td>Image</td>
                                        <td>Quantity</td>  
                                        <td>Price</td>
                                        <td>Subtotal</td>
                                        <c:if test="${order.order_status=='3'}">
                                            <td>Action</td>
                                        </c:if>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach items="${requestScope.listOD}" var="u">
                                        <tr>

                                            <td><a href="ShowProductDetail?pid=${u.product_id}">${u.product_name}</a></td>
                                            <td name="sizeFeedback">${u.size}</td>
                                            <td style="margin: 0"><img  width="80px" src="img_product/${u.img}"></td>
                                            <td name="quantityFeedback">${u.quantity}</td>
                                            <td>${u.price}</td>
                                            <td>$<c:out value="${u.quantity*u.price}"></c:out></td>

                                            <c:choose>
                                                <c:when test="${order.order_status=='3'&&u.status_feedback=='1'}">

                                                    <%-- Link Feedback here --%> 
                                                    <td><a href="addFeedback?pid=${u.product_id}&sizeFeedback=${u.size}&quantityFeedback=${u.quantity}&product_size_id=${u.productSizeId}" >
                                                            <button class="btn btn-outline-success">Feedback</button>
                                                        </a></td>

                                                </c:when>
                                            </c:choose> 

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <form action="ChangeOrderStatusByCus" method="post">
                        <input type="hidden" name="oid" value="${order.order_id}">
                        <input type="hidden" name="uid" value="${u.user_id}">
                        <input type="hidden" name="status" value="4">
                        <c:if test="${order.order_status!='3'&&order.order_status!='4'&&order.order_status!='2'}">
                            <button style="margin-left:  80%" class="btn btn-danger" type="submit"  >Cancel Order</button>

                        </c:if>
                    </form>

                </div> 

                <div style="margin-bottom:  5%">

                </div>

            </div>


            <!-- Footer -->
            <div></div>
            <jsp:include page="component-interfacecus/footer.jsp"></jsp:include>



                <!-- Back to top -->
                <div class="btn-back-to-top" id="myBtn">
                    <span class="symbol-btn-back-to-top">
                        <i class="zmdi zmdi-chevron-up"></i>
                    </span>
                </div>

                <!-- Modal1 -->
            <jsp:include page="component-interfacecus/product-detail-modal.jsp"></jsp:include>


            <jsp:include page="component-interfacecus/script.jsp"></jsp:include>


    </body>
</html>

