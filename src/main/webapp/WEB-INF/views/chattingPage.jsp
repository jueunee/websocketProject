<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>하...</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="static/js/chattingPage.js"></script>
</head>
<link rel="stylesheet" href="static/css/chattingPage.css">
<body>
<header class="container-fluid">
    <p>together</p>
    <a href="/logout">로그아웃</a>
</header>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4>Chatting List</h4>
            <input type="hidden" id="session" value="${member.user_id}">
            <ul class="nav nav-pills nav-stacked" id="chatList">
                <c:forEach var="list" items="${list}">
                    <li id='${list._id}'>${list.user_id}</li>
                    <button onclick="deleteRoom(${list._id})">방 나가기</button>
                </c:forEach>
            </ul><br>
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">매칭하기</button>
            <div class="input-group">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
            </div>
        </div>

        <div id="mainBox">
            <h1>together</h1>
            <h4>MBTI Random Chat</h4>
        </div>

        <div class="col-sm-9">
            <div id="messageBox" style="display: none">
            <h4 id="otherName">RECENT POSTS</h4>
            <hr>
            <ul id="chatStart">

            </ul>
            <form id="messageForm">
                <div>
                    <div>
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off"/>
                        <!--<input type='file' name='uploadFile' id="file" multiple="multiple" onchange="uploadF()">-->
                        <button type="submit" id="submit">보내기</button>
                    </div>
                </div>
            </form>
            </div>
        </div>
    </div>
</div>

<%-- modal --%>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-header">
            <h3>Matching <span id="modalTitle"></span></h3>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>

        <div class="modal-body">
            <div>
                <label>전체선택</label>
                <input type="checkbox" name="mbti" value="selectall" onclick="selectAll(this)">
                <br>
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
                <input type="checkbox" name="gender" value="F">여
                <input type="checkbox" name="gender" value="M">남
            </div>
        </div>

        <div class="modal-footer">
            <button class="modalSave" class="btn btn-default" onclick="matching()"> 매칭하기 </button>
        </div>
    </div>
</div>

<!-- reportModal -->
<div class="modal fade" id="reportModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>





<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</body>
</html>
