<%-- 
    Document   : signIn
    Created on : Jan 8, 2024, 4:26:16 PM
    Author     : TTT
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/signInUp.css" rel="stylesheet">
    </head>
    <body>
        <br>
        <br>
        <c:if test="${s == 1}">
            <div class="cont s--signup" style="height: 700px;">
        </c:if>
        <c:if test="${s != 1}">
            <div class="cont"  style="height: 700px;">
        </c:if>
        
            <div class="form sign-in">
                <h2>Welcome</h2>
                            <div style="margin-top: 40px; color: red; text-align: center">${mess}</div>

                <form action="login" method="post">
                    <label>
                        <span>Email</span>
                        <input type="email" name="u_email" value="${u_email}"/>
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" name="u_pass"/>
                    </label>
                    <p class="forgot-pass" ><a href="resetpassword" style="color: black">Forgot password?</a></p>  
                    <div style="margin-top: 40px; color: red; text-align: center">${u_alert}</div>
                    <input type="hidden" value="1" name="phase">
                    <button type="submit" class="submit">Sign In</button>
                </form>
            </div>
                    <div class="sub-cont" style="height: 700px">
                <div class="img">
                    <div class="img__text m--up">

                        <h3>Don't have an account? Please Sign up!<h3>
                                </div>
                                <div class="img__text m--in">

                                    <h3>If you already has an account, just sign in.<h3>
                                            </div>
                                            <div class="img__btn">
                                                <span class="m--up">Sign Up</span>
                                                <span class="m--in">Sign In</span>
                                            </div>
                                            </div>
                                            <!-- Sign Up below -->
                                            <form action="login" method="post"> 
                                                <div class="form sign-up">
                                                    <h2>Create your Account</h2>
                                                    <label>
                                                        <span>User Name</span>
                                                        <input type="text" name ="u_name" value="${ui.user_name}" required />
                                                    </label>
                                                    <div style="margin-top: 20px; color: red; text-align: center">${u_alert1}</div>
                                                    <label>
                                                        <span>Email</span>
                                                        <input type="email" name ="u_email" value="${ui.user_email}" required />
                                                    </label>
                                                    <label>
                                                        <span>Gender: </span>
                                                        <select name ="gender">
                                                            <option value="1" ${ui.user_gender.equals("1")?"selected":""}>Male</option>
                                                            <option value="0" ${ui.user_gender.equals("1")?"":"selected"}>Female</option>
                                                        </select>
                                                    </label>
                                                        <div style="margin-top: 20px; color: red; text-align: center">${u_cPhone}</div>
                                                    <label>
                                                        <span>Mobile</span>
                                                        <input type="text" name ="u_phone" value="${ui.user_phone}" required />
                                                    </label>
                                                    <label>
                                                        <span>Address</span>
                                                        <input type="text" name ="u_address" value="${ui.user_address}" required />
                                                    </label>
                                                    <label>
                                                        <span>Password</span>
                                                        <input type="password" name ="u_pass" value="${ui.user_pass}" minlength="6" maxlength="20" required />
                                                    </label>
                                                    <div style="margin-top: 20px; color: red; text-align: center">${u_alert2}</div>
                                                    <label>
                                                        <span>Re-enter Password</span>
                                                        <input type="password" name ="u_repass" value="${u_repass}" required />
                                                    </label>                                           
                                                    <button type="submit" class="submit">Sign Up</button>
                                                    <div style="margin-top: 20px; color: red; text-align: center">${u_alert3}</div>
                                                    <input type="hidden" value="2" name="phase">
                                                </div>
                                            </form>
                                            </div>
                                            </div>

                                            <script>
                                                document.querySelector('.img__btn').addEventListener('click', function () {
                                                    document.querySelector('.cont').classList.toggle('s--signup');
                                                });
                                                function switchFunc() {
                                                    var element = document.getElementById("")
                                                }
                                            </script>
                                            </body>
                                            </html>
