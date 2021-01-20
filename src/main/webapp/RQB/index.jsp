<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <title>RQB</title>
        <!-- head -->
        <%@include file="/RQB/include/head.jspf"  %>
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/RQB/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/RQB/include/menu.jspf"  %>
            
            <div id="main">
                <div class="header">
                    <h1>RQB</h1>
                    <h2>Request Board</h2>
                </div>
                
                <img src="${pageContext.request.contextPath}/RQB/images/portfolio.png" width="100%">
                
            </div>
        </div>
        <!-- Foot -->
        <%@include file="/RQB/include/foot.jspf"  %>   
    </body>
</html>
