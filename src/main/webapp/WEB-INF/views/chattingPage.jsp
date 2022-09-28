<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>test</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<body>
<script src="static/js/chattingPage.js"></script>
<%--<script type="text/javascript" src="static/js/lib/jquery-3.6.0.min.js"></script>--%>
<div>
    <div>
        <div>
            Chatting List
        </div>
        <ul id="chatList">
        <c:forEach var="list" items="${list}">
            <li id='${list._id}'>${list.user_id}</li>
        </c:forEach>
            <li id='test3'>test3</li>
            <li id='test1'>test1</li>
            <li id='test2'>tset2</li>
        </ul>
        <div>
            <button onclick="matchingModal()">매칭하기</button>
        </div>
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

<%-- modal --%>
<div id="modal">
    <div class="modalBox">
        <div class="modalHeader">
            <h3>Matching <span id="modalTitle"></span></h3>
            <button class="modalClose">×</button>
        </div>

        <div class="modalCon">
            <div>
                <label>MBTI</label>
                <input type="checkbox" name="mbti1" value="E">E
                <input type="checkbox" name="mbti1" value="I">I
                <input type="checkbox" name="mbti2" value="S">S
                <input type="checkbox" name="mbti2" value="N">N
                <input type="checkbox" name="mbti3" value="T">T
                <input type="checkbox" name="mbti3" value="F">F
                <input type="checkbox" name="mbti4" value="J">J
                <input type="checkbox" name="mbti4" value="P">P
                <br>
                <label>성별</label>
                <input type="checkbox" name="gender" value="female">여
                <input type="checkbox" name="gender" value="male">남
            </div>
        </div>

        <div class="modalFooter">
            <button class="modalSave" onclick="matching()"> 매칭하기 </button>
        </div>
    </div>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</body>
</html>