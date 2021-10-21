<template>
  <div>
    메시지:
    <input v-model="message" type="text" @keyup="sendMessage" />
    <div v-for="(item, idx) in resList" :key="idx">
      <h3>내용: {{ item.message }}</h3>
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
  name: "App",
  data() {
    return {
      message: "",
      resList: [],
    };
  },
  created() {
    this.connect();
  },
  methods: {
    sendMessage(e) {
      if (e.keyCode === 13 && this.message !== "") {
        this.send();
        this.message = "";
      }
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          message: this.message,
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },
    connect() {
      const serverURL = "http://localhost:8080";
      let socket = new SockJS(serverURL);

      this.stompClient = Stomp.over(socket);
      console.log(`try to connect - ${serverURL}`);
      this.stompClient.connect(
        {},
        (frame) => {
          this.connected = true;
          console.log("Socket Connection Success", frame);
          this.stompClient.subscribe("/send", (res) => {
            console.log("Sub Message : ", res.body);

            this.resList.push(JSON.parse(res.body));
          });
        },
        (error) => {
          console.log("Socket Connection Fail", error);
          this.connected = false;
        }
      );
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
