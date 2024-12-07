
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Manager</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/super-build/ckeditor.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/classic/translations/vi.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/media-embed/media-embed.js"></script>
        <jsp:include page="component/admin/head.jsp"></jsp:include>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #007BFF; /* Change the background color to blue */
                    padding: 20px;
                }
                form {
                    background-color: #fff;
                    padding: 20px;
                    border-radius: 8px;
                    box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
                }
                label {
                    font-weight: bold;
                    margin-top: 10px;
                }
                input, select, textarea {
                    width: 100%;
                    padding: 10px;
                    border: 1px solid #ddd;
                    border-radius: 4px;
                    box-sizing: border-box;
                    margin-top: 6px;
                    margin-bottom: 16px;
                    resize: vertical;
                }
                input[type="submit"] {
                    background-color: #4CAF50;
                    color: white;
                    padding: 12px 20px;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                }
                input[type="submit"]:hover {
                    background-color: #45a049;
                }
                /* Add some styling to the footer */
                footer {
                    background-color: #f8f9fa;
                    padding: 20px 0;
                    position: fixed;
                    width: 100%;
                    bottom: 0;
                }
                .container {
                    max-width: 960px;
                    margin: 0 auto;
                    padding: 0 15px;
                }
            </style>
        </head>
    <jsp:include page="component/admin/header.jsp"></jsp:include>
        <body>
            <div class="container-fluid position-relative bg-white d-flex p-0">
            <jsp:include page="component/admin/slidebar.jsp"></jsp:include>
                <!-- Content Start -->
                <div class="content">
                    <div class="container-xxl">
                    <jsp:include page="component/admin/navbar.jsp"></jsp:include>
                        <!-- Sale & Revenue Start -->
                        <div class="container pt-4 px-4">
                            <div class="row">
                                <form name="productForm" action="editsetting" method="POST">
                                    <h2 class="mb-3">Edit Setting</h2>
                                    <br>
                                    <label for="settingId"> Id:</label><br>
                                    <input type="text" id="settingId" name="settingId" required="" class="form-control" readonly="" value="${requestScope.setting.getSettingId()}"><br>
                                <label for="type"> Type:</label><br>
                                <input type="text" id="type" name="type" required="" class="form-control" readonly="" value="${requestScope.setting.getSettingCategory()}" >
                                <br>
                                <label for="order">Order:</label><br>
                                <input type="number" id="order" name="order" class="form-control" readonly="" value="${requestScope.setting.getSettingOrder()}" >
                                <div class="md-form">
                                    <label for="editor">Value:</label>
                                    <input type="text" id="editor" name="value" class="md-textarea form-control" rows="3" required="" value="${requestScope.setting.getSettingValue()}">
                                </div>
                                <label>Status</label>
                                <select name="status" class="form-select" aria-label="Default select example">
                                    <option ${requestScope.setting.isStatus() == '1'?"selected":""} value="1">Available</option>
                                    <option ${requestScope.setting.isStatus() == '0'?"selected":""} value="0">Not Available</option>
                                </select>
                                <br>
                                <button type="submit" class="btn btn-primary mt-3">Update</button>     
                            </form>
                        </div>
                    </div>
                    <!-- Sale & Revenue End -->
                </div>
                <!-- Footer Start -->
                <jsp:include page="component/admin/footer.jsp"></jsp:include>
                <!-- Footer End -->
            </div>
        </div>
    </body>

</html>