const getUrlParameter = name => {
    // eslint-disable-next-line
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    let regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

    // let results = regex.exec(this.props.location.search);
    let results = regex.exec(window.location.href);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};

var sock = new SockJS('http://localhost:9000/ws', null, {
    transports: ['xhr-streaming'],
    headers: {'Authorization': 'Basic ' + getUrlParameter("token")}
});
let stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {

    console.log('Connected: ' + frame);
    stompClient.subscribe('/response/data', function (greeting) {
        console.log(JSON.parse(greeting.body));
    });
});

