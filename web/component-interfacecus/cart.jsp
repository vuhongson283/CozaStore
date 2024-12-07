<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="priceCart" value="${0.00}"/>
<c:forEach items="${listPC}" var="p">
    <c:set var="priceCart" value="${priceCart+p.price*(100-p.sale_price)/100*p.quantity}" />
</c:forEach>
<div class="wrap-header-cart js-panel-cart">
    <div class="s-full js-hide-cart"></div>

    <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
            <span class="mtext-103 cl2">
                Your Cart
            </span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
                <i class="zmdi zmdi-close"></i>
            </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
            <ul class="header-cart-wrapitem w-full">
                <c:forEach items="${listPC}" var="p">
                    <li class="header-cart-item flex-w flex-t m-b-12 ">
                        <a href="removeItem?user_id=${sessionScope.acc.user_id}&pid=${p.product_id}&sid=${p.size_id}"  >
                            <div class="header-cart-item-img">
                                <img src="img_product/${p.img}" width="100px" height="70px"alt="IMG">
                            </div>
                        </a>
                        <div class="header-cart-item-txt p-t-8">
                            <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                ${p.product_name}
                            </a>
                            <div style="display: flex">
                            <span class="header-cart-item-info">
                                ${p.quantity} x $<fmt:formatNumber pattern="##.00" value="${p.price*(100-p.sale_price)/100}"/>
                            </span>
                            
                            <span class="header-cart-item-info" style="padding-left:70px">
                                <c:forEach items="${size}" var="s">
                                    <c:if test="${p.size_id==s.size_id}">Size:<fmt:formatNumber pattern="##" value="${s.size}"/></c:if>
                                </c:forEach>  
                            </span>
                            </div>
                        </div>
                    </li>
                </c:forEach>

            </ul>

            <div class="w-full">
                <div class="header-cart-total w-full p-tb-40 ">
                    Total: $ <fmt:formatNumber pattern="##.##" value="${priceCart}"/>
                </div>

                <div class="header-cart-buttons flex-w w-full">
                    <a href="cartDetail" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
                        View Cart
                    </a>

                    <a href="myPurchase?status=1" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                        My Purchases
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>