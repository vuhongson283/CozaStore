

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
        <link rel="stylesheet" href="modal/stylecus.css" />
        <link rel="stylesheet" href="modal/styleviewcus.css" />
        <jsp:include page="component/admin/head.jsp"></jsp:include>
            <script src="canvasjs.min.js"></script>
        </head>
    <jsp:include page="component/admin/header.jsp"></jsp:include>
        <body onload="updateFil()">
            <div class="container-fluid position-relative bg-white d-flex p-0">
            <jsp:include page="component/admin/slidebarsale.jsp"></jsp:include>
                <!-- Content Start -->
                <div class="content">
                    <div class="container">
                    <jsp:include page="component/admin/navbar.jsp"></jsp:include>
                        <div class="row">
                            <div class="col-lg-3 box_info" style="display: flex; border: 0.1px solid #ebe7e7;box-shadow: rgba(136, 165, 191, 0.48) 6px 2px 16px 0px, rgba(255, 255, 255, 0.8) -6px -2px 16px 0px; margin-top: 30px; margin-left: 11px;width: 22%;">
                                <div class="col-lg-6" style="margin-top: 12px;">
                                    <i class="fa-solid fa-table-list" style="font-size: 54px;
                                       margin-left: 12%;
                                       margin-top: 10%;"></i>
                                </div>
                                <div class="col-lg-6" style="margin-top: 12px; margin-left: 12%;">
                                    <h6 style="margin-bottom: 0px">Total Order</h6>
                                    <h1 style="color: green; margin-top: 14%; margin-left: 16%;">${totalOrder}</h1>
                            </div>
                            </div>
                        <div class="col-lg-3 box_info" style="display: flex; border: 0.1px solid #ebe7e7;box-shadow: rgba(136, 165, 191, 0.48) 6px 2px 16px 0px, rgba(255, 255, 255, 0.8) -6px -2px 16px 0px; margin-top: 30px; margin-left: 11px; width: 22%;">
                            <div class="col-lg-6" style="margin-top: 12px;">
                                    <i class="fa-solid fa-rectangle-xmark" style="font-size: 54px;
                                       margin-left: 12%;
                                       margin-top: 10%;"></i>
                                </div>
                                <div class="col-lg-6" style="margin-top: 12px; margin-left: 12%;">
                                    <h6 style="margin-bottom: 0px">Cancelled</h6>
                                    <h1 style="color: green; margin-top: 14%; margin-left: 16%;">${orderCancelled}</h1>
                            </div>
                        </div>
                        <div class="col-lg-3 box_info" style="display: flex; border: 0.1px solid #ebe7e7;box-shadow: rgba(136, 165, 191, 0.48) 6px 2px 16px 0px, rgba(255, 255, 255, 0.8) -6px -2px 16px 0px; margin-top: 30px; margin-left: 11px; width: 22%;">
                            <div class="col-lg-6" style="margin-top: 12px;">
                                    <i class="fa-solid fa-money-bill" style="font-size: 54px;
                                       margin-left: 12%;
                                       margin-top: 10%;"></i>
                                </div>
                                <div class="col-lg-6" style="margin-top: 12px;">
                                    <h6 style="margin-bottom: 0px">Total Profit</h6>
                                    <h3 style="margin-top: 14%; color: green
                                    ">${orderProfit} $</h3>
                            </div>
                        </div>
                        <div class="col-lg-3 box_info" style="display: flex; border: 0.1px solid #ebe7e7;box-shadow: rgba(136, 165, 191, 0.48) 6px 2px 16px 0px, rgba(255, 255, 255, 0.8) -6px -2px 16px 0px; margin-top: 30px; margin-left: 11px;width: 22%;">
                                <div class="col-lg-6" style="margin-top: 12px;">
                                    <i class="fa-solid fa-comment" style="font-size: 54px;
                                       margin-left: 12%;
                                       margin-top: 10%;"></i>
                                </div>
                                <div class="col-lg-6" style="margin-top: 12px;">
                                    <h6 style="margin-bottom: 0px">Highly feedback</h6>
                                    <h1 style="margin-top: 14%; margin-left: 48%; color: green">${feedbackHighly}</h1>
                            </div>
                        </div>
                    </div>
                    <form action="DashboardSale" method="post" id="frm">
                        <div style="display: flex">
                            <select id="type_fil" name="type_filter" class="form-select" aria-label="Default select example" style="width: 8%;
                                    margin-top: 24px;" onchange="updateFil()">
                                <option value="0" >Years</option>
                                <c:forEach items="${product_date_list}" var="i">
                                    <option value="${i}" ${year_date_choose == i ? 'selected' : ''}>${i}</option>
                                </c:forEach>
                            </select>
                            <select id="pro_date" name="products_date" class="form-select" aria-label="Default select example" style="width: 12%;
                                    margin-top: 24px; margin-left: 24px;" onchange="updateDashboard()">
                            </select>
                        </div>
                    </form>
                    <!-- Sale & Revenue Start -->
                    <div id="dashBoardContainer">
                        <c:forEach items="${listOrCom}" var="i">
                            <input name="OrCom" value="${i}" hidden="">
                        </c:forEach>
                        <c:forEach items="${listOrPay}" var="i">
                            <input name="OrPay" value="${i}" hidden="">
                        </c:forEach>
                        <c:forEach items="${listYear}" var="i">
                            <input name="YearsAgo" value="${i}" hidden="">
                        </c:forEach>
                        <c:if test="${dbChoose eq '1'}">
                            <div style="display: block; height: 100%; width: 100%"><canvas id="densityChart5" width="100" height="40" ></canvas>
                            </div>
                        </c:if>
                        <c:if test="${dbChoose eq '2'}">
                            <div style="display: block; height: 100%; width: 100%"><canvas id="densityChart3" width="100" height="40" ></canvas>
                            </div>
                        </c:if>

                    </div>

                    <!-- Sale & Revenue End -->
                </div>
                <!-- Footer Start -->
                <jsp:include page="component/admin/footer.jsp"></jsp:include>
                <!-- Footer End -->
            </div>
        </div>
        <div class="modalm" id="modal1" style="width: 700px">
        </div>
        <div id="overlaym"></div>

        <script src="modal/script.js"></script> 
        <script type="text/javascript">
                                        $(document).ready(function () {
                                            $(".link-detail").on("click", function (e) {
                                                e.preventDefault();
                                                $("#modal1").load($(this).attr("href"));
                                            });
                                        });
        </script>
        <script>
            var densityCanvas5 = document.getElementById("densityChart5");
            Chart.defaults.global.defaultFontFamily = "Lato";
            Chart.defaults.global.defaultFontSize = 18;

            if (document.getElementsByName("OrCom")[0].value === null) {
                dataA = 0;
            } else
                dataA = document.getElementsByName("OrCom")[0].value;

            if (document.getElementsByName("OrCom")[1].value === null) {
                dataB = 0;
            } else
                dataB = document.getElementsByName("OrCom")[1].value;

            if (document.getElementsByName("OrCom")[4].value === null) {
                dataE = 0;
            } else
                dataE = document.getElementsByName("OrCom")[4].value;

            if (document.getElementsByName("OrCom")[2].value === null) {
                dataC = 0;
            } else
                dataC = document.getElementsByName("OrCom")[2].value;

            if (document.getElementsByName("OrCom")[3].value === null) {
                dataD = 0;
            } else
                dataD = document.getElementsByName("OrCom")[3].value;

            var completedData = {
                label: 'Orders completed',
                data: [dataA, dataB, dataC, dataD, dataE],
                backgroundColor: 'rgba(0, 99, 132, 0.6)',
                borderWidth: 0,
                yAxisID: "y-axis-Orders-completed"
            };

            if (document.getElementsByName("OrPay")[0].value === null) {
                dataA = 0;
            } else
                dataA = document.getElementsByName("OrPay")[0].value;

            if (document.getElementsByName("OrPay")[1].value === null) {
                dataB = 0;
            } else
                dataB = document.getElementsByName("OrPay")[1].value;

            if (document.getElementsByName("OrPay")[4].value === null) {
                dataE = 0;
            } else
                dataE = document.getElementsByName("OrPay")[4].value;

            if (document.getElementsByName("OrPay")[2].value === null) {
                dataC = 0;
            } else
                dataC = document.getElementsByName("OrPay")[2].value;

            if (document.getElementsByName("OrPay")[3].value === null) {
                dataD = 0;
            } else
                dataD = document.getElementsByName("OrPay")[3].value;

            var toPayData = {
                label: 'Orders cancelled',
                data: [dataA, dataB, dataC, dataD, dataE],
                backgroundColor: 'rgba(99, 132, 0, 0.6)',
                borderWidth: 0,
                yAxisID: "y-axis-Orders-to-pay"
            };

            if (document.getElementsByName("YearsAgo")[0].value === null) {
                dataA = 0;
            } else
                dataA = document.getElementsByName("YearsAgo")[0].value;

            if (document.getElementsByName("YearsAgo")[1].value === null) {
                dataB = 0;
            } else
                dataB = document.getElementsByName("YearsAgo")[1].value;

            if (document.getElementsByName("YearsAgo")[4].value === null) {
                dataE = 0;
            } else
                dataE = document.getElementsByName("YearsAgo")[4].value;

            if (document.getElementsByName("YearsAgo")[2].value === null) {
                dataC = 0;
            } else
                dataC = document.getElementsByName("YearsAgo")[2].value;

            if (document.getElementsByName("YearsAgo")[3].value === null) {
                dataD = 0;
            } else
                dataD = document.getElementsByName("YearsAgo")[3].value;

            var OrderData = {
                labels: [dataA, dataB, dataC, dataD, dataE],
                datasets: [completedData, toPayData]
            };

            var chartOptions = {
                scales: {
                    xAxes: [{
                            barPercentage: 1,
                            categoryPercentage: 0.6
                        }],
                    yAxes: [{
                            id: "y-axis-Orders-completed"
                        }, {
                            id: "y-axis-Orders-to-pay"
                        }]
                }
            };

            var barChart = new Chart(densityCanvas5, {
                type: 'bar',
                data: OrderData,
                options: chartOptions
            });

        </script>
        <script>
            var densityCanvas3 = document.getElementById("densityChart3");
            Chart.defaults.global.defaultFontFamily = "Lato";
            Chart.defaults.global.defaultFontSize = 18;

            if (document.getElementsByName("OrCom")[0].value === null) {
                dataA = 0;
            } else
                dataA = document.getElementsByName("OrCom")[0].value;

            if (document.getElementsByName("OrCom")[1].value === null) {
                dataB = 0;
            } else
                dataB = document.getElementsByName("OrCom")[1].value;
            if (document.getElementsByName("OrCom")[2].value === null) {
                dataC = 0;
            } else
                dataC = document.getElementsByName("OrCom")[2].value;
            var completedData = {
                label: 'Orders completed',
                data: [dataA, dataB, dataC],
                backgroundColor: 'rgba(0, 99, 132, 0.6)',
                borderWidth: 0,
                yAxisID: "y-axis-Orders-completed"
            };

            if (document.getElementsByName("OrPay")[0].value === null) {
                dataA = 0;
            } else
                dataA = document.getElementsByName("OrPay")[0].value;

            if (document.getElementsByName("OrPay")[1].value === null) {
                dataB = 0;
            } else
                dataB = document.getElementsByName("OrPay")[1].value;
            if (document.getElementsByName("OrPay")[2].value === null) {
                dataC = 0;
            } else
                dataC = document.getElementsByName("OrPay")[2].value;
            var toPayData = {
                label: 'Orders cancelled',
                data: [dataA, dataB, dataC],
                backgroundColor: 'rgba(99, 132, 0, 0.6)',
                borderWidth: 0,
                yAxisID: "y-axis-Orders-to-pay"
            };

            if (document.getElementsByName("YearsAgo")[0].value === null) {
                dataA = 0;
            } else
                dataA = document.getElementsByName("YearsAgo")[0].value;

            if (document.getElementsByName("YearsAgo")[1].value === null) {
                dataB = 0;
            } else
                dataB = document.getElementsByName("YearsAgo")[1].value;
            if (document.getElementsByName("YearsAgo")[2].value === null) {
                dataC = 0;
            } else
                dataC = document.getElementsByName("YearsAgo")[2].value;
            var OrderData = {
                labels: [dataA, dataB, dataC],
                datasets: [completedData, toPayData]
            };

            var chartOptions = {
                scales: {
                    xAxes: [{
                            barPercentage: 1,
                            categoryPercentage: 0.6
                        }],
                    yAxes: [{
                            id: "y-axis-Orders-completed"
                        }, {
                            id: "y-axis-Orders-to-pay"
                        }]
                }
            };

            var barChart = new Chart(densityCanvas3, {
                type: 'bar',
                data: OrderData,
                options: chartOptions
            });

        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function updateFil() {
                var type_filter = document.getElementById("type_fil").value;
                if (type_filter === '') {
                    type_filter = 1;
                }
                var pro_date = sessionStorage.getItem('date');
                $.ajax({
                    type: "get",
                    url: "/SWP-demo3/FilterTypeDate",
                    data: {
                        type_filter: type_filter,
                        pro_date: pro_date
                    },
                    success: function (data) {
                        var row = document.getElementById("pro_date");
                        row.innerHTML = data;
                    }
                });
            }
        </script>
        <script>
            function updateDashboard() {
                var pro_date = document.getElementById("pro_date").value;
                sessionStorage.setItem('date', pro_date);
                document.getElementById("frm").submit();
            }
        </script>
    </body>


</html>



