<%-- 
    Document   : product-detail-view
    Created on : Jan 27, 2024, 6:32:58 PM
    Author     : PCMSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="component-interfacecus/header.jsp"></jsp:include>

        </head>
        <body class="animsition">

            <!-- Header -->
        <jsp:include page="component-interfacecus/taskbar.jsp"></jsp:include>
        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>
            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-01.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    Feedback
                </h2>
            </section>

            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>



            <!-- breadcrumb -->
            <div class="container">
                <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
                    <a href="home" class="stext-109 cl8 hov-cl1 trans-04">
                        Home
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>

                    <a href="filterProduct?supplier=${pd.supplier_id}" class="stext-109 cl8 hov-cl1 trans-04">
                    <c:forEach items="${s}" var="ci">
                        <c:if test="${ci.supplier_id==pd.supplier_id}">
                            ${ci.supplier_name}
                        </c:if>
                    </c:forEach>
                    <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                </a>

                <span class="stext-109 cl4">
                    ${pd.product_name}
                </span>
            </div>
        </div>


        <!-- Product Detail -->
        <section class="sec-product-detail bg0 p-t-65 p-b-60">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-lg-7 p-b-30">
                        <div class="p-l-25 p-r-30 p-lr-0-lg">
                            <div class="wrap-slick3 flex-sb flex-w">
                                <div class="wrap-slick3-dots"></div>
                                <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                                <div class="slick3 gallery-lb">
                                    <div class="item-slick3" data-thumb="img_product/${pd.img}">
                                        <div class="wrap-pic-w pos-relative">
                                            <img src="img_product/${pd.img}" alt="IMG-PRODUCT">

                                            <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="img_product/${pd.img}">
                                                <i class="fa fa-expand"></i>
                                            </a>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6 col-lg-5 p-b-30">
                        <div class="p-r-50 p-t-5 p-lr-0-lg">
                            <h4 class="mtext-105 cl2 js-name-detail p-b-14" >
                                ${pd.product_name}
                            </h4>

                            <span class="mtext-106 cl2" style="color: red; text-decoration: line-through;">
                                $${pd.product_price} 
                            </span> &nbsp;
                            <span class="mtext-106 cl2" >
                                $<c:out value="${pd.product_price*(100-pd.product_saleprice)/100}"></c:out>        
                                </span>

                                <p class="stext-102 cl3 p-t-23">
                                <c:forEach items="${c}" var="ci">
                                    <c:if test="${ci.category_id==pd.category_id}">
                                        ${ci.category_name}
                                    </c:if>
                                </c:forEach>,
                                <c:forEach items="${s}" var="ci">
                                    <c:if test="${ci.supplier_id==pd.supplier_id}">
                                        ${ci.supplier_name}
                                    </c:if>
                                </c:forEach>
                            </p>
                            <div style="color: red; transform: translateY(10px)">${mess}</div>
                            <!--  -->
                            <form action="addToCart" method="">
                                <input type="hidden" name="pid" value="${pd.product_id}">
                                <input type="hidden" name="user_id" value="${sessionScope.acc.user_id}">
                                <div class="p-t-33">
                                    <div class="flex-w flex-r-m p-b-10">
                                        <div class="size-203 flex-c-m respon6">
                                            Size: 
                                        </div>

                                        <div class="size-204 respon6-next">
                                            <div class="rs1-select2 bor8 bg0">
                                                ${requestScope.sizeFeedback}
                                                <div class="dropDownSelect2"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="flex-w flex-r-m p-b-10">

                                        <div class="size-203 flex-c-m respon6">
                                            Quantity: 
                                        </div>

                                        <div class="size-204 respon6-next " id="quantity" >
                                            <div class="rs1-select2 bor8 bg0">
                                                ${requestScope.quantityFeedback}
                                                <div class="dropDownSelect2"></div>
                                            </div>

                                        </div>
                                    </div>


                                </div>
                            </form>
                            <!--  -->
                            <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                                <div class="flex-m bor9 p-r-10 m-r-11">
                                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                                        <i class="zmdi zmdi-favorite"></i>
                                    </a>
                                </div>

                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
                                    <i class="fa fa-facebook"></i>
                                </a>

                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
                                    <i class="fa fa-twitter"></i>
                                </a>

                                <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="bor10 m-t-50 p-t-43 p-b-40">
                    <!-- Tab01 -->
                    <div class="tab01">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item p-b-10">
                                <a class="nav-link active" data-toggle="tab" href="#description" role="tab">Description</a>
                            </li>

                            <li class="nav-item p-b-10">
                                <a class="nav-link" data-toggle="tab" href="#information" role="tab">Additional information</a>
                            </li>

                            <li class="nav-item p-b-10">
                                <a class="nav-link" href="#reviews" data-toggle="tab"  role="tab">Reviews(${requestScope.count})-${requestScope.ave}<i class="zmdi zmdi-star"></i> </a>
                            </li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content p-t-43">
                            <!-- - -->
                            <div class="tab-pane fade show active" id="description" role="tabpanel">
                                <div class="how-pos2 p-lr-15-md">
                                    <p class="stext-102 cl6">
                                        ${pd.des}
                                    </p>
                                </div>
                            </div>

                            <!-- - -->
                            <div class="tab-pane fade" id="information" role="tabpanel">
                                <div class="row">
                                    <div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
                                        <ul class="p-lr-28 p-lr-15-sm">
                                            <li class="flex-w flex-t p-b-7">
                                                <span class="stext-102 cl3 size-205">
                                                    Category:
                                                </span>

                                                <span class="stext-102 cl6 size-206">
                                                    <c:forEach items="${c}" var="ci">
                                                        <c:if test="${ci.category_id==pd.category_id}">
                                                            ${ci.category_name}
                                                        </c:if>
                                                    </c:forEach>
                                                </span>
                                            </li>

                                            <li class="flex-w flex-t p-b-7">
                                                <span class="stext-102 cl3 size-205">
                                                    Brand:
                                                </span>

                                                <span class="stext-102 cl6 size-206">
                                                    <c:forEach items="${s}" var="ci">
                                                        <c:if test="${ci.supplier_id==pd.supplier_id}">
                                                            ${ci.supplier_name}
                                                        </c:if>
                                                    </c:forEach>
                                                </span>
                                            </li>

                                            <li class="flex-w flex-t p-b-7">
                                                <span class="stext-102 cl3 size-205">
                                                    Created Date:
                                                </span>

                                                <span class="stext-102 cl6 size-206">
                                                    ${pd.create_date}
                                                </span>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <!-- - -->
                            <div class="tab-pane fade" id="reviews" role="tabpanel">
                                <div class="row">
                                    <div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
                                        <div class="p-b-30 m-lr-15-sm">
                                            <!-- Review -->
                                            <c:forEach items="${requestScope.list}" var="s">
                                                <div class="flex-w flex-t p-b-68">
                                                    <div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
                                                        <img src="img_product/${s.getFeedback_img()}" alt="AVATAR">
                                                    </div>

                                                    <div class="size-207">
                                                        <div class="flex-w flex-sb-m p-b-17">
                                                            <span class="mtext-107 cl2 p-r-20">
                                                                ${s.getUser_name()}
                                                            </span>

                                                            <span class="fs-18 cl11" id="starRating">
                                                                <c:forEach begin="1" end="${s.getFeedback_rate()}" varStatus="loop">
                                                                    <i class="zmdi zmdi-star"></i>
                                                                </c:forEach>
                                                                <c:forEach begin="${s.getFeedback_rate() + 1}" end="5" varStatus="loop">
                                                                    <i class="zmdi zmdi-star-outline"></i>
                                                                </c:forEach>
                                                            </span>

                                                        </div>

                                                        <p class="stext-102 cl6">
                                                            ${s.getFeedback_des()}
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:forEach>

                                            <!-- Add review -->
                                            <form action="addFeedback" method="post" class="w-full " enctype="multipart/form-data">
                                                <h5 class="mtext-108 cl2 p-b-7">
                                                    Add a review
                                                </h5>
                                                <input type="hidden" name="pid" value="${requestScope.pd.getProduct_id()}">
                                                <input type="hidden" name="product_size_id" value="${requestScope.product_size_id}">

                                                <p class="stext-102 cl6">
                                                    Your email address will not be published. Required fields are marked *
                                                </p>

                                                <div class="flex-w flex-m p-t-50 p-b-23">
                                                    <span class="stext-102 cl3 m-r-16">
                                                        Your Rating
                                                    </span>

                                                    <span class="wrap-rating fs-18 cl11 pointer">
                                                        <i class="item-rating pointer zmdi zmdi-star-outline" value="1"></i>
                                                        <i class="item-rating pointer zmdi zmdi-star-outline" value="2"></i>
                                                        <i class="item-rating pointer zmdi zmdi-star-outline" value="3"></i>
                                                        <i class="item-rating pointer zmdi zmdi-star-outline" value="4"></i>
                                                        <i class="item-rating pointer zmdi zmdi-star-outline" value="5"></i>
                                                        <input class="dis-none" type="number" name="rating">
                                                    </span>
                                                </div>

                                                <div class="row p-b-25">
                                                    <div class="col-12 p-b-5">
                                                        <label class="stext-102 cl3" for="review">Your review</label>
                                                        <textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
                                                    </div>

                                                    <div class="col-sm-6 p-b-5">
                                                        <label class="stext-102 cl3" for="name">Upload Image</label>
                                                        <input type="file" accept="image/*" class="form-control" name="photo" id="recipient-name"><br>
                                                    </div>


                                                </div>

                                                <button type="submit" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
                                                    Submit
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </section>


        <!-- Related Products -->
        <section class="sec-relate-product bg0 p-t-45 p-b-105">
            <div class="container">
                <div class="p-b-45">
                    <h3 class="ltext-106 cl5 txt-center">
                        Related Products
                    </h3>
                </div>

                <!-- Slide2 -->
                <div class="wrap-slick2">
                    <div class="slick2">
                        <c:forEach items="${l5}" var="o">
                            <div class="item-slick2 p-l-15 p-r-15 p-t-15 p-b-15">
                                <!-- Block2 -->
                                <div class="block2">
                                    <div class="block2-pic hov-img0">
                                        <img src="img_product/${o.img}" alt="IMG-PRODUCT" height="320" width="300">

                                        <a href="ShowProductDetail?pid=${o.product_id}" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 ">
                                            View Details
                                        </a>
                                    </div>

                                    <div class="block2-txt flex-w flex-t p-t-14">
                                        <div class="block2-txt-child1 flex-col-l ">
                                            <a href="ShowProductDetail?pid=${o.product_id}" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                                ${o.product_name}
                                            </a>

                                            <span class="stext-105 cl3">
                                                $${o.product_price}
                                            </span>
                                        </div>


                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>

                </div>
            </div>
        </section>


        <!-- Footer -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>



            <!-- Back to top -->
            <div class="btn-back-to-top" id="myBtn">
                <span class="symbol-btn-back-to-top">
                    <i class="zmdi zmdi-chevron-up"></i>
                </span>
            </div>

            <!-- Modal1 -->
        <jsp:include page="component-interfacecus/product-detail-modal.jsp"></jsp:include>


        <jsp:include page="component-interfacecus/script.jsp"></jsp:include>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script>
                function checkquantity() {
                    var sid = document.getElementById("sizeid").value;
                    var pid = "<c:out value='${pd.product_id}' />";

                    $.ajax({
                        type: "get",
                        url: "/SWP-demo3/CheckQuanProduct",
                        data: {
                            sid: sid,
                            pid: pid
                        },
                        success: function (data) {
                            var row = document.getElementById("quantity");
                            row.innerHTML = data;
                        }
                    });
                }
        </script>


    </body>
</html>
