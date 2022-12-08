<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <p style="margin: 3px;" id="createId" >Account Creation</p>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-create"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-create"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-create"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <div>
        <form action="">
          <div class=img>
          <div class="radio">
            <img id="sword" src="C:/Users/Student/workspace/java-finalcapstone-team0/vue/capstone-images/olivebranch.svg" />
            <input type="radio" name="answer1" value="sword">
          </div>
          </div>
        </form>
      </div>
      <router-link class="needAnAccount" :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>


.form-register {
  display: flex;
  flex-direction: column;
  color: white;
  margin-top: 10%;
  text-align: center;
  justify-content: space-evenly;
  align-items: center;
  border: solid 1px black
  
  

}

#username {
  text-align: left;
}

.form-create {
  width: 40%;
  justify-content: space-evenly;
  padding: 10px;
  margin: 15px;
}

#createId {
  color: orange;
  font-size: 25px;
}

.img {
  display: flex;
  align-items: end;
}

img {
  border: 1px solid black;
}

.radio {
  position: relative;
  height: 50px;
}

input[type="radio"] {
  position: absolute;
  top: calc(50% - 4px);
  right: 0;
  margin: 0;
}





</style>
