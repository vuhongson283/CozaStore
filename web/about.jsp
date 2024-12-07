<%-- 
    Document   : about
    Created on : Jan 27, 2024, 11:55:15 AM
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
            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-01.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    About
                </h2>
            </section>	


            <!-- Content page -->
            <section class="bg0 p-t-75 p-b-120">
                <div class="container">
                    <div class="row p-b-148">
                        <div class="col-md-7 col-lg-8">
                            <div class="p-t-7 p-r-85 p-r-15-lg p-r-0-md">
                                <h3 class="mtext-111 cl2 p-b-16">
                                    Our Story
                                </h3>

                                <p class="stext-113 cl6 p-b-26" style="color: black">
                                    COZA Store: Where Every Step Tells a Story
                                </p>

                                <p class="stext-113 cl6 p-b-26" style="color: black">



                                    Step into the world of COZA Store, where footwear becomes a canvas for craftsmanship, style, and sustainability. Our curated collection offers a diverse range of shoes, from urban chic to outdoor ruggedness, each pair crafted with care and commitment to quality. As you explore our digital emporium, join our vibrant community of shoe enthusiasts, where connections are forged and stories shared. At COZA Store, every step is an opportunity to embrace excellence and express your unique journey.						</p>

                                <p class="stext-113 cl6 p-b-26" style="color: black">
                                    Welcome to a realm where footwear fantasies come to life.						</p>
                            </div>
                        </div>

                        <div class="col-11 col-md-5 col-lg-4 m-lr-auto">
                            <div class="how-bor1 ">
                                <div class="hov-img0">
                                    <img src="img_product/giay-nike-gdragon-2.jpg" alt="IMG" >
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="order-md-2 col-md-7 col-lg-8 p-b-30">
                            <div class="p-t-7 p-l-85 p-l-15-lg p-l-0-md">
                                <h3 class="mtext-111 cl2 p-b-16">
                                    Our Mission
                                </h3>

                                <p class="stext-113 cl6 p-b-26" style="color: black" >
At COZA Store, our mission is to redefine footwear by offering a curated collection that blends style, quality craftsmanship, and sustainability. We aim to foster a community where enthusiasts can connect and celebrate the art of shoemaking. Through ethical sourcing and sustainable practices, we empower our customers to step confidently into their journeys while minimizing our environmental impact. Welcome to a world where every step tells a story of excellence and conscientiousness.                                </p>

                                <div class="bor16 p-l-29 p-b-9 m-t-22">
                                    <p class="stext-114 cl6 p-r-40 p-b-11">
                                        Creativity is just connecting things. When you ask creative people how they did something, they feel a little guilty because they didn't really do it, they just saw something. It seemed obvious to them after a while.
                                    </p>

                                    <span class="stext-111 cl8">
                                        - Steve Job’s 
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="order-md-1 col-11 col-md-5 col-lg-4 m-lr-auto p-b-30">
                            <div class="how-bor2">
                                <div class="hov-img0">
                                    <img src="images/fashion.jpg" alt="IMG">
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
