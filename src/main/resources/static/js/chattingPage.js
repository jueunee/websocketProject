let stompClient = null
let id = null;

$(document).ready(function () {
    connect(); //웹소켓 연결 함수 실행
    document.querySelector('#messageForm').addEventListener('submit', sendMessage, true)

    $('.modalClose').click(function () {
        $('#modal').fadeOut(150);
        $('.modalSave').off('click');
    });

    $('#chatList li').click(function () {
        id = $(this).attr('id');
        roadChat(id);
    });
});

/*
* SockJS와 stompClient를 이용하여 springBoot에서 구성한 엔드 포인트에 연결
*/
function connect() {
    let userId = "userA"; //세션 아이디값

    if (userId) {
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
            .append($('<span>' + message.sender + '</span>')
                .append($('<p>' + message.message + '</p>'))))

    $('#chatStart').scrollTop = $('#chatStart').scrollHeight;
}

/*
* 메시지를 보내는 함수
*/
function sendMessage(e) {
    let messageContent = $('#message').val();

    if (messageContent && stompClient) {
        let chatMessage = {
            "id": id,
            "sender": "userA", // 세션값
            "message": messageContent
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        $('#message').val("");
    }

    e.preventDefault();

}

// 채팅 기록 불러오기
function roadChat(id) {
    $('#chatStart').empty();
    $.ajax({
        url: "roadChat",
        type: "post",
        data: {"id": id},
        success: function (message) {
            $.each(message, (index, obj) => {
                $('#chatStart')
                    .append($('<li>')
                        .append($('<span>' + obj.sender + '</span>')
                            .append($('<p>' + obj.message + '</p>'))))
            })
        },
        error: function () {
            console.log("데이터 불러오기 실패")
        }
    });
}

//모달 test
function matchingModal() {
    $('#modal').fadeIn(250);
}

// 매칭하기 메서드
function matching() {
    let mbti1 = [];
    let mbti2 = [];
    let mbti3 = [];
    let mbti4 = [];
    let gender = [];
    let check = true;

    if (!$('input[name=mbti1]').is(":checked")) {
        console.log("mbti1체크X")
        check = false;
    } else {
        $('input[name=mbti1]:checked').each(function () {
            mbti1.push($(this).val());
        })
    }

    if (!$('input[name=mbti2]').is(":checked")) {
        console.log("mbti2체크X")
        check = false;
    } else {
        $('input[name=mbti2]:checked').each(function () {
            mbti2.push($(this).val());
        })
    }

    if (!$('input[name=mbti3]').is(":checked")) {
        console.log("mbti3체크X")
        check = false;
    } else {
        $('input[name=mbti3]:checked').each(function () {
            mbti3.push($(this).val());
        })
    }

    if (!$('input[name=mbti4]').is(":checked")) {
        console.log("mbti4체크X")
        check = false;
    } else {
        $('input[name=mbti4]:checked').each(function () {
            mbti4.push($(this).val());
        })
    }

    if (!$('input[name=gender]').is(":checked")) {
        console.log("gender체크X")
        check = false;
    } else {
        $('input[name=gender]:checked').each(function () {
            gender.push($(this).val());
        })
    }

    if (check === true) {
        const sendData = {
            "mbti1": mbti1,
            "mbti2": mbti2,
            "mbti3": mbti3,
            "mbti4": mbti4,
            "gender": gender,
            "user": "userA" //user_id
        }
        console.log(sendData)

        $.ajax({
            url: "matching",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(sendData),
            dataType: "json",
            success: function (e) {
                if (e === "random") {
                    alert("랜덤매치")
                    location.reload();
                } else if (e === "nothing") {
                    alert("없음")
                } else {
                    setTimeout(function () {
                        location.reload();
                    }, 3000);
                }
            }
        })
    }
}