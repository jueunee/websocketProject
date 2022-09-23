<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>test</title>
</head>
<body>

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

<script type="text/javascript" src="static/js/lib/jquery-3.6.0.min.js"></script>
<script src="static/js/chattingPage.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

</body>
</html>