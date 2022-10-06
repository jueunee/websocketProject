let stompClient = null
let id = null;
let isEnd = false; // 채팅메시지를 끝까지 다 불러왔을 경우 체크

$(document).ready(function () {
    connect(); //웹소켓 연결 함수 실행
    document.querySelector('#messageForm').addEventListener('submit', sendMessage, true)

    // 파라미터받기
    let queryString = location.search;
    const urlParam = new URLSearchParams(queryString)
    if (urlParam.get("user_id") !== null) {
        $('#mainBox').css("display", "none");
        $('#messageBox').css("display", "block");

        $('#otherName').text(urlParam.get("user_id"));

        history.replaceState({}, null, location.pathname); // url 파라미터 지우기
    } else {
        history.replaceState({}, null, location.pathname);
    }

    $('.modalClose').click(function () {
        $('#modal').fadeOut(150);
        $('.modalSave').off('click');
    });

    $('#chatList li').click(function () {
        $('#mainBox').css("display", "none");
        $('#messageBox').css("display", "block");

        $('#otherName').text($(this).text())

        isEnd = false;
        id = $(this).attr('id');
        $('#otherName')
            .append($('<button id="'+$(this).text()+'">신고하기</button>')
                .on('click',function() {
                    reportUser($(this).attr("id"));
                }))

        roadChat(id);
    });
});

/*
* SockJS와 stompClient를 이용하여 springBoot에서 구성한 엔드 포인트에 연결
*/
function connect() {
    let userId = $('#session').val(); //세션 아이디값

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
    stompClient.subscribe('/topic/createdRoom', setCreatedRoom);
}

function setCreatedRoom(payload) {
    let roomMember = JSON.parse(payload.body);

    if (roomMember.response_user === $('#session').val()) {
        $('#chatList')
            .append($(`<li id="` + roomMember.id + `" class='blink'>` + roomMember.request_user + "</li>")
                .css("display", "none")
                .on('click', function () {
                    $(this).removeClass("blink");
                    $('#mainBox').css("display", "none");
                    $('#messageBox').css("display", "block");

                    id = roomMember.id;
                    roadChat(roomMember.id)
                }))
    }
}

/*
* 구독한 목적지에서 받아온 data를 실행할 함수
*/
function onMessageReceived(payload) {
    let message = JSON.parse(payload.body);

    if ($('#' + message.id).css("display") === "none") {
        $('#' + message.id).css('display', 'block');
    }

    if (id != null && message.id == id) {
        if (message.sender !== $('#session').val()) { //세션아이디 부여
            $('#chatStart')
                .append($('<li>')
                    .attr("id", "other")
                    .css("color", "skyblue")
                    .append($('<span>' + message.sender + '</span>')
                        .append($('<p>' + message.message + '</p>'))))
        } else {
            $('#chatStart')
                .append($('<li>')
                    .attr("id", "user")
                    .css("color", "coral")
                    .append($('<span>' + message.sender + '</span>')
                        .append($('<p>' + message.message + '</p>'))))
        }
    } else if (id === null) {
        if (message.sender === $('#session').val()) {
            $('#chatStart')
                .append($('<li>')
                    .attr("id", "user")
                    .css("color", "coral")
                    .append($('<span>' + message.sender + '</span>')
                        .append($('<p>' + message.message + '</p>'))))
        } else {
            $('#chatStart')
                .append($('<li>')
                    .attr("id", "other")
                    .css("color", "skyblue")
                    .append($('<span>' + message.sender + '</span>')
                        .append($('<p>' + message.message + '</p>'))))
        }
    }

    $('#chatStart').scrollTop = $('#chatStart').scrollHeight;
}

