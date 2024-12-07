<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>

            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/xuan2.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    My Purchases
                </h2>
            </section>
            <h1 style="transform: translateY(15px); color: #D19C97; font-family: 'Droid serif', serif; font-size: 60px; font-weight: normal; line-height: 48px; margin: 0 0 50px; text-align: center; text-shadow: 1px 1px 2px #082b34;">${mess}</h1>
        <!-- Product -->
        <div class="bg0 m-t-23 p-b-140">
            <div class="container">
                <div class="flex-w flex-sb-m p-b-52">
                    <div class="flex-w flex-l-m filter-tope-group m-tb-10">

                        <c:forEach items="${listOs}" var="o">
                            <a href="myPurchase?status=${o.status_id}"> 
                                <button style="transform: translateX(400px)"class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${param.status == o.status_id  ? 'how-active1':''}"  >
                                    ${o.status_name}
                                </button>
                            </a>
                        </c:forEach>

                    </div>

<!--                    <div class="flex-w flex-c-m m-tb-10">
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
                    </div>-->
                    <input type="hidden" name="page" value="${requestScope.paging.page}">
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
                    <input type="hidden" name="page" value="${requestScope.paging.page}">

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
                                        <div style="line-height: 0.7">
                                            <c:forEach items="${listC}" var="c">
                                                <li class="p-b-6">
                                                    <div style="display: flex">
                                                        <input type="radio" name="category" value="${c.category_id}" id="category-${c.category_id}" ${param.category== c.category_id ? 'checked' : ''} > 
                                                        <c:if test="${param.category == c.category_id}">
                                                            <label for="category-${c.category_id}" style="padding-top: 7px; padding-left: 10px; "><span class="filter-link-active" >${c.category_name}</span></label>
                                                            </c:if>
                                                            <c:if test="${param.category != c.category_id}">
                                                            <label  for="category-${c.category_id}" style="padding-top: 7px; padding-left: 10px; "><span style=" transition: color 0.3s;" onmouseover="this.style.color = 'red'" onmouseout="this.style.color = ''">${c.category_name}</span></label>
                                                            </c:if>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </div>
                                    </ul>

                                </div>
                                <a href="shop"><input  type="button" class="flex-c-m text-101 cl0 size-102 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn " style="margin-left: 350px;background-color: orange;color: black" value="Clear"></a>
                                <input type="submit" class="flex-c-m text-101 cl0 size-102 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" style="margin-left: 50px" value="Apply">

                            </div>
                        </form>
                    </div>
                </div>
                <h3 style="text-align: center;color: orangered">${mess0}</h3>
                <div class="row isotope-grid" style="">

                    <c:forEach items="${listO}" var="o">
                        <div class="col-lg-6 p-b-35 isotope-item " >
                            <!-- Block2 -->
                            <div class="block2" style="box-shadow: rgba(136, 165, 191, 0.48) 6px 2px 16px 0px, rgba(255, 255, 255, 0.8) -6px -2px 16px 0px; border-radius: 5px">
                                <div class="row">
                                    <div class="block2-pic col-md-4" data-label="New">
                                        <img src="img_product/${o.img}" alt="IMG-PRODUCT" height="200" width="180">
                                        <div class=" cl2" style="transform: translateY(2px)">
                                            <c:if test="${o.quantityoforder=='1'}">${o.quantityoforder} item</c:if>
                                            <c:if test="${o.quantityoforder!='1'}">${o.quantityoforder} items</c:if>
                                            </div>

                                        </div>

                                        <div class=" col-md-8" >
                                            <div class="  " style="align-items: center">


                                                <div >
                                                    <div style="transform: translateY(10px)">
                                                    <span class="mtext-106 cl2" style="">
                                                    ${o.product_name}  
                                                </span>
                                                </div>
                                                <br>

                                                <br>
                                                <div style="transform: translateY(20px)">
                                                <span class="mtext-106 cl2" style="">
                                                    Size: <fmt:formatNumber pattern="##" value="${o.size_id}"/>  
                                                </span>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                                <span class=" cl2" style="">
                                                    x${o.quantity}
                                                </span>
                                                <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <span class=" cl2" style="transform: translateX(130px);color: tomato">
                                                    $${o.price}
                                                </span>
                                                <br>
                                                </div>
                                                <div class=" cl2" style="transform: translateY(60px)">
                                                    Order date: ${o.order_date}
                                                </div>
                                            </div>
                                            <hr style="background-color: black;height: 1px; width: 570px;transform: translateY(50px) translateX(-200px)">

                                            <div class=" cl2" style="color: tomato;transform: translateX(260px) translateY(32px)">
                                                Total:$<c:out value="${o.order_total}"></c:out>        
                                                </div>

                                                <a href="ViewOrderInfo?oid=${o.order_id}"><input type="button" class=" btn btn-outline-danger" style=" width: 40%;border-radius: 5px;transform:translateY(-110px) translateX(220px) " value="View Detail"></a>

                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>

                <!-- Load more -->
                <!--                Paging search-->

                <!--Paging shop-->
                <c:if test="${totalP!=0}">
                    <div class="flex-c-m flex-w w-full p-t-38">
                        <c:if test="${cp!=1}">

                            <a class="flex-c-m how-pagination1 trans-04 m-all-7" href="myPurchase?page=${cp-1}&status=${status}"" aria-label="Previous" style="color: black">
                                <span aria-hidden="true"><<</span>
                                <span class="sr-only">Previous</span>
                            </a>

                        </c:if>
                        <c:forEach  begin="1" end="${endPage}" var="item">
                            <div class="flex-c-m how-pagination1 ${item==cp?"active-pagination1":""}">
                                <a href="myPurchase?page=${item}&status=${status}" class="flex-c-m how-pagination1 trans-04 m-all-7 ">
                                    <div style="color: black">${item}</div>
                                </a>
                            </div>
                        </c:forEach>
                        <c:if test="${cp!=endPage}">

                            <a class="flex-c-m how-pagination1 trans-04 m-all-7" href="myPurchase?page=${cp+1}&status=${status}"" aria-label="Next" style="color: black">
                                <span aria-hidden="true">>></span>
                                <span class="sr-only">Next</span>
                            </a>

                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>


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

