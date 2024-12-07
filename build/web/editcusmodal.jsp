<%-- 
    Document   : editcusmodal
    Created on : Jan 22, 2024, 5:28:54 PM
    Author     : PCMSI
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


    </head>
    <body>
        <div class="headerm">
            <div class="title">Customer Profile</div>
            <!--                    <button class="btnm close-modalm">&times;</button>-->
        </div>
<!--<h3 style="text-align: center;">${mess}</h3>-->
        <div class="bodym">
            <div class="row">
                <div class="col-md-4">
                    <div class="profile-img">
                        <img src="img/${detail.user_image}"width="180px" height="160px"/ >



                    </div>
                </div>

                <div class="col-md-8">
                    <div class="profile-head " >



                        <div class="tab-content profile-tab" id="myTabContent">

                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>User Name</label>
                                    </div>
                                    <div class="col-md-8">

                                        <p> ${detail.user_name}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>Gender</label>
                                    </div>

                                    <c:if test="${detail.user_gender==1}">
                                        <div class="col-md-8">
                                            <p>Male</p>
                                        </div>
                                    </c:if>
                                    <c:if test="${detail.user_gender==0}">
                                        <div class="col-md-8">
                                            <p>Female</p>
                                        </div>
                                    </c:if>

                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-md-8">

                                        <p>${detail.user_email}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>Phone</label>
                                    </div>
                                    <div class="col-md-8">

                                        <p>${detail.user_phone}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <label>Address</label>
                                    </div>
                                    <div class="col-md-8">

                                        <p>${detail.user_address}</p>
                                    </div>
                                </div>
                            </div>
                            <div >
                                <a  href="setAvatarDefault?email=${detail.user_email}"><button style="margin: 1px" class="btn btn-primary" >Set default avatar</button></a>

                            </div>

                        </div>


                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
