<%-- 
    Document   : shopping-cart
    Created on : Jan 27, 2024, 7:00:28 PM
    Author     : PCMSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="component-interfacecus/header.jsp"></jsp:include>
            <!--                        <link rel="stylesheet" href="modal/styleprofile1.css" />-->

        </head>
        <body class="animsition">
        <c:set var="priceCart" value="${0.00}"/>
        <c:forEach items="${sessionScope.listOrder}" var="p">
            <c:set var="priceCart" value="${priceCart+p.price*(100-p.sale_price)/100*p.quantity}" />
        </c:forEach>
        <!-- Header -->
        <jsp:include page="component-interfacecus/taskbar.jsp"></jsp:include>
        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>

            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>


            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-01.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    Confirm Order
                </h2>
            </section>	
            <!-- breadcrumb -->
            <div class="container">
                <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg" style="transform: translateY(-15px)">
                    <a href="home" class="stext-109 cl8 hov-cl1 trans-04">
                        Home
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>
                    <a href="cartDetail" class="stext-109 cl8 hov-cl1 trans-04">
                        Shopping Cart
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>
                    <span class="stext-109 cl4">
                        Confirm Order
                    </span>

                </div>
            </div>


            <!-- Shoping Cart -->

            <form class="bg0 p-t-75 p-b-85">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                            <div class="m-l-25 m-r--38 m-lr-0-xl">
                                <div class="wrap-table-shopping-cart">
                                    <table class="table-shopping-cart">
                                        <tr class="table_head">
                                            <!--                                            <th class="column-0"></th>-->

                                            <th class="column-1">Product</th>
                                            <th class="column-2"></th>
                                            <th class="column-3">Price</th>
                                            <th class="column-4">Quantity</th>
                                            <th class="column-5">Total</th>
                                        </tr>

                                    <c:forEach items="${listOrder}" var="p">
                                        <tr class="table_row">
                                            <!--                                            <td class="column-0">
                                                                                            <div class="checkbox-wrapper-39"style="transform: translateX(22px)">
                                                                                                <label>
                                                                                                    <input type="checkbox"/>
                                                                                                    <span class="checkbox"></span>
                                                                                                </label>
                                                                                            </div>
                                                                                        </td>-->
                                            <td class="column-1">

                                                <div class="how-itemcart1">
                                                    <img src="img_product/${p.img}" width="70px"  height="70px" alt="IMG">
                                                </div>

                                            </td>

                                            <td class="column-2">${p.product_name}</td>
                                            <td class="column-3">$ ${p.price*(100-p.sale_price)/100}</td>
                                            <td class="column-4">
                                                <div class="">


                                                    <input style="transform: translateX(75px)" class="mtext-104 cl3 txt-center num-product"  type="number" name="num-product1" value="${p.quantity}" readonly>


                                                </div>


                                            </td>
                                            <td class="column-5">$ ${p.quantity*p.price*(100-p.sale_price)/100}</td>
                                        </tr>
                                    </c:forEach>

                                </table>
                            </div>

                            <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
                                <div class="flex-w flex-m m-r-20 m-tb-5">
                                    <!--                                        <input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder="Coupon Code">
                                    
                                                                            <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
                                                                                Apply coupon
                                                                            </div>-->
                                </div>
                                <a href="cartDetail">
                                <div class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                                    Update Cart
                                </div>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
                        <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                            <h4 class="mtext-109 cl2 p-b-30">
                                Cart Totals
                            </h4>

                            <div class="flex-w flex-t bor12 p-b-13">
                                <!--                                <div class="size-208">
                                                                    <span class="stext-110 cl2">
                                                                        Subtotal:
                                                                    </span>
                                                                </div>
                                
                                                                <div class="size-209">
                                                                    <span class="mtext-110 cl2">
                                                                         $ <fmt:formatNumber pattern="##.##" value="${priceCart}"/>
                                                                    </span>
                                                                </div>-->
                            </div>

                            <div class="flex-w flex-t bor12 p-t-15 p-b-30">
                                <div class="">
                                    <span class="stext-110 cl2">
                                        Delivery address :
                                    </span>
                                </div>

                                <div class="">
                                    <p class="stext-111 cl6 p-t-2">

                                    </p>

                                    <div class="">
                                        <span class="">
                                            You can change your delivery address.
                                        </span>

                                        <div class="rs1-select2 rs2-select2 bor8 bg0 m-b-12 m-t-9">
                                            <!--                                            <select class="js-select2" name="time">
                                                                                            <option>Select a country...</option>
                                                                                            <option>USA</option>
                                                                                            <option>UK</option>
                                                                                        </select>-->
                                            <div class="dropDownSelect2"></div>
                                        </div>
                                        <div class="bor8 bg0 m-b-12">
                                            <input style="color: black" class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="address" placeholder="Username" value="${profileuser.user_name}" readonly>
                                        </div>
                                        <div class="bor8 bg0 m-b-12">
                                            <input style="color: black" class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="state" placeholder="Address" value="${sessionScope.address}" readonly>
                                        </div>

                                        <div class="bor8 bg0 m-b-22">
                                            <input style="color: black" class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="postcode" placeholder="Phone" value="${sessionScope.phone}" readonly>
                                        </div>

                                        <!--                                            <div class="flex-w">
                                                                                        <div class="flex-c-m stext-101 cl2 size-115 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer">
                                                                                            Update Totals
                                                                                        </div>
                                                                                    </div>-->

                                    </div>
                                </div>
                            </div>

                            <div class="flex-w flex-t p-t-27 p-b-33">
                                <div class="size-208">
                                    <span class="mtext-101 cl2">
                                        Total:
                                    </span>
                                </div>

                                <div class="size-209 p-t-1">
                                    <span class="mtext-110 cl2">
                                        $ <fmt:formatNumber pattern="##.##" value="${priceCart}"/>
                                    </span>
                                </div>
                            </div>
                            <span class="stext-110 cl2">
                                Payment Method
                            </span>
                            <input type="button" class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" style="width: 100%;border-radius: 5px;background-color: palevioletred" value="VNPay" data-toggle="modal" data-target="#myModal">
                            <a href="checkOut"><input type="button" class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" style="width: 100%;border-radius: 5px;background-color: #009CFF;transform: translateY(5px)" value="Cash on Delivery"></a>


                        </div>
                    </div>
                </div>
            </div>
        </form>






        <!-- Footer -->
        <jsp:include page="component-interfacecus/footer.jsp"></jsp:include>



            <!-- Back to top -->
            <div class="btn-back-to-top" id="myBtn">
                <span class="symbol-btn-back-to-top">
                    <i class="zmdi zmdi-chevron-up"></i>
                </span>
            </div>
            <!--<div class="modalm" id="modal1" ">
                        <div class="headerm">
                            <div class="title">Pick a picture</div>
                            <button class="btnm close-modalm">&times;</button>
                        </div>
                        <div class="bodym">
                            
                                <div class="mb-3">
                                    <input type="file" accept="image/*" class="form-control" name="photo" id="recipient-name">
                                </div>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-link">Upload</button>
                                </div>
                            
                        </div>
                    </div>
                    <div id="overlaym"></div>
            
                    <script src="modal/script.js"></script>-->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog  modal-dialog-centered" style="transform: translateY(200px)">
                    <div class="modal-content">

                        <!-- Modal Header -->
                        <div class="modal-header">
                            <h4 class="modal-title" style="color: #f74877; text-align: center"><strong>Transaction Notification</strong></h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <h6>To use VNPay for payment, the price of the product will be converted at an exchange rate of <span style="color: red">1 USD = 23,000 VND</span> </h6>
                            <h6> So, the amount you need to pay for the order is <span style="color: red"><fmt:formatNumber pattern="##.##" value="${priceCart*23000}"/></span> VND</h6>
                        <h6 >Do you agree to proceed with the payment?</h6>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                        <a href="vnpayajax"><button type="button" class="btn btn-outline-success " style="" >Accept</button></a>
                    </div>

                </div>
            </div>
        </div>

        <!--===============================================================================================-->	
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <script>
            $(".js-select2").each(function () {
                $(this).select2({
                    minimumResultsForSearch: 20,
                    dropdownParent: $(this).next('.dropDownSelect2')
                });
            })
        </script>
        <!--===============================================================================================-->
        <script src="vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <script>
            $('.js-pscroll').each(function () {
                $(this).css('position', 'relative');
                $(this).css('overflow', 'hidden');
                var ps = new PerfectScrollbar(this, {
                    wheelSpeed: 1,
                    scrollingThreshold: 1000,
                    wheelPropagation: false,
                });

                $(window).on('resize', function () {
                    ps.update();
                })
            });
        </script>
        <!--===============================================================================================-->
        <script src="js/main1.js"></script>

    </body>
</html>
