<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/main.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>

</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <table id="results" class="table">
                <thead>
                    <tr>
                        <th>uuid</th>
                    </tr>
                </thead>
                <tbody>
                    <jsp:useBean id="uuids" scope="request" type="java.util.List"/>
                    <c:forEach var="uuid" items="${uuids}">
                        <tr class="table-danger">
                            <td>${uuid}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/uuid.js"></script>
</body>