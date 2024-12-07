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

        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>

            <!-- Cart -->


            <!-- Title page -->
            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-02.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                <c:if test="${status.equals('new')}">New Blog</c:if>
                <c:if test="${status.equals('edit')}">Edit Blog</c:if>
                </h2>
            </section>	

            <!-- breadcrumb -->
            <div class="container">
                <div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">

                    <a href="postListMkt" class="stext-109 cl8 hov-cl1 trans-04">
                        Blog Manager
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>
                    <a href="manageblog" class="stext-109 cl8 hov-cl1 trans-04">
                        Blog List
                        <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
                    </a>

                    <span class="stext-109 cl4">
                    <c:if test="${status.equals('new')}">
                        <a href="AddPost?mod=1" class="stext-109 cl8 hov-cl1 trans-04">
                            New Blog
                        </a>
                    </c:if>
                    <c:if test="${status.equals('edit')}">
                        <a href="AddPost?mod=2&b_blogId=${b_blogId}" class="stext-109 cl8 hov-cl1 trans-04">
                            Edit Blog
                        </a>
                    </c:if>
                </span>
            </div>
        </div>


        <!-- Content page -->
        <!-- New blog -->
        <c:if test="${status.equals('new')}">
            <section class="bg0 p-t-52 p-b-20">
                <form action="AddPost" method="post" enctype="multipart/form-data">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-lg-9 p-b-80">
                                <div class="p-r-45 p-r-0-lg">
                                    <div class="wrap-pic-w how-pos5-parent">
                                        <label for="photo">Thumbnail:</label><br>
                                        <input type="file" accept="image/*" class="form-control" name="photo" id="recipient-name"><br>
                                    </div>

                                    <div class="p-t-32">
                                        <span class="flex-w flex-m stext-111 cl2 p-b-19">
                                            <span style="display: flex">
                                                <span class="cl4">By</span> 
                                                <input type="text" name="b_authorName"  style="border: 1px solid black ; margin-left: 10px" value="  Author name" required="">  
                                                <span class="cl12 m-l-4 m-r-6">|</span>
                                            </span>

                                            <span style="display: flex">
                                                <input type="text" name="b_updateDate" style="border: 1px solid black ; margin-left: 10px" value="${b_updateDate}" readonly="" required=""> 
                                                <span class="cl12 m-l-4 m-r-6">|</span>
                                            </span>
                                            <h4 class="ltext-109 cl2 p-b-28" style="border: 1px solid black; width: 100%; margin-top: 20px">
                                                <input type="text" name="blog_title" value=" Title" required="">
                                            </h4>
                                            <p class="stext-117 cl6 p-b-26" style="border: 1px solid black; width: 100%; margin-top: 20px">
                                                <input type="text" name="blog_brief" value=" Brief_info" required="">
                                            </p>
                                            <p class="stext-117 cl6 p-b-26">
                                                <textarea style="border: 1px solid black; margin-top: 20px"  rows="20" cols="112" name="blog_content" required="">  Paragraph  ...</textarea>
                                            </p>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-lg-3 p-b-80">
                                <div class="side-menu">
                                    <div class="p-t-55">
                                        <h4 class="mtext-112 cl2 p-b-33">
                                            Categories
                                        </h4>
                                        <ul>
                                            <c:forEach items="${requestScope.blogCategoriesList}" var="b">
                                                <li class="bor18" style="display: flex">
                                                    <input type="radio" name="blogCategories" value="${b.getCategoryBlogId()}">
                                                    <span class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
                                                        ${b.getCategoryBlogName()}
                                                    </span> 
                                                </li>
                                            </c:forEach>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3" style="background-color: black">Finish</button>      
                    </div>

                </form>


            </section>
        </c:if>
        <!-- Edit blog -->
        <c:if test="${status.equals('edit')}">
            <section class="bg0 p-t-52 p-b-20">
                <form action="AddPost" method="post" enctype="multipart/form-data">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-lg-9 p-b-80">
                                <div class="p-r-45 p-r-0-lg">
                                    <div class="wrap-pic-w how-pos5-parent">
                                        <label for="photo">THUMBNAILS:</label><br>
                                        <div class="size-210 bor10 p-lr-70 p-t-55 p-b-70 p-lr-15-lg w-full-md">

                                            <div class="text-center">
                                                <div class="row align-items-center justify-content-center rounded-circle img-fluid" >
                                                    <img src="images/${blog_thumbnail}"

                                                         class="" style="width: 250px;">
                                                </div>
                                            </div>

                                            <button class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" data-target="#modal1" style="margin-top: 32px">
                                                Change thumbnail
                                            </button>

                                        </div>
                                    </div>
                                    <div class="p-t-32">
                                        <span class="flex-w flex-m stext-111 cl2 p-b-19">
                                            <span style="display: flex">
                                                <span class="cl4">By</span> 
                                                <input type="text" name="b_authorName"  style="border: 1px solid black ; margin-left: 10px" value="${b_authorName}" required="">  
                                                <span class="cl12 m-l-4 m-r-6">|</span>
                                            </span>

                                            <span style="display: flex">
                                                <input type="text" name="b_updateDate" style="border: 1px solid black ; margin-left: 10px" value="${b_updateDate}" readonly="" required=""> 
                                                <span class="cl12 m-l-4 m-r-6">|</span>
                                            </span>
                                            <h4 class="ltext-109 cl2 p-b-28" style="border: 1px solid black; width: 100%; margin-top: 20px">
                                                <input type="text" name="blog_title" value="${blog_title}" required="">
                                            </h4>
                                            <p class="stext-117 cl6 p-b-26" style="border: 1px solid black; width: 100%; margin-top: 20px">
                                                <input type="text" name="blog_brief" value="${blog_brief}" required="">
                                            </p>
                                            <p class="stext-117 cl6 p-b-26">
                                                <textarea style="border: 1px solid black; margin-top: 20px"  rows="20" cols="112" name="blog_content" required="">${blog_content}</textarea>
                                            </p>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-lg-3 p-b-80">
                                <div class="side-menu">
                                    <div class="p-t-55">
                                        <h4 class="mtext-112 cl2 p-b-33">
                                            Categories
                                        </h4>
                                        <ul>
                                            <c:forEach items="${requestScope.blogCategoriesList}" var="b">
                                                <li class="bor18" style="display: flex">
                                                    <input type="radio" name="blogCategories" value="${b.getCategoryBlogId()}" ${blogCategories.equals(b.getCategoryBlogId()) ? "checked" : ""}>
                                                    <span class="dis-block stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">
                                                        ${b.getCategoryBlogName()}
                                                    </span> 
                                                </li>
                                            </c:forEach>
                                        </ul>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3" style="background-color: black">Finish</button>      
                    </div>
                    <input name="blog_id" value="${b_blogId}" hidden="">    
                </form>


            </section>
        </c:if>




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
