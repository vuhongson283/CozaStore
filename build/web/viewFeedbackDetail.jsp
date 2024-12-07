

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Manager</title>
        <style>
            .dot {
                height: 6px;
                width: 6px;
                background-color: black;
                border-radius: 50%;
                display: inline-block;
                margin-right: 5px;
            }


            label {
                margin-left: 3%;
                display: block;
            }
        </style>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link rel="stylesheet" href="modal/stylecus.css" />
        <link rel="stylesheet" href="modal/styleviewcus.css" />
        <link rel="stylesheet" href="modal/style.css" />
        <jsp:include page="component/admin/head.jsp"></jsp:include>
        </head>
    <jsp:include page="component/admin/header.jsp"></jsp:include>
        <body>
            <div class="container-fluid position-relative bg-white d-flex p-0">
            <jsp:include page="component/admin/slidebarsale.jsp"></jsp:include>
                <!-- Content Start -->
                <div class="content">
                    <div class="container">
                    <jsp:include page="component/admin/navbar.jsp"></jsp:include> <br/>
                        
                        
                    <form name="productForm" onsubmit="return validateForm()" action="" method="POST" enctype="multipart/form-data">
                        <div class = "row">
                            <div class="col-md-6">
                                <h2 class="mb-3">General Information</h2>


                                <label for="name" readonly>Feedback ID:</label><br>
                                <input type="text" readonly id="productName" name="id" value="${requestScope.id}" required="" readonly=""><br>
                                <label for="name" ">Feedback Description:</label><br>
                                <textarea readonly="">${feedback.feedback_des}</textarea><br>
                                <label for="name">Feedback Image:</label><br>
                                <img width="100px" src="img_product/${feedback.feedback_img}">
                                <br>
                                <label for="supplier">Feedback Rate:</label><br>
                                <select id="brand" name="status" readonly>
                                    <option value="0">Select Rating</option>
                                    <option value="1" readonly="" ${feedback.feedback_rate == 1 ? 'selected' : ''}>&#9733;</option>
                                    <option value="2"readonly="" ${feedback.feedback_rate == 2 ? 'selected' : ''}>&#9733;&#9733;</option>
                                    <option value="3"readonly="" ${feedback.feedback_rate == 3 ? 'selected' : ''}>&#9733;&#9733;&#9733;</option>
                                    <option value="4"readonly="" ${feedback.feedback_rate == 4 ? 'selected' : ''}>&#9733;&#9733;&#9733;&#9733;</option>
                                    <option value="5"readonly="" ${feedback.feedback_rate == 5 ? 'selected' : ''}>&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                                </select>
                                <br>
                                <label for="supplier">Status:</label><br>
                                <select id="brand" name="status">
                                    <option readonly=""  value="1" ${feedback.feedback_status == 1 ? 'selected' : ''}>Show</option>
                                    <option readonly=""  value="0"${feedback.feedback_status == 0 ? 'selected' : ''}>Hide</option>
                                </select>
                            </div>  
                            <div class = "col-md-6">
                                <h2 class="mb-3">Product & User Information</h2>
                                <label for="name">User Name</label><br>
                                <input readonly="" type="text" id="productName" value="${feedback.user_name}" name="backlink" required=""><br>
                                <label for="name">User Email</label><br>
                                <input readonly="" type="text" id="productName" value="${feedback.user_email}" name="backlink" required=""><br><!-- comment -->
                                <label readonly="" for="name">User Phone</label><br>
                                <input readonly="" type="text" id="productName" value="${feedback.user_phone}" name="backlink" required=""><br>
                                <label for="name">Product Name</label><br>
                                <input readonly="" type="text" id="productName" value="${feedback.product_name}" name="backlink" required=""><br>  
                            </div>
                        </div>
                    </form>


                </div>
                <!-- Footer Start -->
                <jsp:include page="component/admin/footer.jsp"></jsp:include>
                <!-- Footer End -->
            </div>
        </div>

        <script src="modal/script.js">
        </script>


        <script type="text/javascript">
            $(document).ready(function () {
                $(".link-detail").on("click", function (e) {
                    e.preventDefault();
                    $("#modal1").load($(this).attr("href"));
                });
            });
        </script>
    </body>


</html>



