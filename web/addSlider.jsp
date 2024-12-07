
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
            <script>
                function validateForm() {
                    var inputs = document.getElementsByClassName('form-control');
                    for (var i = 0; i < inputs.length; i++) {
                        if (inputs[i].type !== 'file' && inputs[i].value == '') {
                            inputs[i].value = '0';
                        }
                    }
                    var price = parseFloat(document.forms["productForm"]["price"].value);
                    var sale = parseFloat(document.forms["productForm"]["sale"].value);
                    if (price < 0 || sale < 0) {
                        alert("Price and discount can't be negative");
                        return false;
                    }
                    // Check if any file input is empty
                    var fileInput = document.getElementById('photo');
                    var file = fileInput.files[0];

                    // Check if a file has been selected
                    if (!file) {
                        alert("Please select a photo.");
                        return false;
                    }

                }
            </script>
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
            <jsp:include page="component/admin/slidebarmarketing.jsp"></jsp:include>
                <!-- Content Start -->
                <div class="content">
                    <div class="container-xxl">
                    <jsp:include page="component/admin/navbar.jsp"></jsp:include>
                        <!-- Sale & Revenue Start -->
                        <div class="container pt-4 px-4">
                            <div class="row">
                                <form name="productForm" onsubmit="return validateForm()" action="addSlider" method="POST" enctype="multipart/form-data">
                                    <h2 class="mb-3">Add Slide</h2>

                                    <br>
                                    <label for="name">Slider Title:</label><br>
                                    <input type="text" id="productName" name="title" required=""><br>

                                    <label for="photo">Upload Images:</label><br>
                                    <input type="file" accept="image/*" class="form-control" name="photo" id="recipient-name"><br>
                                    <label for="name">Backlink:</label><br>
                                    <input type="text" id="productName" name="backlink" required=""><br>
                                    <label for="supplier">Status:</label><br>
                                    <select id="brand" name="status">

                                        <option  value="1">Available</option>
                                        <option  value="0">Not Available</option>

                                    </select>

                                    <button type="submit" class="btn btn-primary mt-3">Add Slider</button>     
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