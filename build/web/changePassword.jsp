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
            <script>
                const seePassword = () => {
                    const seePwdIcon = document.querySelector('.see-password'),
                            pwdInput = document.querySelector('.group input')

                    seePwdIcon.addEventListener('mousedown', () => {
                        pwdInput.type = 'text'
                    })

                    seePwdIcon.addEventListener('mouseup', () => {
                        pwdInput.type = 'password'
                    })

                    seePwdIcon.addEventListener('mouseover', () => {
                        pwdInput.type = 'password'
                    })
                }

                seePassword()
            </script>
        </head>
        <body class="animsition">

            <!-- Header -->
        <c:if test="${sessionScope.acc.user_roleId=='4'}">
            <!-- Header -->
        <jsp:include page="component-interfacecus/taskbar.jsp"></jsp:include>
        <jsp:include page="component-interfacecus/sidebar.jsp"></jsp:include>

            <!-- Cart -->
        <jsp:include page="component-interfacecus/cart.jsp"></jsp:include>
        </c:if>


            <!-- Title page -->

            



            <!-- Title page -->
            <section class="bg-img1 txt-center p-lr-15 p-tb-92" style="background-image: url('images/bg-01.jpg');">
                <h2 class="ltext-105 cl0 txt-center">
                    Change Password
                </h2>
            </section>	
            
            <h4 class="text-danger" style="text-align: center;transform: translateY(60px)">${alertP}</h4>
        <!-- Content page -->
        <section class="bg0 p-t-104 p-b-116">
            <div class="container">
                <div class="flex-w flex-tr">
                    <div class="size-210 bor10 flex-w flex-col-m p-lr-93 p-tb-30 p-lr-15-lg w-full-md" style="margin: auto; display: flex;
                         align-items: center; justify-content: center; min-height: 10vh;">
                        <div class="card-body" style="min-height: 0px;">
                            <form action="changepass" method="post">
                                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                    <div class="row">

                                        <div class="col-md-12">

                                            <input name="oldpass" type="password" class="form-control" required placeholder="Current Password">
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">

                                        <div class="col-md-12">

                                            <input name="newpass" type="password" class="form-control"  required placeholder="New Password">
                                        </div>
                                    </div>
                                    <hr>
                                    
                                    <div class="row">
                                       
                                        <div class="col-md-12">

                                            <input name="cfpass" type="password" class="form-control" required placeholder="Re-enter Password">
                                        </div>
                                    </div>


                                </div>
                                <hr>
                                <div >
                                    
                                    <input type="submit" class="flex-c-m stext-101 cl0 size-121 bg3 bor1 hov-btn3 p-lr-15 trans-04 pointer btn"  value="Change Pass">
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

