<template>
  <b-form>
    <b-form-group
        label="Username"
    >
      <b-form-input
          v-model="username"
          type="text"
          required
          placeholder="Username"
      ></b-form-input>
    </b-form-group>
    <b-form-group
        label="Email address:"
    >
      <b-form-input
          v-model="email"
          type="email"
          required
          placeholder="Enter email"
      ></b-form-input>
    </b-form-group>
    <b-form-group
        label="Password:"
    >
      <b-form-input
          id="input-1"
          v-model="password"
          type="password"
          required
          placeholder="Password"
      ></b-form-input>
    </b-form-group>
    <b-button @click="Register" block>Register</b-button>
    <b-button block>Login</b-button>
  </b-form>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      email: ""
    }
  },
  methods: {
    Register: function () {
      axios.post("http://localhost:8080/register", {
        username: this.username,
        password: this.password,
        email: this.email
      })
          .then(function (response) {
            console.log(response)
            localStorage.userID = response.data.uuid
            localStorage.username = response.data.username
          })
    }
  }
}
</script>

<style scoped>

</style>