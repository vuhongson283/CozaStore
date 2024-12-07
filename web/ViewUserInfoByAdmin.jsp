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
        <link rel="stylesheet" href="modal/usercss.css" />

    </head>
    <body>
        <div class="headerm" style="margin-bottom: 0px">
            <div class="title">User Details</div>
        </div>
        <div class="bodym">
            <form action="EditCusByAdmin" method="post">

                <div class="row">
                    <div class="col-md-4">
                        <div class="profile-img">
                            <img src="img/${detail.user_image}" style="transform: translateY(-30px)" alt="User Image">
                        </div>
                    </div>
                    <div class="col-md-8">
                        <div class="profile-head">
                            <div class="tab-content profile-tab" id="myTabContent">
                                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>Name</label>
                                        </div>
                                        <div class="col-md-8">
                                            <p>${detail.user_name}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>Gender</label>
                                        </div>
                                        <div class="col-md-8">
                                            <p>${detail.user_gender == 1 ? 'Male' : 'Female'}</p>
                                        </div>
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
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>Role</label>
                                        </div>
                                        <div class="col-md-8">
                                              <c:if test="${detail.user_roleId=='2'}">
                                                    <p>Sale</p>
                                                </c:if>
                                                <c:if test="${detail.user_roleId=='3'}">
                                                    <p>Marketing</p>
                                                </c:if>
                                                <c:if test="${detail.user_roleId=='4'}">
                                                    <p>Customer</p>
                                                </c:if>
                                        </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>Status</label>
                                        </div>
                                        <div class="col-md-8">
                                            <input type="radio" name="status" ${detail.user_status=='1'?"checked":""} value="1"> Active &nbsp;&nbsp;
                                            <input type="radio" name="status" ${detail.user_status=='0'?"checked":""} value="0"> Inactive 
                                        </div>
                                            
                                    </div>
                                        
                                </div>
                                <div>
                                    <input type="hidden" name="email" value="${detail.user_email}">
                                        <button type="submit" class="btn btn-primary">Save</button>
                                    
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

