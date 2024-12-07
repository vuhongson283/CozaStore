<%-- 
    Document   : contact
    Created on : Jan 27, 2024, 5:33:11 PM
    Author     : PCMSI
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="component-interfacecus/header.jsp"></jsp:include>
            <link rel="stylesheet" href="modal/styleprofile1.css" />

        </head>
        <body class="animsition">
        
        <c:if test="${sessionScope.acc.user_roleId=='4'}">
            <!-- Header -->
        <jsp:include page="component-interfacecus/taskbar.jsp"></jsp:include>
        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>

            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>
        </c:if>


            <!-- Title page -->
            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-01.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    My Profile
                </h2>
            </section>
            <c:if test="${sessionScope.acc.user_roleId=='3'}">
            <a href="customerListController"><input type="button" class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" style="width: 15%;border-radius: 5px;" value="Back to Home Manager"></a>
            </c:if>
            <c:if test="${sessionScope.acc.user_roleId=='1'}">
            <a href="ManageAccount"><input type="button" class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" style="width: 15%;border-radius: 5px;" value="Back to Home Manager"></a>
            </c:if>
            <h3 style="text-align: center; color: orangered; transform: translateY(50px);">${mess}</h3>

        <!-- Content page -->
        <section class="bg0 p-t-104 p-b-116">
            <div class="container">
                <div class="flex-w flex-tr">
                    <div class="size-210 bor10 p-lr-70 p-t-55 p-b-70 p-lr-15-lg w-full-md">

                        <div class="text-center">
                            <div class="row align-items-center justify-content-center rounded-circle img-fluid" >
                                <img src="img/${profileuser.user_image}"/

                                     class="rounded-circle img-fluid" style="width: 250px;">
                            </div>
                        </div>



                        <button class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn" data-target="#modal1">
                            Change avatar
                        </button>

                    </div>

                    <div class="size-210 bor10 flex-w flex-col-m p-lr-93 p-tb-30 p-lr-15-lg w-full-md">

                        <div class="card-body">
                            <form action="updateprofile" method="post">
                                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label>User Name</label>
                                        </div>
                                        <div class="col-md-9">

                                            <input value="${profileuser.user_name}" name="name" type="text" class="form-control" required>
                                        </div>
                                    </div>
                                    <hr>

                                    <div class="row">
                                        <div class="col-md-3" >
                                            <label style="transform: translateY(2px)">Gender</label>
                                        </div>

                                        <c:if test="${profileuser.user_gender==null}">
                                            <div class="col-md-9" style="display: flex">
                                                <input type="radio" name="gender" value="1" ><div style="padding-top: 3px">Male</div>
                                                <input type="radio" name="gender" value="0" style="transform: translateX(6px)"><div style="padding-top: 3px;transform: translateX(6px)">Female</div>
                                            </div>
                                        </c:if>
                                        <c:if test="${profileuser.user_gender==1}">
                                            <div class="col-md-9" style="display: flex">
                                                <input type="radio" name="gender" value="1" checked=""><div style="padding-top: 3px">Male</div>
                                                <input type="radio" name="gender" value="0" style="transform: translateX(6px)"><div style="padding-top: 3px;transform: translateX(6px)">Female</div>
                                            </div>
                                        </c:if>
                                        <c:if test="${profileuser.user_gender==0}">
                                            <div class="col-md-9" style="display: flex">
                                                <input type="radio" name="gender" value="1" ><div style="padding-top: 3px">Male</div>
                                                <input type="radio" name="gender" value="0" checked="" style="transform: translateX(6px)"><div style="padding-top: 3px;transform: translateX(6px)">Female</div>
                                            </div>
                                        </c:if>
                                    </div>
                                    <hr>

                                    <div class="row">
                                        <div class="col-md-3">
                                            <label style="transform: translateY(6px)">Email</label>
                                        </div>
                                        <div class="col-md-9">

                                            <input value="${profileuser.user_email}" name="email" type="text" class="form-control" readonly required>
                                        </div>
                                    </div>
                                    <hr>
                                    <div style="margin-top: 20px; color: red; text-align: center">${phone}</div>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label style="transform: translateY(6px)">Phone</label>
                                        </div>
                                        <div class="col-md-9">

                                            <input value="${profileuser.user_phone}" name="phone" type="text" class="form-control" required>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label style="transform: translateY(6px)">Address</label>
                                        </div>
                                        <div class="col-md-9">

                                            <input value="${profileuser.user_address}" name="address" type="text" class="form-control" required>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div >
                                    <input type="submit" class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn"  value="Update Profile">
                                </div>
                            </form>      
                        </div>

                    </div>
                </div>
            </div>
        </section>	


        <!-- Map -->




        <!-- Footer -->
        <jsp:include page="component-interfacecus/footer.jsp"></jsp:include>


        <!-- Back to top -->
        <div class="btn-back-to-top" id="myBtn">
            <span class="symbol-btn-back-to-top">
                <i class="zmdi zmdi-chevron-up"></i>
            </span>
        </div>
        <div class="modalm" id="modal1" ">
            <div class="headerm">
                <div class="title">Pick a picture</div>
                <button class="btnm close-modalm">&times;</button>
            </div>
            <div class="bodym">
                <form action="upload-image" method="POST" onsubmit="" enctype="multipart/form-data">
                    <div class="mb-3">
                        <input type="file" accept="image/*" class="form-control" name="photo" id="recipient-name">
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-link">Upload</button>
                    </div>
                </form>
            </div>
        </div>
        <div id="overlaym"></div>

        <script src="modal/script.js"></script>      
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
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
        <script src="js/map-custom.js"></script>
        <!--===============================================================================================-->
        <script src="js/main1.js"></script>

    </body>
</html>

