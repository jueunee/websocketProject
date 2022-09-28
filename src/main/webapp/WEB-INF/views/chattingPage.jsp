<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>test</title>
</head>
<body>
<script src="static/js/chattingPage.js"></script>
<script type="text/javascript" src="static/js/lib/jquery-3.6.0.min.js"></script>
<div>
    <div>
        <div>
            <h2>Chatting List</h2>
        </div>
        <ul>
            <c:forEach var="list" items="${list}">
                <li><span onclick=chatDetail(this) id='${list._id}'>${list.user_id}</span></li>
            </c:forEach>
        </ul>
    </div>
</div>

<div>
    <div>
        <div>
            <h2>Chatting Page</h2>
        </div>
        <ul id="chatStart">

        </ul>
        <form id="messageForm">
            <div>
                <div>
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off"/>
                    <button type="submit" id="submit">보내기</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</body>
</html>