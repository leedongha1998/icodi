const ws = new WebSocket(`ws://${location.host}/icodi/helloWebSocket`);

ws.addEventListener('open', (e) => console.log('open : ', e));
ws.addEventListener('error', (e) => console.log('error : ', e));
ws.addEventListener('close', (e) => console.log('close : ', e));
ws.addEventListener('message', (e) => {
	console.log('message : ', e);	
	
	const {messageType, data : {msg}, time} = JSON.parse(e.data);
	
	switch(messageType){
		case 'NOTIFY_NEW_COMMENT' : 
			const wrapper = document.querySelector("#btn-alarm");
			const i = document.querySelector("#notification");
			i.classList.add('bell');
			i.addEventListener('click',()=>{
				i.remove();
			})
			wrapper.append(i);
			break;
	}
	
});
