<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Page</title>
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <link   rel="stylesheet" href="CSS/ProductPage.css">
    <link   rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap">
</head>
<body>
    <div class="product_container">
        <div class="brands">
            <a href="/productservlet?type=brand&value=Apple" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong" >
                </div>
                <div>
                    Apple
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Samsug" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Samsung
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Xiaomi" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Xiaomi
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Oppo" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    OPPO
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Realmi" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Realmi
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Vivo" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Vivo
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Asus" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Asus
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Google" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Google
                </div>
            </a>
            <a href="/productservlet?type=brand&value=Nokia" style=" text-decoration: none" class="brand_items">
                <div>
                    <img src="https://s3-alpha-sig.figma.com/img/9360/8395/21dfb11202a1c3f1d1a7f5a4e34bc80c?Expires=1701043200&Signature=VPUDE1a8Loedy2aKqY9Wckwm4KpIKdrYBtjfWrhbsvLdKt5lK44OBLHHnhpnKD7tPlOSEVh~eRz3IjH~PJ09x0YfQnrBCNO-9sbAj2Kxwnc8Xt5Jc3LV2StVS6YouENeMwpYTn0FuHGsDe-6AnjLqFF-wcA3jN6944noJvR3O5HD4g-QRTmzf3~~oYsaDO~Y-a13kRY~vBeUNqEOrEk8OAwMNuvuGduAIWm1Y2KJ-h~HUfH8IrRWLxoVWsiaFU8WXcScwQ6b54xlrAtHDinRJJme1WD9vfAixkgC-YlKXqfEhua1YEN5-iUjPr-WQjYbGrsJ0ByU4fIZdRkVnOkBSg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4" alt="wrong">
                </div>
                <div>
                    Nokia
                </div>
            </a>
        </div>
        <c:forEach var="p" items="${productlist}" varStatus="loop">
            <c:if test="${loop.index % productperpage == 0}">
                <div class="list_product" id="${(loop.index / productperpage).intValue()}">
            </c:if>
            <div class="items">
                <div class="image_div">
                    <img src="${p.productImage}">
                </div>
                <div class="items_name_div">
                        ${p.productName}
                </div>
                <div class="price_div">
                    <fmt:formatNumber value="${p.price}" type="currency" pattern="###,###,###.00đ" />
                </div>
            </div>
            <c:if test="${loop.index % productperpage == productperpage - 1 or loop.last}">
                </div>
            </c:if>
        </c:forEach>
            <div class="pagenav">
                <a href="productservlet?type=${type}&value=${value}&page=${pagenum - 1}" ${pagenum <= 0 ? 'style="pointer-events: none; color: gray;"' : ''}><</a>

                <c:forEach var="page" begin="1" end="${totalpages}">
                    <c:choose>
                        <c:when test="${page eq 1 && (pagenum + 1) - page >= 3}">
                            <a href="productservlet?type=${type}&value=${value}&page=${page - 1}">${page}</a>
                            <a>...</a>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${page eq 1 && (pagenum + 1) - page >= 2}">
                                <a href="productservlet?type=${type}&value=${value}&page=${page - 1}">${page}</a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${page eq (pagenum+1)-1 || page eq (pagenum+1) || page eq (pagenum+1)+1}">
                        <a href="productservlet?type=${type}&value=${value}&page=${page - 1}">${page}</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${page eq totalpages && page - (pagenum+1) >= 3}">
                            <a>...</a>
                            <a href="productservlet?type=${type}&value=${value}&page=${page - 1}">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${page eq totalpages && page - (pagenum+1) >= 2}">
                                <a href="productservlet?type=${type}&value=${value}&page=${page - 1}">${page}</a>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <a href="productservlet?type=${type}&value=${value}&page=${pagenum + 1}" ${pagenum >= totalpages - 1 ? 'style="pointer-events: none; color: gray;"' : ''}>></a>
            </div>
        </div>
        <script src="JS/product.js"></script>
    </div>
</body>
</html>