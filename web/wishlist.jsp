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
            Wish List
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
                Wish List
            </span>
        </div>
    </div>

    <h5 style="color: orangered; transform: translateX(220px) translateY(60px)">${mess}</h5>

<!-- Shoping Cart -->

<!--        <form class="bg0 p-t-75 p-b-85">-->
<div class="container bg0 p-t-75 p-b-85">
    <div class="row">

        <div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">

            <div class="m-l-25 m-r--38 m-lr-0-xl">
                <div class="wrap-table-shopping-cart">

                    <table class="table-shopping-cart">
                        <tr class="table_head">
                            <!--                                            <th class="column-0"></th>-->

                            <th class="column-1">Product</th>
                            <th class="column-2"></th>
                            <th class="column-3">Brand</th>
                            <th class="column-4 "  style="transform: translateX(-20px)">Tag</th>
                            <th class="column-5">Price</th>
                        </tr>

                        <c:forEach items="${listWL}" var="p">
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
                                    <a href="wishlist?pid=${p.pid}&action=remove">
                                        <div class="how-itemcart1">
                                            <img src="img_product/${p.img}" width="70px"  height="70px" alt="IMG">
                                        </div>
                                    </a>
                                </td>

                                <td class="column-2" ><a href="ShowProductDetail?pid=${p.pid}" style="color:#555 ">${p.pname}</a></td>
                                        <c:forEach items="${listS}" var="s">
                                            <c:if test="${p.supid==s.supplier_id}"><td class="column-3">${s.supplier_name}</td></c:if>
                                </c:forEach>
                                <c:forEach items="${listC}" var="c">
                                    <c:if test="${p.cateid==c.category_id}"> <td class="column-4">${c.category_name}</td> </c:if>
                                </c:forEach> 
                                    <c:if test="${p.price == p.price*(100-p.saleprice)/100}"><td class="column-5">$${p.price} </td></c:if>
                                    <c:if test="${p.price != p.price*(100-p.saleprice)/100}"><td class="column-5"><div style="display: flex;transform: translateX(50px)">

                                            <span class="mtext-106 cl2" style="color: red; text-decoration: line-through; transform: translateX()">
                                                $${p.price}
                                            </span> &nbsp;

                                            <span class="mtext-106 cl2" style="transform: translateX()">
                                                $<c:out value="${p.price*(100-p.saleprice)/100}"></c:out>        
                                                </span>


                                            </div> </td></c:if>


                               
                            </tr>
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

                    <div class="flex-w flex-m m-r-20 m-tb-5">
                        <!--                                <input class="stext-104 cl2 plh4 size-117 bor13 p-lr-20 m-r-10 m-tb-5" type="text" name="coupon" placeholder="Coupon Code">-->
                        <a href="cartDetail">
                            <div class="flex-c-m stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5">
                                View Cart Detail
                            </div>
                        </a>
                    </div>
                </div>
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



