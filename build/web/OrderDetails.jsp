

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Manager</title>
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
                    <jsp:include page="component/admin/navbar.jsp"></jsp:include> <br/>
                        <label style="font-size: 20px;margin-left: 1%;font-weight: bold;">Order Detail</label><br/>
                        <label><span class="dot"></span> Order ID:<span style="font-style: italic;font-weight: bold;"> ${order.order_id} </span></label><br/>
                    <label><span class="dot"></span> Customer Name:<span style="font-style: italic;font-weight: bold;"> ${u.user_name}</span></label><br/>
                    <label><span class="dot"></span> Customer Email:<span style="font-style: italic;font-weight: bold;"> ${u.user_email}</span></label><br/>
                    <label><span class="dot"></span> Phone:<span style="font-style: italic;font-weight: bold;"> ${order.phone}</span></label><br/>
                    <label><span class="dot"></span> Address:<span style="font-style: italic;font-weight: bold;"> ${order.address}</span></label><br/>
                    <label><span class="dot"></span> Order Date:<span style="font-style: italic;font-weight: bold;"> ${order.order_date}</span></label><br/>
                    <label style="font-size: 20px;margin-left: 1%;font-weight: bold;">Order Summary</label><br/>

                    <label><span class="dot"></span> Payment:  <span style="font-style: italic;color: red;"> ${order.payment}</span> </label><br/>
                    <label><span class="dot"></span> Total:   <span style="font-style: italic;color: red;"> $${order.order_total}</span> </label><br/>
                    <form action="ManageOrderStatus" method="post">
                        <label><span class="dot"></span> Status: 
                            <select id="id" name="status" style="color: red;font-style: italic;" ${order.order_status=="3"||order.order_status=="4"?"disabled":""}>
                                <c:forEach items="${OS}" var="os">
                                    
                                    <option value="${os.status_id}" ${os.status_id==order.order_status?"selected":""}> ${os.status_name}</option>

                                    
                                </c:forEach>   
                            </select>
                        </label><br/>
                        <input type="hidden" name="oid" value="${order.order_id}">
                        <input type="hidden" name="uid" value="${u.user_id}">
                        

                        <label style="font-size: 20px;margin-left: 1%;font-weight: bold;">List of product</label> &nbsp;<span style="color: red;font-weight: bold">${mess}</span> <br/>
                        <div class="row">
                            <div class="col-sm-12">

                                <table class="table table-striped" border="1" style="margin-left: 1%">
                                    <thead style="font-weight: bold">
                                        <tr>
                                            <td>Product ID</td>
                                            <td>Product Name</td> 
                                            <td>Size</td>
                                            <td>Image</td>
                                            <td>Quantity</td>  
                                            <td>Price</td>
                                            <td>Subtotal</td>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${requestScope.listOD}" var="u">
                                            <tr>
                                                <td>${u.product_id}</td>
                                                <td>${u.product_name}</td>
                                                <td>${u.size}</td>
                                                <td style="margin: 0"><img  width="80px" src="img_product/${u.img}"></td>
                                                <td>${u.quantity}</td>
                                                <td>${u.price}</td>
                                                <td>$<c:out value="${u.quantity*u.price}"></c:out></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>

                        <button style="margin-left:  80%" class="btn btn-primary" type="submit" ${order.order_status=="3"||order.order_status=="4"?"disabled":""} >Save</button>
                    </form>
                    
                </div>
                <!-- Footer Start -->
                <jsp:include page="component/admin/footer.jsp"></jsp:include>
                <!-- Footer End -->
            </div>
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



