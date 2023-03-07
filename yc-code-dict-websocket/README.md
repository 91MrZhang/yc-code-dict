# yc-code-dict-websocket

Websocket接入示例

采用spring-websocket和java原生websocket两种方式接入，包含Server端广播以及Client端重连

应用场景
1. 大屏滚动图表实时渲染
2. 前后端即使提交
3. 工作台通知
4. 数字孪生项目实体轨迹

一般生产使用场景websocket通常采用json或base64传输

可以在浏览器F12，在控制台输入下方js代码
```javascript
let url = `ws://172.20.72.206:5517/ws/live`;
let ws = new WebSocket(url);
ws.onmessage = (resp) => console.log(resp.data);
(resp) => console.log(resp.data)
```