/*
* 메시지를 보내는 함수
*/
function sendMessage(e) {
    if (id === null) {
        let first_id = $('#chatList li').last().text()

        if ($("#chatStart > li").val() === undefined) {

            const send = {
                "request_user": $(' #session').val(),
                "response_user": $('#chatList li').last().text()
            };

            $.ajax({
                url: "firstMessage",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(send),
                dataType: "json",
                success: function (result) {
                    $('#chatList li').last()
                        .append($("<input type='hidden' id=" + first_id + " value=" + result + ">"))

                    let messageContent = $('#message').val();

                    if (messageContent && stompClient) {
                        let chatMessage = {
                            "id": result,
                            "sender": $('#session').val(), // 세션값
                            "message": messageContent
                        };
                        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
                        $('#message').val("");

                    }
                }
            })
        } else {
            let messageContent = $('#message').val();

            if (messageContent && stompClient) {
                let chatMessage = {
                    "id": $('#' + first_id).val(),
                    "sender": $('#session').val(), // 세션값
                    "message": messageContent
                };
                stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
                $('#message').val("");
            }
        }
    } else {
        let messageContent = $('#message').val();

        if (messageContent && stompClient) {
            let chatMessage = {
                "id": id,
                "sender": $('#session').val(), // 세션값
                "message": messageContent
            };
            stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
            $('#message').val("");
        }
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
                if (obj.sender !== $('#session').val()) { //세션아이디 부여
                    $('#chatStart')
                        .append($('<li>')
                            .attr("id", "other")
                            .css("color", "skyblue")
                            .append($('<span>' + obj.sender + '</span>')
                                .append($('<p>' + obj.message + '</p>')
                                    .append($('<input type="hidden" value=' + obj.num + '>')))))
                } else {
                    $('#chatStart')
                        .append($('<li>')
                            .attr("id", "user")
                            .css("color", "coral")
                            .append($('<span>' + obj.sender + '</span>')
                                .append($('<p>' + obj.message + '</p>')
                                    .append($('<input type="hidden" value=' + obj.num + '>')))))
                }
            })
        },
        error: function () {
            console.log("데이터 불러오기 실패")
        }
    });
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
            "user": $('#session').val()
        }
        console.log(sendData)

        $.ajax({
            url: "matching",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(sendData),
            dataType: "json",
            success: function (e) {
                if (e[0] === "random") {
                    let sendData = {
                        "other": e[1],
                        "user": $('#session').val()
                    }
                    stompClient.send("/app/chat.createdRoom", {}, JSON.stringify(sendData));
                    $('#modal').fadeOut(150);
                } else if (e[0] === "nothing") {
                    alert("없음")
                } else {
                    $('#mainBox').css("display", "none");
                    $('#messageBox').css("display", "block");

                    let sendData = {
                        "other": e[1],
                        "user": $('#session').val()
                    }
                    stompClient.send("/app/chat.createdRoom", {}, JSON.stringify(sendData));
                    $('#modal').fadeOut(150);
                }
                location.href = location.href + "?user_id=" + e[1];
            }
        })
    }
}

//이전기록 불러오기 위한 업스크롤 이벤트
$(window).on('scroll', function () {
    let scrollTop = $(this).scrollTop();
    if (scrollTop < 1) {
        fetchList();
    }
})

//이전기록 15개 불러오기
function fetchList() {

    if (isEnd === true) {
        alert("마지막페이지")
        return;
    }

    let endNum = $('#chatStart > li > span > p > input').val();
    const data = {
        "endNum": endNum,
        "id": id
    }

    $.ajax({
        url: "roadNextChat",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (message) {
            let length = message.length;

            if (length < 15) {
                isEnd = true;
            }

            $.each(message, (index, obj) => {
                if (obj.sender !== $('#session').val()) { //세션아이디 부여
                    $('#chatStart')
                        .prepend($('<li>')
                            .attr("id", "other")
                            .css("color", "skyblue")
                            .append($('<span>' + obj.sender + '</span>')
                                .append($('<p>' + obj.message + '</p>')
                                    .append($('<input type="hidden" value=' + obj.num + '>')))))
                } else {
                    $('#chatStart')
                        .prepend($('<li>')
                            .attr("id", "user")
                            .css("color", "coral")
                            .append($('<span>' + obj.sender + '</span>')
                                .append($('<p>' + obj.message + '</p>')
                                    .append($('<input type="hidden" value=' + obj.num + '>')))))
                }
            })
        }
    })

}

function selectAll(selectAll) {
    const checkboxes1
        = document.getElementsByName('mbti1');

    checkboxes1.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })

    const checkboxes2
        = document.getElementsByName('mbti2');

    checkboxes2.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })

    const checkboxes3
        = document.getElementsByName('mbti3');

    checkboxes3.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })

    const checkboxes4
        = document.getElementsByName('mbti4');

    checkboxes4.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}

// 방 나가기
function deleteRoom(e) {
    console.log(e)
    let deleteUser = {
        "user_id" : $('#session').val(),
        "id" : e
    }

    $.ajax({
        url: "deleteRoom",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(deleteUser),
        dataType: "json",
        success: function (message) {
            if (message == 1) {
                alert("나가기 완료!")
                location.reload()
            } else {
                alert("다시 시도")
            }
        }
    })
}

// 유저 신고하기
function reportUser(e) {

}

// 파일 업로드 -- 진행중
function uploadF() {
    let ws = new WebSocket('ws://localhost:8080/binary');
    ws.binaryType = 'blob';
    ws.onopen = function () {
        console.log('open socket');g

        let inputFile = $("input[name='uploadFile']");
        let files = inputFile[0].files;
        ws.send(files);
    }


    ws.onmessage = function (message) {
        var blob = message.data;
        var fileReader = new FileReader();
        fileReader.onload = function (event) {
            var arrayBuffer = event.target.result;
            var dataview = new DataView(arrayBuffer);
            var answer = dataview.getFloat64(0);
            alert("Server> : " + answer);
        };
        fileReader.readAsArrayBuffer(blob);
    };


    /*let formData = new FormData();
    let inputFile = $("input[name='uploadFile']");
    let files = inputFile[0].files;
    let fileReader = new FileReader();

    // formData에 fileData 추가
    for (let i = 0; i < files.length; i++) {
        formData.append("uploadFile", files[i]);
    }

    let info = {
        type: "blob",
        file: formData
    }
    stompClient.send("/app/chat.sendFile", {}, JSON.stringify(info));

    arrayBuffer = this.result;
    stompClient.send("/app/chat.sendFile", {}, arrayBuffer);
    console.log("이건 뭐지??? : " + arrayBuffer)
    $('#file').val("");*/

}
