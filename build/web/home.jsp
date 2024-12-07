<%-- 
    Document   : home
    Created on : Jan 27, 2024, 10:20:10 AM
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



            <!-- Sidebar -->
        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>



            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>




            <!-- Slider -->
            <section class="section-slide">
                <div class="wrap-slick1 rs2-slick1">
                    <div class="slick1">
                    <c:forEach items="${listSlide}" var="s">
                        <div class="item-slick1 bg-overlay1" style="background-image: url(img_product/${s.slide_image});"  data-thumb="img_product/${s.slide_image}" data-caption="${s.slide_title}">
                            <div class="container h-full">
                                <div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
                                    <div class="layer-slick1 animated visible-false" data-appear="fadeInDown" data-delay="0">
                                        <span class="ltext-202 txt-center cl0 respon2">
                                            COZA STORE
                                        </span>
                                    </div>

                                    <div class="layer-slick1 animated visible-false" data-appear="fadeInUp" data-delay="800">
                                        <h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
                                            ${s.slide_title}
                                        </h2>
                                    </div>

                                    <div class="layer-slick1 animated visible-false" data-appear="zoomIn" data-delay="1600">
                                        <a href="${s.backlink}" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn2 p-lr-15 trans-04">
                                            Shop Now
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>

            </div>
        </section>


        <input type="hidden" name="user_id" value="${sessionScope.acc.user_id}">
        <!-- Banner -->
        <div class="p-b-10">
            <h3 class="ltext-103 cl5" style=" text-align: center;transform: translateY(50px)">
                Blog
            </h3>
        </div>
        <div class="sec-banner bg0 p-t-95 p-b-55">
            <div class="container">
                <div class="row">
                    <c:forEach items="${listB1}" var="b">
                        <div class="col-md-6 p-b-30 m-lr-auto">
                            <!-- Block1 -->
                            <div class="block1 wrap-pic-w">
                                <img src="images/${b.categoryBlogImg}" alt="IMG-BANNER" width="406.800px" height="350px">

                                <a href="manageblog?category=${b.categoryBlogId}" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
                                    <div class="block1-txt-child1 flex-col-l">
                                        <div class="block1-txt-child2 p-b-4 trans-05">
                                            <span class="block1-name ltext-102 trans-04 p-b-8">
                                                <div style="color: black">${b.categoryBlogName}</div>
                                            </span>
                                        </div>


                                    </div>

                                    <div class="block1-txt-child2 p-b-4 trans-05">
                                        <div class="block1-link stext-101 cl0 trans-09">
                                            Read more
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>

                    <c:forEach items="${listB2}" var="b">
                        <div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
                            <!-- Block1 -->
                            <div class="block1 wrap-pic-w">
                                <img src="images/${b.categoryBlogImg}" alt="IMG-BANNER" width="200px" height="240px">

                                <a href="manageblog?category=${b.categoryBlogId}" class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
                                    <div class="block1-txt-child1 flex-col-l">

                                        <div class="block1-txt-child2 p-b-4 trans-05">
                                            <span class="block1-name ltext-102 trans-04 p-b-8">
                                                <div style="color: black">${b.categoryBlogName}</div>
                                            </span>
                                        </div>

                                    </div>

                                    <div class="block1-txt-child2 p-b-4 trans-05">
                                        <div class="block1-link stext-101 cl0 trans-09">
                                            Read more
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>


        <!-- Product -->
        <section class="bg0 p-t-23 p-b-130">
            <div class="container">
                <div class="p-b-10">
                    <h3 class="ltext-103 cl5" style="color: orangered; text-align: center;padding-bottom: 40px">
                        New Products
                    </h3>
                </div>

                <div class="flex-w flex-sb-m p-b-52">
                    <div class="flex-w flex-l-m filter-tope-group m-tb-10">
                        <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1" data-filter="*">
                            All Products
                        </button>

                        <c:forEach items="${listS}" var="s">
                            <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".${s.supplier_id}">
                                ${s.supplier_name}
                            </button>
                        </c:forEach>
                    </div>

                    <div class="flex-w flex-c-m m-tb-10">
                        <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
                            <i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
                            <i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                            <div style="color: black">Filter</div>
                        </div>

                        <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
                            <i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
                            <i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
                            Search
                        </div>
                    </div>

                    <!-- Search product -->
                    <div class="dis-none panel-search w-full p-t-10 p-b-15">
                        <form action="search-product" method="get">

                            <div class="bor8 dis-flex p-l-15">
                                <button type="submit" class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
                                    <i class="zmdi zmdi-search"></i>
                                </button>

                                <input class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="search" placeholder="Search">

                            </div>	
                        </form>
                    </div>

                    <!-- Filter -->
                    <div class="dis-none panel-filter w-full p-t-10">
                        <form action="filterProduct" method="get">

                            <div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
                                <div class="filter-col1 p-r-15 p-b-27">
                                    <div class="mtext-102 cl2 p-b-15">
                                        Sort By
                                    </div>

                                    <ul>
                                        <div style="line-height: 0.7">
                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="sort" value="1" id="cat-1" ${param.sort == '1' ? 'checked ' : ''} >  
                                                    <c:if test="${param.sort == '1'}">
                                                        <label for="cat-1" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >Default</span></label>
                                                    </c:if>
                                                    <c:if test="${param.sort != '1'}">
                                                        <label for="cat-1" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">Default</span></label>
                                                    </div>
                                                </c:if>
                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="sort" value="2" id="cat-2" ${param.sort == '2' ? 'checked' : ''} >
                                                    <c:if test="${param.sort == '2'}">
                                                        <label for="cat-2" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >Popularity</span></label>
                                                    </c:if>
                                                    <c:if test="${param.sort != '2'}">

                                                        <label  for="cat-2" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">Popularity</span></label>
                                                    </c:if>
                                                </div>


                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="sort" value="3" id="cat-3" ${param.sort == '3' ? 'checked' : ''} >
                                                    <c:if test="${param.sort == '3'}">
                                                        <label for="cat-3" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >Newness</span></label>
                                                    </c:if>
                                                    <c:if test="${param.sort != '3'}">
                                                        <label  for="cat-3" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">Newness</span></label>
                                                    </c:if>
                                                </div>
                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="sort" value="4" id="cat-4" ${param.sort == '4' ? 'checked' : ''} > 
                                                    <c:if test="${param.sort == '4'}">
                                                        <label for="cat-4" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >Price: Low to High</span></label>
                                                    </c:if>
                                                    <c:if test="${param.sort != '4'}">
                                                        <label  for="cat-4" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">Price: Low to High</span></label>
                                                    </c:if>
                                                </div>

                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="sort" value="5" id="cat-5" ${param.sort == '5' ? 'checked' : ''} >  
                                                    <c:if test="${param.sort == '5'}">
                                                        <label for="cat-5" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >Price: High to Low</span></label>
                                                    </c:if>
                                                    <c:if test="${param.sort != '5'}">

                                                        <label  for="cat-5" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">Price: High to Low</span></label>
                                                    </c:if>
                                                </div>

                                            </li>
                                        </div>
                                    </ul>
                                </div>

                                <div class="filter-col2 p-r-15 p-b-27">
                                    <div class="mtext-102 cl2 p-b-15">
                                        Price
                                    </div>

                                    <ul>
                                        <div style="line-height: 0.7">

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="price" value="price1" id="pr1" ${param.price == 'price1' ? 'checked' : ''}  > 
                                                    <c:if test="${param.price == 'price1'}">
                                                        <label for="pr1" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >$0.00 - $50.00</span></label>
                                                    </c:if>
                                                    <c:if test="${param.price != 'price1'}">    
                                                        <label  for="pr1" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">$0.00 - $50.00</span></label>
                                                    </c:if>
                                                </div>
                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="price" value="price2" id="pr2" ${param.price == 'price2' ? 'checked' : ''} >
                                                    <c:if test="${param.price == 'price2'}">
                                                        <label for="pr2" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >$50.00 - $100.00</span></label>
                                                    </c:if>
                                                    <c:if test="${param.price != 'price2'}">
                                                        <label  for="pr2" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">$50.00 - $100.00</span></label>
                                                    </c:if>
                                                </div>

                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="price" value="price3" id="pr3" ${param.price == 'price3' ? 'checked' : ''} > 
                                                    <c:if test="${param.price == 'price3'}">
                                                        <label for="pr3" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >$100.00 - $150.00</span></label>
                                                    </c:if>
                                                    <c:if test="${param.price != 'price3'}">
                                                        <label  for="pr3" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">$100.00 - $150.00</span></label>
                                                    </c:if>
                                                </div>
                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="price" value="price4" id="pr4" ${param.price == 'price4' ? 'checked' : ''} >
                                                    <c:if test="${param.price == 'price4'}">
                                                        <label for="pr4" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >$150.00 - $200.00</span></label>
                                                    </c:if>
                                                    <c:if test="${param.price != 'price4'}">
                                                        <label  for="pr4" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">$150.00 - $200.00</span></label>
                                                    </c:if>
                                                </div>

                                            </li>

                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="price" value="price5" id="pr5" ${param.price == 'price5' ? 'checked' : ''} >
                                                    <c:if test="${param.price == 'price5'}">
                                                        <label for="pr5" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >$200.00+</span></label>
                                                    </c:if>
                                                    <c:if test="${param.price != 'price5'}">
                                                        <label  for="pr5" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">$200.00+</span></label>
                                                    </c:if>
                                                </div>

                                            </li>
                                        </div>
                                    </ul>
                                </div>

                                <div class="filter-col3 p-r-15 p-b-27">
                                    <div class="mtext-102 cl2 p-b-15">
                                        Brand
                                    </div>

                                    <ul>
                                        <div style="line-height: 0.7">
                                            <c:forEach items="${listS}" var="s">
                                                <li class="p-b-6">
                                                    <div style="display: flex">
                                                        <input type="radio" name="supplier" value="${s.supplier_id}" id="supplier-${s.supplier_id}" ${param.supplier== s.supplier_id ? 'checked' : ''} > 
                                                        <c:if test="${param.supplier == s.supplier_id}">
                                                            <label for="supplier-${s.supplier_id}" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >${s.supplier_name}</span></label>
                                                            </c:if>
                                                            <c:if test="${param.supplier != s.supplier_id}">
                                                            <label  for="supplier-${s.supplier_id}" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">${s.supplier_name}</span></label>
                                                            </c:if>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </div>
                                    </ul>
                                </div>

                                <div class="filter-col4 p-b-27">
                                    <div class="mtext-102 cl2 p-b-15">
                                        Tags
                                    </div>
                                    <ul>
                                        <c:forEach items="${listC}" var="c">
                                            <li class="p-b-6">
                                                <div style="display: flex">
                                                    <input type="radio" name="category" value="${c.category_id}" id="category-${c.category_id}" ${param.category == c.category_id ? 'checked' : ''}> 
                                                    <label for="category-${c.category_id}" style="padding-top: 7px; padding-left: 10px;">
                                                        <span class="${param.category == c.category_id ? 'filter-link-active' : ''}" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">
                                                            ${c.category_name}
                                                        </span>
                                                    </label>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>

                                <a href="shop"><input  type="button" class="flex-c-m text-101 cl0 size-102 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn " style="margin-left: 350px;background-color: orange;color: black" value="Clear"></a>
                                <input type="submit" class="flex-c-m text-101 cl0 size-102 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" style="margin-left: 50px" value="Apply">

                            </div>
                        </form>
                    </div>
                </div>

                <div class="row isotope-grid">
                    <c:forEach items="${listP}" var="p"  >
                        <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item ${p.supplier_id}">
                            <!-- Block2 -->
                            <div class="block2">
                                <div class="block2-pic hov-img0 label-new" data-label="New">
                                    <img src="img_product/${p.img}" alt="IMG-PRODUCT" height="320" width="300">

                                    <a href="ShowProductDetail?pid=${p.product_id}" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 ">
                                        View Details
                                    </a>
                                </div>

                                <div class="block2-txt flex-w flex-t p-t-14">
                                    <div class="block2-txt-child1 flex-col-l " style="align-items: center">

                                        <a href="ShowProductDetail?pid=${p.product_id}" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 " >
                                            <div style="color: #593E67;  ">${p.product_name}</div>
                                        </a>
                                        <c:if test="${p.product_price!=p.product_price*(100-p.product_saleprice)/100}" >   
                                            <div style="display: flex">

                                                <span class="mtext-106 cl2" style="color: red; text-decoration: line-through; transform: translateX(-60px)">
                                                    $${p.product_price} 
                                                </span> &nbsp;

                                                <span class="mtext-106 cl2" style="transform: translateX(-20px)">
                                                    $<c:out value="${p.product_price*(100-p.product_saleprice)/100}"></c:out>        
                                                    </span>


                                                </div>
                                        </c:if>
                                        <c:if test="${p.product_price==p.product_price*(100-p.product_saleprice)/100}" >   
                                            <div style="display: flex">



                                                <span class="mtext-106 cl2" style="transform: translateX(10px)">
                                                    $<c:out value="${p.product_price}"></c:out>        
                                                    </span>


                                                </div>
                                        </c:if>
                                    </div>

                                    <!--                                    <div class="block2-txt-child2 flex-r p-t-3">
                                                                            <a href="#" class="btn-addwish-b2 dis-block pos-relative js-addwish-b2">
                                                                                <img class="icon-heart1 dis-block trans-04" src="images/icons/icon-heart-01.png" alt="ICON">
                                                                                <img class="icon-heart2 dis-block trans-04 ab-t-l" src="images/icons/icon-heart-02.png" alt="ICON">
                                                                            </a>
                                                                        </div>-->
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="flex-c-m flex-w w-full p-t-45">
                    <a href="shop" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
                        Load More
                    </a>
                </div>
                <!-- Pagination -->
                <!--			<div class="flex-c-m flex-w w-full p-t-38">
                                                <a href="#" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">
                                                        1
                                                </a>
                
                                                <a href="#" class="flex-c-m how-pagination1 trans-04 m-all-7">
                                                        2
                                                </a>
                                        </div>-->
            </div>
        </section>
        <script>
            // Kiểm tra nếu URL chứa "login" 
            if (window.location.href.indexOf("login") !== -1) {

                window.location.href = "home";
            }
        </script>
        <script>
            $(document).ready(function () {
                // Kiểm tra nếu đang ở trang đăng nhập (login)
                if (window.location.href.indexOf("removeItem") > -1) {
                    // Chuyển hướng sang trang chính (home)
                    window.location.href = "home"; // Thay thế 'đường_dẫn_home' bằng đường dẫn thực sự của trang chính
                }

                // Thêm lớp 'show-header-cart' cho phần tử có lớp 'js-panel-cart'
                $('.js-panel-cart').addClass('show-header-cart');
            });
        </script>   


        <!-- Footer -->
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
