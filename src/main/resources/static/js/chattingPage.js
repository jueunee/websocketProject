$(document).ready(function () {
    connect(); //웹소켓 연결 함수 실행
    document.querySelector('#messageForm').addEventListener('submit', sendMessage, true)
})

let stompClient = null

/*
* SockJS와 stompClient를 이용하여 springBoot에서 구성한 엔드 포인트에 연결
*/
function connect() {
    let userId = "userA"; //세션 아이디값

    if(userId){
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected);

    }
}

/*
* 웹소켓 연결 성공시, 실행할 함수
*/
function onConnected() {
    // 웹소켓 목적지 구독
    stompClient.subscribe('/topic/public', onMessageReceived);
}

/*
* 구독한 목적지에서 받아온 data를 실행할 함수
*/
function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);

    $('#chatStart')
        .append($('<li>')
            .append($('<span>'+ message.sendUser + '</span>')
                .append($('<p>' + message.message + '</p>'))))

    $('#chartStart').scrollTop = $('#chartStart').scrollHeight;
}

/*
* 메시지를 보내는 함수
*/
function sendMessage(e) {
    let messageContent = $('#message').val();

    if (messageContent && stompClient) {
        let chatMessage = {
            "listId" : {"_id" : "632bcc06c83bfb1c473790a7", "requestUser" : "userA", "responseUser" : "userB"}, // 리스트 값
            "sendUser": "userA", // 세션값
            "message": messageContent
        };
        console.log(chatMessage);
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        $('#message').val("");
    }

    e.preventDefault2();

}