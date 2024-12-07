<%-- 
    Document   : blog
    Created on : Jan 27, 2024, 12:12:41 PM
    Author     : PCMSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="component-interfacecus/header.jsp"></jsp:include>
        <script src="https://kit.fontawesome.com/fe90bb0cfe.js" crossorigin="anonymous"></script>
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
                    Blog
                </h2>
            </section>	


            <!-- Content page -->
        <c:if test="${sessionScope.acc.getUser_roleId().equals('3')}">
            <a class="btn btn-primary" href="postListMkt" style="margin-left: 170px;
               margin-top: 40px;
               background-color: black;"><i class="fa-solid fa-arrow-left" style="margin-right: 8px"></i>Return to manage post</a>
            <a class="btn btn-primary" href="AddPost" style="margin-left: 12px;
               margin-top: 40px;
               background-color: black;
               width: 8%;">New Post</a>
        </c:if>
        <section class="bg0 p-t-62 p-b-60">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-lg-9 p-b-80">
                        <div class="p-r-45 p-r-0-lg">

                            <!-- item blog -->
                            <c:forEach items="${requestScope.blogs}" var="b" >
                                <div class="p-b-63">
                                    <a href="blogdetail?blogId=${b.blogId}" class="hov-img0 how-pos5-parent">
                                        <img src="images/${b.thumbnail}" alt="IMG-BLOG">
                                        <div class="flex-col-c-m size-123 bg9 how-pos5">
                                            <span class="ltext-107 cl2 txt-center">
                                                ${b.getShortenedUpdatedDate()}
                                            </span>
                                            <span class="stext-109 cl3 txt-center">
                                                ${b.getMonthYear()}
                                            </span>
                                        </div>
                                    </a>
                                    <div class="p-t-32">
                                        <h4 class="p-b-15">
                                            <a href="blogdetail?blogId=${b.blogId}" class="ltext-108 cl2 hov-cl1 trans-04">
                                                ${b.title}
                                            </a>
                                        </h4>
                                        <p class="stext-117 cl6">
                                            ${b.briefInfo}
                                        </p>
                                        <div class="flex-w flex-sb-m p-t-18">
                                            <span class="flex-w flex-m stext-111 cl2 p-r-30 m-tb-10">
                                                <span>
                                                    <span class="cl4">By</span> ${b.authorName}  
                                                    <span class="cl12 m-l-4 m-r-6">|</span>
                                                </span>
                                            </span>


                                            <a href="blogdetail?blogId=${b.blogId}" class="stext-101 cl2 hov-cl1 trans-04 m-tb-10">
                                                Continue Reading
                                                <i class="fa fa-long-arrow-right m-l-9"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="flex-l-m flex-w w-full p-t-10 m-lr--7">
                                <c:if test="${totalBlogsInPage!=0}">
                                    <div class="col-12 pb-1">
                                        <nav aria-label="Page navigation">
                                            <ul class="pagination justify-content-center mb-3">
                                                <c:if test="${cp!=1}">
                                                    <li class="page-item ">
                                                        <a class="flex-c-m how-pagination1 trans-04 m-all-7" href="manageblog?page=${cp-1}&search=${requestScope.search}"" aria-label="Previous">
                                                            <span aria-hidden="true">&laquo;</span>
                                                            <span class="sr-only">Previous</span>
                                                        </a>
                                                    </li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endPage}" var="item">
                                                    <li class="page-item ${item==cp?"active":""} " style="z-index: 0">                               
                                                        <a class="flex-c-m how-pagination1 trans-04 m-all-7" href="manageblog?page=${item}&search=${requestScope.search}">${item}</a>                               
                                                    </li>
                                                </c:forEach>
                                                <c:if test="${cp!=endPage}">
                                                    <li class="page-item">
                                                        <a class="flex-c-m how-pagination1 trans-04 m-all-7" href="manageblog?page=${cp+1}&search=${requestScope.search}"" aria-label="Next">
                                                            <span aria-hidden="true">&raquo;</span>
                                                            <span class="sr-only">Next</span>
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div>
                                </c:if>
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
