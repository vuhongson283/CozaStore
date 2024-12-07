<%-- 
    Document   : shopping-cart
    Created on : Jan 27, 2024, 7:00:28 PM
    Author     : PCMSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="component-interfacecus/header.jsp"></jsp:include>

    </head>

<c:set var="priceCart" value="${0.00}"/>
<c:forEach items="${listPC}" var="p">
    <c:set var="priceCart" value="${priceCart+p.price*(100-p.sale_price)/100*p.quantity}" />
</c:forEach>
<!-- Header -->
<jsp:include page="component-interfacecus/taskbar.jsp"></jsp:include>
<jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>

    <!-- Cart -->
<jsp:include page="component-interfacecus/cart.jsp"></jsp:include>


    <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-01.jpg');">
        <h2 class="ltext-105 cl0 txt-center">
            Shopping Cart
        </h2>
    </section>	
    <!-- breadcrumb -->
    <div class="container">
        <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg" style="transform: translateY(-15px)">
            <a href="home" class="stext-109 cl8 hov-cl1 trans-04">
                Home
                <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
            </a>

            <span class="stext-109 cl4">
                Shoping Cart
            </span>
        </div>
    </div>

    <h5 style="color: orangered; transform: translateX(220px) translateY(60px)">${mess}</h5>

    <!-- Shoping Cart -->

    <!--        <form class="bg0 p-t-75 p-b-85">-->
    <div class="container bg0 p-t-75 p-b-85">
        <div class="row">

            <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
                <form action="updateCart" method="">
                    <div class="m-l-25 m-r--38 m-lr-0-xl">
                        <div class="wrap-table-shopping-cart">

                            <table class="table-shopping-cart">
                                <tr class="table_head">
                                    <!--                                            <th class="column-0"></th>-->

                                    <th class="column-1">Product</th>
                                    <th class="column-2"></th>
                                    <th class="column-3">Price</th>
                                    <th class="column-4"><div style="transform: translateX(-30px)">Quantity</div></th>
                                    <th class="column-5"><div style="transform: translateX(-30px)">Total</div></th>
                                    
                                </tr>

                            <c:forEach items="${listPC}" var="p">
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
                                        <a href="removeItemShoppingCart?user_id=${sessionScope.acc.user_id}&pid=${p.product_id}&sid=${p.size_id}">
                                            <div class="how-itemcart1">
                                                <img src="img_product/${p.img}" width="70px"  height="70px" alt="IMG">
                                            </div>
                                        </a>
                                    </td>

                                    <td class="column-2"><a>${p.product_name}</a></td>
                                    <td class="column-3">$ ${p.price*(100-p.sale_price)/100}</td>
                                    <td class="column-4">
                                        <div class="wrap-num-product flex-w m-l-auto m-r-0">
                                            <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                <i class="fs-16 zmdi zmdi-minus"></i>
                                            </div>

                                            <input class="mtext-104 cl3 txt-center num-product" id="inputValue" type="number" name="num-product1" value="${p.quantity}" >

                                            <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                <i class="fs-16 zmdi zmdi-plus"></i>
                                            </div>
                                        </div>


                                    </td>
                                    <td class="column-5" >$ ${p.quantity*p.price*(100-p.sale_price)/100} <a href="wishlist?pid=${p.product_id}&action=add" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                                        <i class="zmdi zmdi-favorite" style="transform: translateX(25px)"></i>
                                    </a></td>
                                    
                            </c:forEach>

                        </table>
                    </div>

                    <div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
                        <div class="flex-w flex-m m-r-20 m-tb-5">
                            <!--                                <input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder="Coupon Code">-->
                            <a href="shop">
                                <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
                                    Continue shopping
                                </div>
                            </a>
                        </div>

                        <button type="submit" class="flex-c-m stext-101 cl2 size-119 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">
                            Update Cart
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
            <div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
                <h4 class="mtext-109 cl2 p-b-30">
                    Cart Totals
                </h4>


                <hr>
                <form action="confirmOrder" method="">
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

                                    <div class="dropDownSelect2"></div>
                                </div>
                                <div class="bor8 bg0 m-b-12">
                                    <input style="color: black" class="stext-111 cl8 plh3 size-111 p-lr-15" type="text"  placeholder="Username" value="${profileuser.user_name}" readonly>
                                </div>
                                <div style="color: red;font-size: 12px">*The recipient's name will be taken from the user's profile</div>
                                <div class="bor8 bg0 m-b-12">
                                    <input style="color: black" class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="address" placeholder="Address" value="${profileuser.user_address}">
                                </div>

                                <div class="bor8 bg0 m-b-22">
                                    <input style="color: black" class="stext-111 cl8 plh3 size-111 p-lr-15" type="text" name="phone" placeholder="Phone" value="${profileuser.user_phone}">
                                </div>


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
<!--                                <input type="text" name="amount" value="${priceCart}" hidden>-->
                                $ <fmt:formatNumber pattern="##.##" value="${priceCart}"/>
                            </span>
                        </div>
                    </div>

                    <div>
                        <button class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
                            Proceed to Checkout
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--        </form>-->








<!-- Footer -->
<jsp:include page="component-interfacecus/footer.jsp"></jsp:include>



<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
    <span class="symbol-btn-back-to-top">
        <i class="zmdi zmdi-chevron-up"></i>
    </span>
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



