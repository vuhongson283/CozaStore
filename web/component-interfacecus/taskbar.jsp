<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="totalCart" value="${0}"/>
<c:forEach items="${listPC}" var="p">
    <c:set var="totalCart" value="${totalCart+1}"/>
</c:forEach>
<c:set var="totalWish" value="${0}"/>
<c:forEach items="${listWL}" var="w">
    <c:set var="totalWish" value="${totalWish+1}"/>
</c:forEach>
<header class="header-v3">
    <!-- Header desktop -->
    <div class="container-menu-desktop trans-03">
        <div class="wrap-menu-desktop">
            <nav class="limiter-menu-desktop p-l-45">

                <!-- Logo desktop -->		
                <a href="home" class="logo">
                    <img src="images/icons/logo-02.png" alt="IMG-LOGO">
                </a>

                <!-- Menu desktop -->
                <div class="menu-desktop">
                    <ul class="main-menu">
                        <li>
                            <a href="home">Home</a>

                        </li>

                        <li class="label1" data-label1="hot">
                            <a href="shop">Shop</a>
                        </li>



                        <li>
                            <a href="manageblog">Blog</a>
                        </li>

                        <li>
                            <a href="about.jsp">About</a>
                        </li>

                        <li>
                            <a href="contact.jsp">Contact</a>
                        </li>
                    </ul>
                </div>	

                <!-- Icon header -->

                <div class="wrap-icon-header flex-w flex-r-m h-full">

                    <div class="flex-c-m h-full p-r-25 bor6">
                        <div class="icon-header-item cl0 hov-cl1 trans-04 p-l-40 p-r-1  js-show-modal-search" >
                            <i class="zmdi zmdi-search"></i>
                        </div>
                    </div> 
                    <c:if test="${sessionScope.acc.user_roleId=='4'}">

                        <div class="flex-c-m h-full p-r-25 bor6" >
                            <div class="icon-header-item cl0 hov-cl1 trans-04 p-l-22 p-lr-1 icon-header-noti js-show-cart" data-notify="${totalCart}">
                                <i class="zmdi zmdi-shopping-cart" ></i>
                            </div>
                        </div>
                        <div class="flex-c-m h-full p-r-25 bor6">
                            <a href="viewWishList">
                            <div class="icon-header-item cl0 hov-cl1 trans-04 p-l-22 p-r-1 icon-header-noti" data-notify="${totalWish}" >
                                <i class="zmdi zmdi-favorite-outline"></i>
                            </div>
                            </a>
                        </div>

                    </c:if>



                    <div class="flex-c-m h-full p-lr-19" >
                        <c:if test="${sessionScope.acc==null}"> 
                            <div  style="display: flex">
                                <div class="menu-desktop">
                                    <ul class="main-menu">
                                        <li>
                                            <a href="login?mod=1">Login</a>

                                        </li>

                                        <li>
                                            <a href="login?mod=2">Register</a>
                                        </li>
                                    </ul>
                                </div>


                            </c:if>
                            <c:if test="${sessionScope.acc!=null}">
                                <div class="flex-c-m h-full p-lr-19">
                                    <div class="icon-header-item cl0 hov-cl1 trans-04 p-lr-11 js-show-sidebar">
                                        <i class="zmdi zmdi-menu"></i>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
            </nav>
        </div>	
    </div>

    <!-- Header Mobile -->
    <div class="wrap-header-mobile">
        <!-- Logo moblie -->		
        <div class="logo-mobile">
            <a href="index.html"><img src="images/icons/logo-01.png" alt="IMG-LOGO"></a>
        </div>

        <!-- Icon header -->
        <div class="wrap-icon-header flex-w flex-r-m h-full m-r-15">
            <div class="flex-c-m h-full p-r-5">
                <div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart" data-notify="0">
                    <i class="zmdi zmdi-shopping-cart"></i>
                </div>
            </div>

        </div>


        <!-- Button show menu -->
        <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
            <span class="hamburger-box">
                <span class="hamburger-inner"></span>
            </span>
        </div>
    </div>


    <!-- Menu Mobile -->
    <div class="menu-mobile">
        <ul class="main-menu-m">
            <li>
                <a href="index.html">Home</a>
                <ul class="sub-menu-m">
                    <li><a href="index.html">Homepage 1</a></li>
                    <li><a href="home-02.html">Homepage 2</a></li>
                    <li><a href="home-03.html">Homepage 3</a></li>
                </ul>
                <span class="arrow-main-menu-m">
                    <i class="fa fa-angle-right" aria-hidden="true"></i>
                </span>
            </li>

            <li>
                <a href="product.html">Shop</a>
            </li>

            <li>
                <a href="shoping-cart.html" class="label1 rs1" data-label1="hot">Features</a>
            </li>

            <li>
                <a href="blog.html">Blog</a>
            </li>

            <li>
                <a href="about.html">About</a>
            </li>

            <li>
                <a href="contact.html">Contact</a>
            </li>
        </ul>
    </div>

    <!-- Modal Search -->
    <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
        <button class="flex-c-m btn-hide-modal-search trans-04">
            <i class="zmdi zmdi-close"></i>
        </button>

        <form action="search-product" method="get" class="container-search-header">
            <div class="wrap-search-header">
                <div style="display: flex">
                    <input class="plh0" type="text" name="search" placeholder="Search product..." style="padding-top: 27px">

                    <button type="submit" class="flex-c-m trans-04" style="padding-top: 40px">
                        <i class="zmdi zmdi-search" ></i>
                    </button>
                </div>
            </div>
        </form>
    </div>

</header>