<%-- 
    Document   : blog-detail
    Created on : Jan 27, 2024, 12:19:24 PM
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

            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>

            <!-- Title page -->
            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-02.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    Blog Detail
                </h2>
            </section>	

            <!-- breadcrumb -->
            <div class="container">
                <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
                    <a href="home" class="stext-109 cl8 hov-cl1 trans-04">
                        Home
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>

                    <a href="manageblog" class="stext-109 cl8 hov-cl1 trans-04">
                        Blog
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>

                    <span class="stext-109 cl4">
                    ${blog.title}
                </span>
            </div>
        </div>


        <!-- Content page -->
        <section class="bg0 p-t-52 p-b-20">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-9 p-b-80">
                        <div class="p-r-45 p-r-0-lg">
                            <!--  -->
                            <div class="wrap-pic-w how-pos5-parent">
                                <img src="images/${blog.thumbnail}" alt="IMG-BLOG">

                                <div class="flex-col-c-m size-123 bg9 how-pos5">
                                    <span class="ltext-107 cl2 txt-center">
                                        ${blog.getShortenedUpdatedDate()}
                                    </span>
                                    <span class="stext-109 cl3 txt-center">
                                        ${blog.getMonthYear()}
                                    </span>
                                </div>
                            </div>

                            <div class="p-t-32">
                                <span class="flex-w flex-m stext-111 cl2 p-b-19">
                                    <span>
                                        <span class="cl4">By</span> ${blog.authorName}  
                                        <span class="cl12 m-l-4 m-r-6">|</span>
                                    </span>

                                    <span>
                                        ${blog.updatedDate}
                                        <span class="cl12 m-l-4 m-r-6">|</span>
                                    </span>

                                </span>

                                <h4 class="ltext-109 cl2 p-b-28">
                                    ${blog.title}
                                </h4>
                                <c:forEach items="${blog.splitContentAroundNearestDotAfterAdventures()}" var="half">
                                    <p class="stext-117 cl6 p-b-26">
                                        ${half}
                                    </p>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-lg-3 p-b-80">
                        <div class="side-menu">
                            <div class="bor17 of-hidden pos-relative">
                                <form action="manageblog" method="POST">
                                    <input class="stext-103 cl2 plh4 size-116 p-l-28 p-r-55" type="text" name="search" placeholder="Search">

                                    <button type="submit" class="flex-c-m size-122 ab-t-r fs-18 cl4 hov-cl1 trans-04">
                                        <i class="zmdi zmdi-search"></i>
                                    </button>
                                </form>
                            </div>

                            <div class="p-t-55">
                                <h4 class="mtext-112 cl2 p-b-33">
                                    Categories
                                </h4>

                                <ul>
                                    <c:forEach var="blogCategory" items="${requestScope.blogCategorys}">
                                        <li class="bor18">
                                            <a href="manageblog?category=${blogCategory.categoryBlogId}" class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
                                                ${blogCategory.categoryBlogName}
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>	



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

    </body>
</html>
