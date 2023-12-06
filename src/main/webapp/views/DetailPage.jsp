<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="initial-scale=1, width=device-width" />
    <title>${product.line}</title>
    <link rel="stylesheet" href="../CSS/detail.css" >
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Arial:wght@400;700&display=swap" type="text/css">
</head>
<body>
<article>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <section class="info">
        <section class="line_product">
            <h1>${product.line}</h1>
        </section>
        <section class="preview">
            <div class="button" id="previous">
                <img width="50%" src="https://img.icons8.com/plumpy/96/back--v1.png" alt="back--v1"/>
            </div>
            <div class="picture">
                <div class="slideShow" id="slideShow">
                    <c:forEach var="link" items="${imagelink}">
                        <div class="picbox">
                            <img src=${link}>
                        </div>
                    </c:forEach>
                </div>
                <div class="tablist">
                    <c:forEach var="link" items="${imagelink}">
                        <div class="point">
                            <svg xmlns="http://www.w3.org/2000/svg" width="9" height="8" viewBox="0 0 9 8" fill="none">
                                <rect x="0.5" width="8" height="8" rx="4" fill="black" fill-opacity="0.8"/>
                            </svg>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="button" id="next">
                <img width="50%" src="https://img.icons8.com/plumpy/96/back--v1.png" style="transform: scaleX(-1) scaleY(-1);" alt="back--v1"/>
            </div>
        </section>
        <section class="specifi">
            <div class="header">
                <h1>THÔNG SỐ KỸ THUẬT</h1>
            </div>
            <section class="detail">
                <div class="detail_value">
                    <h1>Vi xử lý</h1>
                    <p>${speci[0]}</p>
                </div>
                <div class="detail_value">
                    <h1>RAM/ROM</h1>
                    <p>${speci[1]}</p>
                </div>
                <div class="detail_value">
                    <h1>Màn hình</h1>
                    <p>${speci[2]}</p>
                </div>
                <div class="detail_value">
                    <h1>Camera</h1>
                    <p>${speci[3]}</p>
                </div>
                <div class="detail_value">
                    <h1>Pin</h1>
                    <p>${speci[4]}</p>
                </div>
                <div class="detail_value">
                    <h1>Kết nối</h1>
                    <p>${speci[5]}</p>
                </div>
            </section>
        </section>
    </section>
    <section class="variation">
        <h1>Phiên bản. <span class="deep">Mẫu nào phù hợp nhất với bạn?</span></h1>
        <section class="vari">
            <c:forEach var="VariRom" items="${rom}">
<%--                <a href="${request.contextPath}/views/detail?line=${product.line}&variRom=${VariRom}" style="text-decoration: none; color:black">--%>
                <div class="vers" onclick="displayColor(this, '${product.line}', '${VariRom}')">
                    <div class="left">
                        <h1>${product.line} ${VariRom}GB</h1>
                    </div>
                    <div class="right">
                        <p>Chỉ từ <fmt:formatNumber type="number" value="${product.price}" pattern="#,###"/>đ</p>
                    </div>
                </div>
                </a>
            </c:forEach>
        </section>
        <section class="select_color" id="color">
            <h1>Màu sắc. <span class="deep">Chọn màu bạn yêu thích!</span></h1>
            <section class="color" id="colorSection">
                <c:forEach var="Color" items="${color}">
                    <a href="${request.contextPath}/views/detail?line=${product.line}&variRom=${variRom}&id=${Color.productId}" style="text-decoration: none; color:black">
                    <div class="vers" onclick="displayBuy(this)" name="action" value="load">
                        <div class="left">
                            <h1>${Color.productName}</h1>
                        </div>
                        <div class="right">
                            <p>Chỉ từ <fmt:formatNumber type="number" value="${product.price}" pattern="#,###"/>đ</p>
                        </div>
                    </div>
                    </a>
                </c:forEach>
            </section>
        </section>
        <section class="button" id="button">
            <a href="${request.contextPath}/views/detail?action=add_cart&line=${product.line}&variRom=${variRom}&id=${id}" style="text-decoration: none; color:black">
            <button class="add_cart" id="add_cart" onclick="changeContentBuy()">Thêm vào giỏ hàng</button>
            </a>
            <a href="${request.contextPath}/views/detail?action=buy&line=${product.line}&variRom=${variRom}&id=${id}" style="text-decoration: none; color:black">
                <button class="buy">Mua</button>
            </a>
        </section>
    </section>
    <script src="../JS/detail.js"></script>
    <script src="https://unpkg.com/gsap@3.9.0/dist/gsap.min.js"></script>
</article>
</body>
</html>